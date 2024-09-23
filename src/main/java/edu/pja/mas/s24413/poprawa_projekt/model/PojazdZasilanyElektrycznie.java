package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PojazdZasilanyElektrycznie extends Pojazd {

    @Min(0)
    @Max(100)
    private Integer Batery_life;

    @Min(1)
    private Integer electricity_cost;

    private Double cost;

    @PrePersist
    @PreUpdate
    private void calculateOperationCost(){
        ObliczKosztOperacyjny();
    }

    @Override
    public double ObliczKosztOperacyjny() {
        if (Batery_life != null && electricity_cost != null) {
            this.cost = (double) (electricity_cost * Batery_life);
        } else {
            this.cost = 0.0;
        }
        return cost;
    }
}
