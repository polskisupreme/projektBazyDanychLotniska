package edu.pja.mas.s24413.poprawa_projekt.model;

import edu.pja.mas.s24413.poprawa_projekt.model.Osoba;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Pracownik extends Osoba {

    @Min(1)
    private Integer hours_worked;

    @Min(1)
    private Integer paid;

    @Min(1)
    @NotNull(message = "Stawka nie moze byc pusta.")
    private Double stawka;

    @PreUpdate
    @PrePersist // Add this to set stawka before both updates and inserts
    public void ObliczStawke() {
        if(paid != null && hours_worked != null) {
            this.stawka = (double) (hours_worked * paid);
        } else {
            this.stawka = 20.0; // Default value in case hours_worked or paid are not set
        }
    }
}
