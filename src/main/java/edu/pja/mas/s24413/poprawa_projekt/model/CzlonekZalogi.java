package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CzlonekZalogi extends Pracownik {


    @NotBlank(message = "Stanowisko nie moze byc puste.")
    @Size(min = 1, max = 50, message = "Nazwa stanowiska musi znajdowac sie w przedziale od 1 do 50.")
    private String stanowisko;

//    @ManyToOne
//    @JoinColumn(name = "pracownik_id", nullable = false, updatable = false)
//    private Pracownik crewMember;

    public Pilot ZamienNaPilota() {
        Pilot pilot = Pilot.builder()
                .name(this.getName())
                .surname(this.getSurname())
                .hours_worked(this.getHours_worked())
                .paid(this.getPaid())
                .certificate(new HashSet<>(Arrays.asList("BLE", "ALG")))  // You might want to pass existing certificates if applicable
                .build();
        return pilot;
    }
}
