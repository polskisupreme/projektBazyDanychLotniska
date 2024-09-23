package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pasazer extends Osoba{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Min(0)
    @Max(40)
    private Double luggage;

    @ManyToOne
    @JoinColumn(name = "rezerwacja_id")
    private Rezerwacja book;

    public void zmienDateRezerwacji(LocalDate nowa_data) {
        if (this.book != null) this.book.setReservation_date(nowa_data);
        else System.out.println("Brak powiązanej rezerwacji do zmiany daty.");
    }

    public String wyswietlRezerwacje() {
        if (this.book != null) {
            return String.format(
                    "Rezerwacja ID: %d\nKlasa biletu: %s\nData rezerwacji: %s\nStatus: %s\nMiejsca: %d",
                    this.book.getId(),
                    this.book.getTicketClass(),
                    this.book.getReservation_date().toString(),
                    this.book.getStatus(),
                    this.book.getSeats()
            );
        } else {
            return "Brak powiązanej rezerwacji.";
        }
    }

//    @ManyToOne
//    @JoinColumn(name = "osoba_id", nullable = false, updatable = false)
//    private Osoba is;
}
