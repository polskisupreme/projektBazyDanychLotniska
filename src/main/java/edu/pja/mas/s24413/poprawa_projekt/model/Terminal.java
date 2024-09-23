package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Nazwa nie moze byc pusta.")
    @Size(min = 1, max = 25, message = "Nazwa musi znajdowac sie w przedziale od 1 do 25.")
    private String name;

    @NotBlank(message = "Dane kontaktowe musza byc zawarte.")
    @Size(min = 1, max = 255, message = "Dane kontaktowe musza znajdowac sie w przedziale od 1 do 255.")
    private String contact_description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "terminal_id", nullable = false, updatable = false)
    private Lotnisko owner;

}
