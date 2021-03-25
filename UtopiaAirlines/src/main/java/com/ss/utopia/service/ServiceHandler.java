package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.ss.utopia.dao.*;
import com.ss.utopia.entity.*;

public class ServiceHandler {

	Utils util;
	public ServiceHandler() {
		util = new Utils();
	}

	public Integer addAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			Integer airportId = airportDAO.addData(airport);
			conn.commit();
			return airportId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding airport data. Contact Administrator.");
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Integer addBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			Integer boookingId = bookingDAO.addData(booking);
			conn.commit();
			return boookingId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding booking data. Contact Administrator.");
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addBookingPayment(BookingPayment bookingPayment)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingPaymentDAO bookingPaymentDAO = new BookingPaymentDAO(conn,
					"booking_payment");
			bookingPaymentDAO.addData(bookingPayment);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding booking payment data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private void addBookingUser(BookingBaseUser bookingUser, Integer userType)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();

			if (userType == BookingBaseUser.USER) {
				BookingUserDAO bookingDAO = new BookingUserDAO(conn,
						"booking_user");
				bookingDAO.addData((BookingUser) bookingUser);
			} else if (userType == BookingBaseUser.AGENT) {
				BookingAgentDAO bookingDAO = new BookingAgentDAO(conn,
						"booking_agent");
				bookingDAO.addData((BookingAgent) bookingUser);
			} else {
				BookingGuestDAO bookingDAO = new BookingGuestDAO(conn,
						"booking_guest");
				bookingDAO.addData((BookingGuest) bookingUser);
			}

			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding booking user data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Integer addFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			Integer flightId = flightDAO.addData(flight);
			conn.commit();
			return flightId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding flight data. Contact Administrator.");
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addFlightBooking(FlightBooking flightBooking)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightBookingDAO flightBookingDAO = new FlightBookingDAO(conn,
					"flight_bookings");
			flightBookingDAO.addData(flightBooking);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding flight booking data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Integer addPassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PassengerDAO passengerDOA = new PassengerDAO(conn, "passenger");
			Integer passengerId = passengerDOA.addData(passenger);
			conn.commit();
			return passengerId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error adding passenger data. Contact Administrator.");
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Integer addUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO UserDAO = new UserDAO(conn, "user");
			Integer userId = UserDAO.addData(user);
			conn.commit();
			return userId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out
					.println("Error adding user data. Contact Administrator.");
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void bookFlight(User user, Flight flight, Integer seatType,
			Integer userType) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			Random rand = new Random();
			String confirmationCode = UUID.randomUUID().toString();
			Booking booking = new Booking(0, true, confirmationCode, seatType);
			Integer bookingId = addBooking(booking);
			FlightBooking flightBooking = new FlightBooking(flight.getId(),
					bookingId);
			Passenger passenger = new Passenger(0, bookingId,
					user.getFirstName(), user.getLastName(), LocalDate.now(),
					"", "");
			BookingPayment payment = new BookingPayment(bookingId,
					Integer.valueOf(rand.nextInt()).toString(), false);
			BookingBaseUser bookingUser = null;
			switch (userType) {
				case BookingBaseUser.USER :
					bookingUser = new BookingUser(bookingId, user.getId());
					break;
				case BookingBaseUser.AGENT :
					bookingUser = new BookingAgent(bookingId, user.getId());
					break;
				case BookingBaseUser.GUEST :
					bookingUser = new BookingGuest(bookingId, "", "");
					break;
			}

			switch (seatType) {
				case Flight.FIRST :
					flight.setReservedFirstSeats(
							flight.getReservedFirstSeats() + 1);
					break;
				case Flight.BUSINESS :
					flight.setReservedBusinessSeats(
							flight.getReservedBusinessSeats() + 1);
					break;
				case Flight.ECONOMY :
					flight.setReservedEconomySeats(
							flight.getReservedEconomySeats() + 1);
					break;
			}

			addBookingUser(bookingUser, userType);
			addBookingPayment(payment);
			addFlightBooking(flightBooking);
			addPassenger(passenger);
			updateFlight(flight);
			conn.commit();
			System.out.println("Flight Booked!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("Error booking flight. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void cancelBooking(User user, Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			FlightDAO flightDAO = new FlightDAO(conn, "flight");

			Booking booking = bookingDAO.getBookingFromUserFlight(user, flight);
			switch (booking.getSeatType()) {
				case Flight.FIRST :
					flight.setReservedFirstSeats(
							flight.getReservedFirstSeats() - 1);
					break;
				case Flight.BUSINESS :
					flight.setReservedBusinessSeats(
							flight.getReservedBusinessSeats() - 1);
					break;
				case Flight.ECONOMY :
					flight.setReservedEconomySeats(
							flight.getReservedEconomySeats() - 1);
					break;
			}

			booking.setActive(false);

			bookingDAO.updateData(booking);
			flightDAO.updateData(flight);
			conn.commit();
			System.out.println("Flight canceled.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out
					.println("Error canceling flight. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			airportDAO.deleteData(airport);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error deleting airport data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			bookingDAO.deleteData(booking);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error deleting booking data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			flightDAO.deleteData(flight);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error deleting flight data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void deletePassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			passengerDAO.deleteData(passenger);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error deleting passenger data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO userDAO = new UserDAO(conn, "user");
			userDAO.deleteData(user);
			conn.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println(
					"Error deleting user data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	private void fillAirplaneData(Airplane plane, AirplaneDAO airplaneDAO,
			AirplaneTypeDAO AirplaneTypeDAO)
			throws ClassNotFoundException, SQLException {
		AirplaneType airplaneType = AirplaneTypeDAO
				.getAirplaneTypeById(plane.getTypeId());
		plane.setAirplaneType(airplaneType);
	}

	private void fillBookingData(Booking booking, PassengerDAO passengerDAO)
			throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = getPassngersFromBooking(booking);
		booking.setPassengers(passengers);
	}

	private void fillFlightData(Flight flight, RouteDAO routeDOA,
			AirplaneDAO airplaneDAO, AirplaneTypeDAO AirplaneTypeDAO,
			AirportDAO airportDAO) throws ClassNotFoundException, SQLException {
		Airplane plane = airplaneDAO.getAirplaneById(flight.getAirplaneId());
		Route route = routeDOA.getRouteFromId(flight.getRouteId());
		fillRouteData(route, airportDAO);
		fillAirplaneData(plane, airplaneDAO, AirplaneTypeDAO);
		flight.setRoute(route);
		flight.setAirplane(plane);
	}

	private void fillRouteData(Route route, AirportDAO airportDAO)
			throws ClassNotFoundException, SQLException {
		Airport origin = airportDAO.getAirportFromCode(route.getOriginIataId());
		Airport destination = airportDAO
				.getAirportFromCode(route.getDestinationIataId());
		route.setOrigin(origin);
		route.setDestination(destination);
	}

	public Airport getAirportFromCode(String id)
			throws SQLException, IndexOutOfBoundsException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			Airport airport = airportDAO.getAirportFromCode(id);;

			return airport;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Booking> getAllActiveBookings() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			List<Booking> bookings = bookingDAO.getAllActiveBookings();
			bookings.stream().forEach(n -> {
				try {
					fillBookingData(n, passengerDAO);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			return bookings;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Airplane> getAllAirplanes() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirplaneDAO airplaneDAO = new AirplaneDAO(conn, "airplane");
			AirplaneTypeDAO AirplaneTypeDAO = new AirplaneTypeDAO(conn,
					"airplane_type");

			List<Airplane> airplanes = airplaneDAO.getAllData();
			airplanes.forEach(n -> {
				try {
					fillAirplaneData(n, airplaneDAO, AirplaneTypeDAO);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			});

			return airplanes;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Airport> getAllAirports() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			List<Airport> airports = airportDAO.getAllData();
			conn.commit();
			return airports;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Booking> getAllBookings() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			List<Booking> activeBookings = bookingDAO.getAllData();
			activeBookings.stream().forEach(n -> {
				try {
					fillBookingData(n, passengerDAO);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			return activeBookings;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Booking> getAllCanceledBookings() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			List<Booking> canceledBookings = bookingDAO
					.getAllCanceledBookings();
			canceledBookings.stream().forEach(n -> {
				try {
					fillBookingData(n, passengerDAO);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			return canceledBookings;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Flight> getAllFlights() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			AirplaneDAO airplaneDAO = new AirplaneDAO(conn, "airplane");
			AirplaneTypeDAO AirplaneTypeDAO = new AirplaneTypeDAO(conn,
					"airplane_type");
			AirportDAO airportDAO = new AirportDAO(conn, "airport");

			List<Flight> flights = flightDAO.getAllData();
			flights.stream().forEach(n -> {
				try {
					fillFlightData(n, routeDOA, airplaneDAO, AirplaneTypeDAO,
							airportDAO);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			});
			return flights;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<User> getAllUserByRoleId(Integer roleId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO userDAO = new UserDAO(conn, "user");
			List<User> users = userDAO.getUsersFromRole(roleId);
			return users;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Flight> getBookedFlights(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			AirplaneDAO airplaneDAO = new AirplaneDAO(conn, "airplane");
			AirplaneTypeDAO AirplaneTypeDAO = new AirplaneTypeDAO(conn,
					"airplane_type");
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			List<Flight> bookedFlights = flightDAO.getBookedFlights(user);
			bookedFlights.stream().forEach(n -> {
				try {
					fillFlightData(n, routeDOA, airplaneDAO, AirplaneTypeDAO,
							airportDAO);
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(
							"Error retrieving flight data. Contact Administrator.");
					e.printStackTrace();
				}
			});
			return bookedFlights;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(
					"Error adding booking payment data. Contact Administrator.");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Booking getBookingFromId(Integer id) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			Booking booking = bookingDAO.getBookingFromId(id);
			fillBookingData(booking, passengerDAO);
			return booking;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Flight getFlight(Integer id) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			AirplaneDAO airplaneDAO = new AirplaneDAO(conn, "airplane");
			AirplaneTypeDAO AirplaneTypeDAO = new AirplaneTypeDAO(conn,
					"airplane_type");
			AirportDAO airportDAO = new AirportDAO(conn, "airport");

			Flight flight = flightDAO.getFlightById(id);
			fillFlightData(flight, routeDOA, airplaneDAO, AirplaneTypeDAO,
					airportDAO);

			return flight;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Passenger getPassengerFromId(Integer id)
			throws SQLException, IndexOutOfBoundsException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			Passenger passenger = passengerDAO.getUserFromId(id);;

			return passenger;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private List<Passenger> getPassngersFromBooking(Booking booking)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			List<Passenger> passengers = passengerDAO
					.getPassngersFromBooking(booking);;

			return passengers;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Route getRouteFromId(Integer id) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			AirportDAO airportDAO = new AirportDAO(conn, "airport");

			Route route = routeDOA.getRouteFromId(id);
			fillRouteData(route, airportDAO);
			return route;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Route getRouteFromOrgDest(String org, String dest)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			AirportDAO airportDAO = new AirportDAO(conn, "airport");

			Route route = routeDOA.getRouteFromOriDest(org, dest);
			fillRouteData(route, airportDAO);
			return route;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public User getUserFromId(Integer id)
			throws SQLException, IndexOutOfBoundsException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO userDAO = new UserDAO(conn, "user");
			User user = userDAO.getUserFromId(id);;

			return user;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public User getUserFromUserAndPass(String username, String password)
			throws SQLException, IndexOutOfBoundsException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO userDAO = new UserDAO(conn, "user");
			User user = userDAO.getUserFromUserAndPass(username, password);;

			return user;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateAirplaneType(AirplaneType type) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirplaneTypeDAO AirplaneTypeDAO = new AirplaneTypeDAO(conn,
					"airplane_type");
			AirplaneTypeDAO.updateData(type);
			conn.commit();
			System.out.println("Airplane type Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error updating flight. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			airportDAO.updateData(airport);
			conn.commit();
			System.out.println("Airport Updated!");
		} catch (ClassNotFoundException e) {
			System.out
					.println("Error updating airport. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateAirportFromOldIata(Airport airport, String oldIata)
			throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO airportDAO = new AirportDAO(conn, "airport");
			airportDAO.updateAirportFromOldIATA(airport, oldIata);
			conn.commit();
			System.out.println("Airport Updated!");
		} catch (ClassNotFoundException e) {
			System.out
					.println("Error updating airport. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bookingDAO = new BookingDAO(conn, "booking");
			bookingDAO.updateData(booking);
			conn.commit();
			System.out.println("Booking Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error Booking flight. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO flightDAO = new FlightDAO(conn, "flight");
			flightDAO.updateData(flight);
			conn.commit();
			System.out.println("Flight Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error updating flight. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updatePassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PassengerDAO passengerDAO = new PassengerDAO(conn, "passenger");
			passengerDAO.updateData(passenger);
			conn.commit();
			System.out.println("Passenger Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println(
					"Error updating passenger. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO routeDOA = new RouteDAO(conn, "route");
			routeDOA.updateData(route);
			conn.commit();
			System.out.println("Route Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error updating flight. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO UserDAO = new UserDAO(conn, "user");
			UserDAO.updateData(user);
			conn.commit();
			System.out.println("User Updated!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error updating user. Contact Administrator.");
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
