package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Pojazd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Nazwa nie moze byc pusta.")
    @Size(min = 1, max = 50, message = "Musi zmiescic się w przedziale od 1 do 50.")
    private String name;

    @Min(value = 1940, message = "Rok nie moze byc mniejszy niz 1990.")
    private int production_year;

    public abstract double ObliczKosztOperacyjny();

    @ManyToOne
    @JoinColumn(name = "lotnisko_id")
    private Lotnisko hasIt;
}
