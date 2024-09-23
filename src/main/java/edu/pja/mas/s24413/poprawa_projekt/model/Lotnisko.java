package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Lotnisko {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Nazwa nie moze byc pusta.")
    @Size(min = 3, max = 255, message = "Nazwa musi miescic sie w przedziale od 3 do 255.")
    private String name;

    @NotBlank(message = "Lokalizacja nie moze byc pusta.")
    @Size(min = 3, max = 255, message = "Lokalizacja musi miescic sie w przedziale od 3 do 255.")
    private String location;

    @Min(10)
    @Max(100000)
    private int area; // Wartosc podana w km.

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Terminal> terminals = new HashSet<>();

    @OneToMany(mappedBy = "hasIt", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pojazd> vehicles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "osobaa_id")
    private Osoba isLocated;

    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Lot> flightsDeparture = new HashSet<>();

    @OneToMany(mappedBy = "arrivalAirport", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Lot> flightsArrive = new HashSet<>();
}
