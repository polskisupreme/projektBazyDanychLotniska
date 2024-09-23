package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pilot extends Pracownik{

    @ElementCollection
    @CollectionTable(name = "pilot_certificate", joinColumns = @JoinColumn(name="pilot_id"))
    @Builder.Default
    private Set<String> certificate = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "samolot_id")
    private Samolot flies;

//    @ManyToOne
//    @JoinColumn(name = "pracownik_id", nullable = false, updatable = false)
//    private Pracownik workPilots;

    public CzlonekZalogi ZamienNaCzlonkaZalogi() {
        CzlonekZalogi czlonekZalogi = CzlonekZalogi.builder()
                .name(this.getName())
                .surname(this.getSurname())
                .hours_worked(this.getHours_worked())
                .paid(this.getPaid())
                .stanowisko("Inzynier") // You might want to determine the role dynamically
                .build();
        return czlonekZalogi;
    }
}
