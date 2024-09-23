package edu.pja.mas.s24413.poprawa_projekt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * A class which creates first FX GUI View. Its invoked via event from
 */
@Component
@RequiredArgsConstructor
public class StageInit implements ApplicationListener<ApplicationFXGUI.StageReadyEvent> {

    @Value("classpath:/fxml/Hello.fxml")
    private Resource helloFxml;

    private final ApplicationContext springContext;

    @Override
    public void onApplicationEvent(ApplicationFXGUI.StageReadyEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(helloFxml.getURL());
            loader.setControllerFactory(controllerClass -> springContext.getBean(controllerClass));
            Parent parent = loader.load();
            Stage stage = event.getStage();

            // Set the background color of the scene
            Scene scene = new Scene(parent, 800, 600);
 //           scene.getRoot().setStyle("-fx-background-color: #d8d1da;"); // Dark blue using the hex color code

            stage.setScene(scene);
            stage.setTitle("WIIZFREE");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
