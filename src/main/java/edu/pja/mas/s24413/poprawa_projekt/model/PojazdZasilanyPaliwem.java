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
public class PojazdZasilanyPaliwem extends Pojazd{

    @Min(0)
    @Max(1000)
    private Integer capacity;

    @NotBlank(message = "Typ paliwa nie moze byc pusty.")
    private String fuel_type;

    @Min(1)
    private Double fuel_cost;

    private Double cost;

    @PrePersist
    @PreUpdate
    private void calculateOperationCost(){
        ObliczKosztOperacyjny();
    }

    @Override
    public double ObliczKosztOperacyjny() {
        if (fuel_cost != null && capacity != null) {
            this.cost = switch (fuel_type){
                case "PETROL" -> fuel_cost * capacity *1.5;
                case "LPG" -> fuel_cost * capacity * 0.45;
                case "DIESEL" -> fuel_cost * capacity * 1.4;
                default -> fuel_cost * capacity;
            };
        } else {
            this.cost = 0.0;
        }
        return cost;
    }
}
