package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Pasazer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import java.io.IOException;

/**
 * PasazerDetailsController is a JavaFX controller responsible for displaying
 * passenger details and handling user interactions related to these details.
 * It allows users to view the passenger's surname, first name, and passport number.
 * Additionally, it provides functionality to navigate back to the previous screen.
 */
@Component
@RequiredArgsConstructor
public class PasazerDetailsController {
    // Label for displaying the passenger's surname, name, numerPesel.
    @FXML
    private Label nazwiskoLabel, imieLabel, numerPeselLabel;

    // The passenger object whose details are being displayed.
    private Pasazer pasazer;

    // Button that allows the user to navigate back to the previous screen.
    @FXML
    private Button backButton;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    /**
     * Sets the passenger object to be displayed and updates the view with the passenger's details.
     *
     * @param pasazer the passenger object to be displayed
     */
    public void setPasazer(Pasazer pasazer) {
        this.pasazer = pasazer;
        updateView();
    }

    /**
     * Updates the view with the details of the current passenger.
     * If the passenger object is not null, it updates the labels with the passenger's surname, first name, and passport number.
     */
    private void updateView() {
        if (pasazer != null) {
            nazwiskoLabel.setText(pasazer.getSurname());
            imieLabel.setText(pasazer.getName());
            numerPeselLabel.setText(pasazer.getPESEL());
        }
    }

    /**
     * Handles the action of the "Back" button.
     * It loads the previous scene (likely the reservation screen) and switches to it,
     * allowing the user to return to the previous view.
     */
    public void handleBackButtonAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Reservation.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
