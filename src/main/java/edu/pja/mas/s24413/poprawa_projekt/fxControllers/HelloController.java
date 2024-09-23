package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Rezerwacja;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The HelloController class manages the initial screen of the application.
 * It provides options for users to quit the application, start a new flight reservation,
 * or view existing reservations.
 */
@Component
@RequiredArgsConstructor
public class HelloController implements Initializable {

    // Button to quit the application.
    @FXML
    private Button quitButton;

    // Button to start the flight reservation process.
    @FXML
    private Button reserveButton;

    // Current reservation object, if any.
    @Setter
    private Rezerwacja rezerwacja;

    // Button to view existing reservations.
    @FXML
    private Button reservationButton;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    /**
     * Handles the action when the "Reserve" button is clicked.
     * It loads the flight reservation screen and displays it.
     */
    public void handleReserveButtonAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReserveFlight.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) reserveButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Reservation" button is clicked.
     * It loads the screen for viewing existing reservations and displays it.
     */
    public void handleReservationButtonAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Reservation.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) reservationButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "QUIT" button is clicked.
     * It close the screen.
     */
    public void handleQuitButtonAction(){
        // Get the stage from any UI component which is already injected, e.g., a button
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close(); // Close the current window
    }

    /**
     * Initializes the controller after the root element has been completely processed.
     * Currently, this method does not contain any specific initialization logic.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // No initialization needed as of now
    }
}

