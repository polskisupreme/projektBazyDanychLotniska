## 🚀 Flight Reservation Management System
This project is a Flight Reservation Management System developed using Java and Spring Framework. It demonstrates object-oriented programming principles like inheritance, associations between classes, and encapsulation. The system handles different components of a flight reservation application, including flight lists, reservations, passenger details, seat assignments, and ticket classes.

## 📝 Project Features
Flight Management: Manage flight lists and details efficiently.
Reservation System: Handle passenger reservations and manage seating arrangements.
Passenger Information: Display detailed passenger information, including reservation history.
Ticketing System: Manage ticket classes and pricing structures.

## 🧱 Class Structure
1. Inheritance Models:
The project utilizes class inheritance to manage different entities effectively. Common attributes are stored in base classes and inherited by specific entities.

2. Associations:
Associations between classes allow entities like flights, passengers, and reservations to interact, reflecting real-world relationships.

3. Core Classes:
Pracownik: The base class representing employees, with inherited properties for managing working hours and pay.
CzlonekZalogi: Represents a crew member, inheriting from Pracownik, with additional attributes relevant to flight crew.
Pasazer: Represents a passenger in the system, linked to reservations and flight information.

## 🔧 Key Components and Controllers
PoprawaProjektApplication: The main entry point of the application.
StageInit: Initializes various stages of the application interface.
ApplicationFXGUI: Manages the graphical user interface (GUI) of the application using JavaFX.
FlightListController: Manages the interaction and presentation of the flight list.
PasazerDetailsController: Handles the detailed view and management of passenger information.
ReservationListController: Oversees the list of reservations and their interactions.
SeatsListController: Manages seat availability and assignments for different flights.
TicketClassController: Handles ticket class settings and pricing.

##💻 Tech Stack
Language: Java
Framework: Spring Framework
Database: JPA for persistence and database management
Frontend: JavaFX for the graphical interface
Build Tool: Maven

## 📈 How It Works
Data Initialization: The DataInit class is responsible for initializing the system with the necessary data, such as flight schedules, crew members, and reservations.
Graphical Interface: The ApplicationFXGUI and other controllers handle the user interface, allowing users to manage and interact with flights, passengers, and tickets visually.
Business Logic: The business logic related to calculating fares, managing crew members, and handling reservations is integrated into the controllers.

## 🎯 Future Improvements
Add payment gateway integration for processing ticket purchases.
Implement user authentication and role-based access for admins, passengers, and crew members.
Expand the reporting capabilities to generate detailed statistics on flights and reservations.

Clone the repository.
Install dependencies using Maven.
Run the PoprawaProjektApplication.java as the main class.
Explore the various features using the GUI built with JavaFX.
