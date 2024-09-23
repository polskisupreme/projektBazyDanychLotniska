package edu.pja.mas.s24413.poprawa_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rezerwacja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Opis nie moze byc pusty.")
    @Size(min = 1, max = 255, message = "Opis musi miescic sie w przedziale od 1 do 255.")
    private String ticketClass;

    @NotNull(message = "Data rezerweacji nie moze byc pusta.")
    @Column(unique = true, name = "data_rezerwacji")
    private LocalDate reservation_date;

    @Size(min = 1, max = 20)
    private String status;

    @Min(1)
    @Max(100)
    private int seats;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot hasIt;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pasazer> passangers = new HashSet<>();
}
