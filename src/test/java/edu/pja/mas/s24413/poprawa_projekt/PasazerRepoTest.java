package edu.pja.mas.s24413.poprawa_projekt;
import edu.pja.mas.s24413.poprawa_projekt.model.*;
import edu.pja.mas.s24413.poprawa_projekt.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PasazerRepoTest {


    private Pasazer pasazer;
    private Rezerwacja rezerwacja;

    @BeforeEach
    void setUp() {
        rezerwacja = Rezerwacja.builder()
                .reservation_date(LocalDate.of(2023, 8, 31))
                .ticketClass("Economy")
                .status("Confirmed")
                .seats(1)
                .build();

        pasazer = Pasazer.builder()
                .id(1)
                .luggage(20.0)
                .book(rezerwacja)
                .build();
    }

    @Test
    void testZmienDateRezerwacji() {
        LocalDate nowaData = LocalDate.of(2023, 9, 10);

        // Zmieniamy datę rezerwacji
        pasazer.zmienDateRezerwacji(nowaData);

        // Sprawdzamy, czy data rezerwacji została poprawnie zmieniona
        assertEquals(nowaData, pasazer.getBook().getReservation_date(),
                "Data rezerwacji powinna zostać zmieniona na nową datę.");
    }

    @Test
    void testZmienDateRezerwacjiGdyBrakRezerwacji() {
        pasazer.setBook(null);  // Ustawiamy brak rezerwacji

        LocalDate nowaData = LocalDate.of(2023, 9, 10);

        // Wywołujemy metodę zmiany daty rezerwacji
        pasazer.zmienDateRezerwacji(nowaData);

        // Sprawdzamy, czy brak rezerwacji został obsłużony
        assertNull(pasazer.getBook(),
                "Jeśli rezerwacja jest null, zmiana daty nie powinna mieć wpływu.");
    }
}
