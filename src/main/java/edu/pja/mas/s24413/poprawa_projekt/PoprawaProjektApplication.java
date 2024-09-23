package edu.pja.mas.s24413.poprawa_projekt;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A main application class. You start here
 */

@SpringBootApplication
public class PoprawaProjektApplication {

    public static void main(String[] args) {
        //SpringApplication.run(PoprawaProjektApplication.class, args);
        Application.launch(ApplicationFXGUI.class, args);
    }

}
