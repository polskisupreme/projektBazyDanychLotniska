package edu.pja.mas.s24413.poprawa_projekt.fxControllers;

import edu.pja.mas.s24413.poprawa_projekt.model.Pasazer;
import edu.pja.mas.s24413.poprawa_projekt.model.Rezerwacja;
import edu.pja.mas.s24413.poprawa_projekt.repo.RezerwacjaRepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javafx.scene.control.Button;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * ReservationListController is a JavaFX controller responsible for displaying a list of reservations.
 * It allows users to view their reservations and provides functionality to navigate back to the main screen.
 */
@Component
@RequiredArgsConstructor
public class ReservationListController implements Initializable {

    // The ListView element that displays a list of reservations.
    @FXML
    private ListView<Rezerwacja> reservationList;

    // Repository for accessing reservation data from the database.
    private final RezerwacjaRepo rezerwacjaRepo;

    // Button that allows the user to navigate back to the main screen.
    @FXML
    private Button backButton;

    // Spring application context for managing beans and dependencies.
    private final ApplicationContext springContext;

    /**
     * Handles the action of the "Back" button.
     * It loads the main screen and switches to it, allowing the user to return to the previous view.
     */
    public void handleBackButtonAction(){
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
     * Initializes the controller after the root element has been completely processed.
     * This method fetches all reservations from the repository and populates the ListView with them.
     * It also customizes the appearance of each reservation in the list.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all reservations from the database
        List<Rezerwacja> reservations = (List<Rezerwacja>) rezerwacjaRepo.findAll();
        reservationList.getItems().clear(); // Clear existing items

        // Customize the appearance of each reservation in the list
        reservationList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Rezerwacja> call(ListView<Rezerwacja> listView) {
                return new ListCell<Rezerwacja>() {
                    @Override
                    protected void updateItem(Rezerwacja reservation, boolean empty) {
                        super.updateItem(reservation, empty);
                        if (empty || reservation == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            VBox reservationBox = new VBox();
                            reservationBox.getStyleClass().add("vbox-reservation"); // Klasa CSS dla VBox

                            Text whereText = new Text("Where\n" + reservation.getHasIt().getDepartureAirport().getName());
                            whereText.getStyleClass().add("text-common"); // Klasa CSS dla tekstu

                            Text fromText = new Text("From\n" + reservation.getHasIt().getArrivalAirport().getName());
                            fromText.getStyleClass().add("text-common"); // Klasa CSS dla tekstu

                            Text whenText = new Text("When\n" + reservation.getReservation_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                            whenText.getStyleClass().add("text-common"); // Klasa CSS dla tekstu

                            Text seatText = new Text("Seat\n" + reservation.getSeats());
                            seatText.getStyleClass().add("text-common"); // Klasa CSS dla tekstu

                            Text classText = new Text("Class\n" + reservation.getTicketClass());
                            classText.getStyleClass().add("text-common"); // Klasa CSS dla tekstu

                            reservationBox.getChildren().addAll(whereText, fromText, whenText, seatText, classText);
                            reservationBox.setSpacing(5);

                            setGraphic(reservationBox);

                            // Add click event to redirect to passenger details
                            setOnMouseClicked(event -> {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PasazerDetails.fxml"));
                                    loader.setControllerFactory(springContext::getBean);
                                    Parent root = loader.load();

                                    // Assuming PasazerDetailsController has a method setPasazer
                                    PasazerDetailsController controller = loader.getController();

                                    // Pobieramy pierwszy element z kolekcji (jeśli Set zawiera tylko jednego pasażera)
                                    Pasazer pasazer = reservation.getPassangers().iterator().next();

                                    controller.setPasazer(pasazer);

                                    Scene scene = new Scene(root, 800, 600);
                                    Stage stage = (Stage) getScene().getWindow();
                                    stage.setScene(scene);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (NoSuchElementException e) {
                                    System.out.println("No passengers found in this reservation.");
                                    e.printStackTrace();
                                }
                            });

                        }
                    }
                };
            }
        });

        // Add reservations to the list
        reservationList.getItems().addAll(reservations);
    }

}
