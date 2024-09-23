package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "imie nie moze byc puste.")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Nazwisko nie moze byc puste.")
    @Size(min = 2, max = 255)
    private String surname;

    @NotBlank(message = "Pesel nie moze byc pusty.")
    @Column(unique = true, length = 11)
    @Size(min = 11, max = 11, message = "Pesel musi skladac sie z 11 cyfr.")
    private String PESEL;

    @NotNull(message = "Dtaa urodzenia nie moze byc pusta.")
    private LocalDate birth_date;

    private int wiek;

    @PrePersist
    @PreUpdate
    private void calculateAge(){
        this.wiek = obliczWiek();
    }

    public int obliczWiek(){ return Period.between(birth_date, LocalDate.now()).getYears();}

    @OneToMany(mappedBy = "isLocated", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Lotnisko> airports = new HashSet<>();



//    @OneToMany(mappedBy = "is", cascade = CascadeType.REMOVE)
//    @Builder.Default
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<Pasazer> passangers = new HashSet<>();
//
//    @OneToMany(mappedBy = "worksIn", cascade = CascadeType.REMOVE)
//    @Builder.Default
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<Pracownik> workers = new HashSet<>();
}
