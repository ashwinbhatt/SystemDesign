Concurrent Movie Ticket Booking System

Design and implement a thread-safe movie ticket booking system. This system should efficiently manage the booking of movie tickets in a multiplex cinema that has multiple screens, ensuring that no more tickets are sold than the available seats in each screening, even in the face of concurrent booking requests.

The primary challenge is to handle concurrent bookings in such a way that avoids race conditions, ensuring the integrity of the booking process. This includes preventing the sale of the same seat to multiple customers and accurately reflecting the current availability of seats.

Example Scenario:

    The system is initialized with a list of movies, each having multiple screenings throughout the day. Each screening has 100 available seats.
    Customer A and Customer B simultaneously attempt to book 4 tickets each for the same screening of Movie X at 7:00 PM.
    The system processes both requests. If there are at least 8 seats available, both bookings should succeed. If there are fewer than 8 seats available, one booking should succeed, and the other should be declined based on the system's concurrency handling logic.
    After the bookings are processed, the seat availability for the 7:00 PM screening of Movie X should reflect the updated number of booked seats.


