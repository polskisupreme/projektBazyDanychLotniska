package edu.pja.mas.s24413.poprawa_projekt.repo;

import edu.pja.mas.s24413.poprawa_projekt.model.Pasazer;
import edu.pja.mas.s24413.poprawa_projekt.model.Rezerwacja;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PasazerRepo extends CrudRepository<Pasazer, Long> {

    List<Rezerwacja> findRezerwacjaById(int rezerwacja_id);
}
