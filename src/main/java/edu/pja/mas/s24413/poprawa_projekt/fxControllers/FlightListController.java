package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Lot;
import edu.pja.mas.s24413.poprawa_projekt.repo.LotRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * The FlightListController class is a JavaFX controller responsible for managing the flight list view.
 * It handles user interactions with the flight list, including navigation and selection of flights.
 */
@Component
@RequiredArgsConstructor
public class FlightListController implements Initializable {

    // A reference to the ListView in the UI that displays flight information.
    @FXML
    private ListView<String> flightListView;

    // A repository for accessing flight (Lot) data from the database.
    private final LotRepo lotRepo;

    // References to the UI buttons.
    @FXML
    private Button backButton;
    @FXML
    private Button continueButton;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    /**
     * Handles the action when the "Back" button is clicked.
     * It loads the previous scene (e.g., a main scene) and displays it.
     */
    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Hello.fxml"));
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
     * Handles the action when the "Continue" button is clicked.
     * It retrieves the selected flight from the ListView and processes it.
     * If a flight is selected, further actions can be taken (e.g., navigating to a detailed view).
     */
    @FXML
    private void handleContinueButtonAction() {
        String selected = flightListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Find the selected flight object from the list
            List<Lot> flights = (List<Lot>) lotRepo.findAll();
            Lot selectedFlight = flights.stream()
                    .filter(lot -> selected.contains(lot.getDepartureAirport().getName()))
                    .findFirst()
                    .orElse(null);

            if (selectedFlight != null) {
                System.out.println("Selected Flight: " + selectedFlight);

                saveSelectedFlight(selectedFlight);

                // Load the Seats.fxml and pass the selected flight
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Seats.fxml"));
                    loader.setControllerFactory(springContext::getBean);
                    Parent root = loader.load();

                    // Get the controller and set the selected flight
                    SeatsListController seatsListController = loader.getController();
                    seatsListController.setSelectedFlight(selectedFlight);

                    Scene scene = new Scene(root, 800, 600);
                    Stage stage = (Stage) continueButton.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveSelectedFlight(Lot selectedFlight) {
        lotRepo.save(selectedFlight);
    }

    /**
     * Initializes the controller after the root element has been completely processed.
     * This method is called to initialize the flight list with data from the repository.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Fetch all flights
        List<Lot> flights = (List<Lot>) lotRepo.findAll();
        flightListView.getItems().clear(); // Clear existing items

        // Map each Lot object to a formatted string and add to ListView
        flightListView.getItems().addAll(flights.stream().map(lot ->
                String.format("DEPARTURE: %s,   ARRIVAL: %s,    DATE: %s",
                        lot.getDepartureAirport().getName(),
                        lot.getArrivalAirport().getName(),
                        lot.getDeparture_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                )).toList());
    }
}

