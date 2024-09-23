package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Lot;
import edu.pja.mas.s24413.poprawa_projekt.model.Rezerwacja;
import edu.pja.mas.s24413.poprawa_projekt.repo.RezerwacjaRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * SeatsListController is a JavaFX controller responsible for displaying available seats for a selected flight.
 * It allows users to select a seat and proceed with the reservation process, or navigate back to the previous screen.
 */
@Component
@RequiredArgsConstructor
public class SeatsListController  implements Initializable {
    // The reservation object associated with the current process.
    @Setter
    private Rezerwacja rezerwacja;

    // Repository for accessing reservation data from the database.
    private RezerwacjaRepo rezerwacjaRepo;

    // The seat selected by the user from the list of available seats.
    private String selectedSeat;

    // The ListView element that displays available seats for the selected flight.
    @FXML
    private ListView<String> flightListView;

    // Button that allows the user to navigate back to the previous screen.
    @FXML
    private Button backButton;

    // Button that allows the user to proceed to the next step of the reservation process.
    @FXML
    private Button continueButton;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    // The flight selected by the user, for which seats are being displayed.
    @Setter
    private Lot selectedFlight;

    /**
     * Handles the action of the "Back" button.
     * It loads the previous screen (e.g., the flight reservation screen) and switches to it,
     * allowing the user to return to the previous step in the reservation process.
     */
    @FXML
    private void handleBackButtonAction2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReserveFlight.fxml"));
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
     * Handles the action of the "Continue" button.
     * It retrieves the selected seat and proceeds to the next screen, where the user can choose the class of the ticket.
     */
    @FXML
    private void handleContinueButtonAction2() {
        // Load the TicketClass.fxml and pass the selected flight and seat
        try {
            selectedSeat = flightListView.getSelectionModel().getSelectedItem();  // Get the selected seat

            if (selectedSeat != null) {

                saveSelectedSeat(selectedSeat);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TicketClass.fxml"));
                loader.setControllerFactory(springContext::getBean);
                Parent root = loader.load();

                System.out.println("Selected Flight: " + selectedSeat);

                // Get the controller and set the selected flight and seat
                TicketClassController ticketClassController = loader.getController();
                ticketClassController.setSelectedFlight(selectedFlight);
                ticketClassController.setSelectedSeat(selectedSeat);  // Assuming this method exists in TicketClassController

                Scene scene = new Scene(root, 800, 600);
                Stage stage = (Stage) continueButton.getScene().getWindow();
                stage.setScene(scene);

            } else {
                // Handle case where no seat is selected
                System.out.println("No seat selected. Please select a seat before continuing.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller after the root element has been completely processed.
     * This method populates the ListView with available seats for the selected flight.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (selectedFlight != null) {
            loadSeats();
        }
    }

    /**
     * Load all seats and iterets on them.
     */
    public void loadSeats() {
        if (selectedFlight != null) {
            flightListView.getItems().clear();
            int totalSeats = selectedFlight.getTotalSeats();

            // Iteruje po wszystkich miejscach i dodaje je do ListView w formacie "Seat: x".
            for (int i = 1; i <= totalSeats; i++) {
                flightListView.getItems().add(" | Seat: " + i);
            }
        }
    }

    public void setSelectedFlight(Lot flight) {
        this.selectedFlight = flight;
        loadSeats(); // Call this to load seats when the flight is set
    }

    private void saveSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }
}
