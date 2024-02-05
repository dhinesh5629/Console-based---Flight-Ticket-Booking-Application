Console based application - Flight Ticket Booking Application (presidio round 2)

Implemented functionalities:
1. New admin creation
2. New user creation
3. User login
4. Admin login

----------------------------------------------------------------------------

Features included:

Users functionality:

1. Users can filter the each flight using flight ID and can do the following functions:
   Book Ticket
   cancel Ticket
   Print Ticket details

----------------------------------------

Admins functionality:

1. Admins can filter each flight individually and view their booking details along with each passenger details from that flight (including total seats available, current ticket price and number of tickets booked)
                                                                                          or
2. Admins can view the complete booking summary of all flights and view their booking details along with each passenger details from that flight (including total seats available, current ticket price and number of tickets booked)

------------------------------------------------------------------------------

Additional features added:

1. Demand-based pricing:
   Adjusting ticket prices in response to changing demand. As each ticket is booked, the pricing algorithm automatically increases the cost of the remaining tickets. This dynamic pricing strategy 
   allows airlines to adapt to fluctuations in demand, optimizing revenue by adjusting ticket prices based on real-time booking activity.
   
1. Multi-User Support:
   Created the ability to register and authenticate multiple users and admins with distinct usernames and passwords (using HashSet).

2. Cancel Tickets Functionality:
   Implemented the functionality for users to cancel their booked tickets.
   Extended cancellation of tickets based on passenger ID and users can cancel their recently booked tickets (if they have multiple bookings)

3. Detailed Booking Information:
   Enhanced the "View Bookings" feature for admins to display detailed passenger information, including passenger ID, number of tickets booked, and total cost.

4. Dynamic Flight Creation:
   Allowed admins to dynamically create new flights, expanding the system's capacity to handle multiple flights.

-------------------------------------------------------------------------------

Bug Fixes and Improvements:

1. Addressed bugs related to ticket cancellation and the display of booking details.
  
3. Improved the application's overall logic and flow for a better user experience with multiple class files and structured every functionalities neatly.

--------------------------------------------------------------------------------

Working:

There are 7 individual classes in the project contributing to each functionalities:

1. TicketBooker : 
   Main class for handling user interaction, authentication, and navigation through features like booking, cancellation, flight search and view bookings.
   
2. Flight : 
   Represents each flight details, bookings, and prices. Provides methods for booking, canceling, and printing flight information.
   
3. PassengerDetails :
   Holds the details of each passenger's booking, including Passenger ID, ticket count, and total cost.
   
4. User :
   Represents each users with an unique username and password for authentication.
   
5. UserManager :
   Manages user-related tasks, such as adding users and authenticating credentials.
   
6. Admin :
   Represents each admins with an unique username and password for authentication.
   
7. AdminManager :
   Manages admin-related tasks, such as creating admins and authenticating admin credentials.

Note: PassengerDetails class is written along with the Flight class, UserManager Class is written along with the User class, AdminManager class is written along with the Admin class.
