package edu.pja.mas.s24413.poprawa_projekt;

import edu.pja.mas.s24413.poprawa_projekt.model.PojazdZasilanyElektrycznie;
import edu.pja.mas.s24413.poprawa_projekt.model.PojazdZasilanyPaliwem;
import edu.pja.mas.s24413.poprawa_projekt.model.Samolot;
import edu.pja.mas.s24413.poprawa_projekt.repo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PojazdRepoTest {

    @Autowired
    private PojazdRepo pojazdRepo;
    @Autowired
    private PojazdZasilanyElektrycznieRepo pojazdZasilanyElektrycznieRepo;
    @Autowired
    private PojazdZasilanyPaliwemRepo pojazdZasilanyPaliwemRepo;
    @Autowired
    private SamolotRepo samolotRepo;

    @PersistenceContext
            private EntityManager em;


    Samolot s1, s2, s3;
    PojazdZasilanyPaliwem pzp1, pzp2, pzp3;
    PojazdZasilanyElektrycznie pze1, pze2, pze3;

    @BeforeEach
    public void initData() {
        s1 = Samolot.builder()
                .name("aaa")
                .production_year(1990)
                .plane_type("BOEING")
                .max_flight_range(500)
                .plane_fuel_cost(10.4)
                .build();
        s2 = Samolot.builder()
                .name("bbb")
                .production_year(2000)
                .plane_type("JET")
                .max_flight_range(750)
                .plane_fuel_cost(10.4)
                .build();
        s3 = Samolot.builder()
                .name("ccc")
                .production_year(2010)
                .plane_type("AVIONET")
                .max_flight_range(1000)
                .plane_fuel_cost(10.4)
                .build();

        pze1 = PojazdZasilanyElektrycznie.builder()
                .name("pze1")
                .production_year(2006)
                .Batery_life(20)
                .electricity_cost(3)
                .build();
        pze2 = PojazdZasilanyElektrycznie.builder()
                .name("pze2")
                .production_year(2000)
                .Batery_life(80)
                .electricity_cost(3)
                .build();
        pze3 = PojazdZasilanyElektrycznie.builder()
                .name("pze3")
                .production_year(1999)
                .Batery_life(100)
                .electricity_cost(3)
                .build();
        pzp1 = PojazdZasilanyPaliwem.builder()
                .name("pzp1")
                .production_year(2000)
                .capacity(45)
                .fuel_type("PETROL")
                .fuel_cost(5.5)
                .build();
        pzp2 = PojazdZasilanyPaliwem.builder()
                .name("pzp2")
                .production_year(1980)
                .capacity(50)
                .fuel_type("LPG")
                .fuel_cost(2.6)
                .build();
        pzp3 = PojazdZasilanyPaliwem.builder()
                .name("pzp3")
                .production_year(2010)
                .capacity(40)
                .fuel_type("DIESEL")
                .fuel_cost(6.5)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(pojazdRepo);
        assertNotNull(samolotRepo);
        assertNotNull(pojazdZasilanyElektrycznieRepo);
        assertNotNull(pojazdZasilanyPaliwemRepo);
    }

    @Test
    public void testSaveAll() {
        samolotRepo.saveAll(Arrays.asList(s1,s2,s3));
        pojazdZasilanyElektrycznieRepo.saveAll(Arrays.asList(pze1,pze2,pze3));
        pojazdZasilanyPaliwemRepo.saveAll(Arrays.asList(pzp1,pzp2,pzp3));
        em.flush();
        assertEquals(9, pojazdRepo.count());
    }

}
