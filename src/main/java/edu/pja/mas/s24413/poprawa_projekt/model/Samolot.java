package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Samolot extends Pojazd{

    @NotBlank(message = "Typ samolotu nie moze byc pusty.")
    private String plane_type;

    @Max(4000)
    private Integer max_flight_range;

    @Min(0)
    private Double plane_fuel_cost;

    private Double cost;

    @OneToMany(mappedBy = "flies", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pilot> pilots = new HashSet<>();

    @PrePersist
    @PreUpdate
    private void calculateOperationCost(){
        ObliczKosztOperacyjny();
    }

    @Override
    public double ObliczKosztOperacyjny() {
        if (max_flight_range != null && plane_fuel_cost != null) {
            this.cost = switch (plane_type){
                case "AVIONET" -> plane_fuel_cost * max_flight_range;
                case "BOEING" -> plane_fuel_cost * max_flight_range * 2;
                case "JET" -> plane_fuel_cost * max_flight_range * 5.65;
                default -> plane_fuel_cost * max_flight_range;
            };
        } else {
            this.cost = 0.0;
        }
        return cost;
    }
}
