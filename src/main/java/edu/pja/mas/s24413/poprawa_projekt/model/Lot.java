package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Numer lotu nie moze byc pusty.")
    @Column(unique = true, nullable = false)
    private String flight_number;

    @NotNull(message = "Data odlotu nie moze byc pusta.")
    private LocalDate departure_date;

    @NotNull(message = "Data przylotu nie moze byc pusta.")
    private LocalDate arrival_date;

    @ManyToOne
    @JoinColumn(name = "lotnisko_wylot_id", nullable = false)
    private Lotnisko departureAirport;

    @ManyToOne
    @JoinColumn(name = "lotnisko_Przylot_id", nullable = false)
    private Lotnisko arrivalAirport;

    @OneToMany(mappedBy = "hasIt", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rezerwacja> reservations = new HashSet<>();

    private int totalSeats;
}
