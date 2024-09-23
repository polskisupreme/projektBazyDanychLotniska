package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Lot;
import edu.pja.mas.s24413.poprawa_projekt.model.Rezerwacja;
import edu.pja.mas.s24413.poprawa_projekt.repo.RezerwacjaRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * TicketClassController is a JavaFX controller responsible for managing the selection of ticket classes.
 * It allows users to choose between different ticket classes (Business, Normal, VIP) and saves the selection for the reservation.
 */
@Component
@RequiredArgsConstructor
public class TicketClassController implements Initializable {

    // The reservation object associated with the current booking process.
    @Setter
    private Rezerwacja rezerwacja;

    // The flight selected by the user, for which a ticket class is being chosen.
    @Setter
    private Lot selectedFlight;

    // The seat selected by the user for the current reservation.
    @Setter
    private String selectedSeat;

    // All buttons that uses in the scene.
    @FXML
    private Button businessButton, normalButton, vipButton, backButton, saveButton;

    // The ticket class selected by the user.
    private String selectedTicketClass;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    // Example UI component that might display flight details (not fully implemented in the snippet).
    @FXML
    private Label flightDetailsLabel;

    /**
     * Handles the selection of the Business class ticket.
     * It sets the selected ticket class to "Business" and updates the reservation with this choice.
     */
    @FXML
    private void handleBusinessClassSelection() {
        selectedTicketClass = "Business";

        if(rezerwacja != null) rezerwacja.setTicketClass(selectedTicketClass);
    }

    /**
     * Handles the selection of the Normal class ticket.
     * It sets the selected ticket class to "Normal" and updates the reservation with this choice.
     */
    @FXML
    private void handleNormalClassSelection() {
        selectedTicketClass = "Normal";

        if(rezerwacja != null) rezerwacja.setTicketClass(selectedTicketClass);
    }

    /**
     * Handles the selection of the VIP class ticket.
     * It sets the selected ticket class to "VIP" and updates the reservation with this choice.
     */
    @FXML
    private void handleVIPClassSelection() {
        selectedTicketClass = "VIP";

        if(rezerwacja != null) rezerwacja.setTicketClass(selectedTicketClass);
    }

    @FXML
    private void handleSaveButtonAction() {
        // Assuming selectedTicketClass is already set somewhere in the controller
        System.out.println("Selected ticket class: " + selectedTicketClass);

        if (selectedTicketClass != null) {
            // Save the selected ticket class (e.g., to a variable or database)
            saveSelectedTicketClass(selectedTicketClass);

            // Create a Rezerwacja object and set its properties
            Rezerwacja rezerwacja = createRezerwacja(selectedTicketClass);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Hello.fxml"));
                loader.setControllerFactory(springContext::getBean);
                Parent root = loader.load();

                // Optional: Pass the Rezerwacja object to the next controller
                HelloController helloController = loader.getController();
                helloController.setRezerwacja(rezerwacja); // Assuming HelloController has this method

                Scene scene = new Scene(root, 800, 600);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No ticket class selected. Please select a ticket class before saving.");
        }
    }

    /**
     * Handles the action of the "Back" button.
     * It loads the previous scene (likely the reservation screen) and switches to it,
     * allowing the user to return to the previous view.
     */
    @FXML
    private void handleBackButtonAction3() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Seats.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdza, czy etykieta (Label) do wyświetlania szczegółów lotu oraz wybrany lot nie są null.
     * Jeśli nie są, ustawia na etykiecie szczegóły lotu, takie jak numer lotu, data odlotu, data przylotu oraz miejsce docelowe.
     */
    public void setSelectedFlight(Lot selectedFlight) {

        // Assuming you have a Label in your FXML to display flight details
        if (flightDetailsLabel != null && selectedFlight != null) {
            flightDetailsLabel.setText("Flight Number: " + selectedFlight.getFlight_number()
                    + "\nDeparture: " + selectedFlight.getDeparture_date()
                    + "\nArrival: " + selectedFlight.getArrival_date()
                    + "\nDestination: " + selectedFlight.getArrivalAirport());
        }
    }

    private void saveSelectedTicketClass(String selectedTicketClass) {
        this.selectedTicketClass = selectedTicketClass;
    }

    private Rezerwacja createRezerwacja(String selectedTicketClass) {
        // Initialize the Rezerwacja object
        Rezerwacja rezerwacja = new Rezerwacja();

        if (selectedTicketClass != null) {
            // Split the selectedTicketClass on the delimiter "|"
            String[] parts = selectedTicketClass.split("\\|");

            // Ensure there are enough parts before accessing them
            if (parts.length > 1) {
                String seatString = parts[1].trim(); // Extract the "Seat: 7" part
                int seatNumber = Integer.parseInt(seatString.replace("Seat: ", "").trim()); // Extract the numeric part
                rezerwacja.setSeats(seatNumber); // Set the extracted seat number
                rezerwacja.setTicketClass(parts[0].trim()); // Set the ticket class without seat info
            } else {
                // Handle the case where the string does not have the expected format
//                System.out.println("The selected ticket class string is not in the expected format: " + selectedTicketClass);
                // You can throw an exception or set default values if needed
                rezerwacja.setTicketClass(selectedTicketClass.trim()); // Just set the whole string as the ticket class
            }
        } else {
            System.out.println("No ticket class selected.");
        }

        // Set other properties of Rezerwacja if needed
        // rezerwacja.setFlight(selectedFlight);

        // Save or process the Rezerwacja object as needed
        // rezerwacjaRepo.save(rezerwacja); // Example if saving to a repository

        return rezerwacja;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic can be added here, such as loading flight details into the UI.
    }
}
