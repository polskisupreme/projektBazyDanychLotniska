package edu.pja.mas.s24413.poprawa_projekt;

import edu.pja.mas.s24413.poprawa_projekt.model.*;
import edu.pja.mas.s24413.poprawa_projekt.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A class to init data to database
 */

@Component
@RequiredArgsConstructor
public class DataInit {

    private final LotniskoRepo lotniskoRepo;
    private final PasazerRepo pasazerRepo;
    private final LotRepo lotRepo;
    private final PilotRepo pilotRepo;
    private final CzlonekZalogiRepo czlonekZalogiRepo;
    private final PojazdZasilanyElektrycznieRepo pojazdZasilanyElektrycznieRepo;
    private final PojazdZasilanyPaliwemRepo pojazdZasilanyPaliwemRepo;
    private final SamolotRepo samolotRepo;
    private final RezerwacjaRepo rezerwacjaRepo;
    private final TerminalRepo terminalRepo;

    private Samolot samolot;

    Lotnisko l1, l2, l3, l4, l5, l6, l7, l8;
    Pasazer p1, p2, p3, p4, p5, p6, p7, p8;
    Lot f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12;
    Pilot pilot1, pilot2, pilot3, pilot4, pilot5, pilot6, pilot7, pilot8;
    CzlonekZalogi cz1, cz2, cz3, cz4, cz5, cz6, cz7, cz8;
    PojazdZasilanyElektrycznie pze1, pze2, pze3, pze4, pze5, pze6, pze7, pze8;
    PojazdZasilanyPaliwem pzp1, pzp2, pzp3, pzp4, pzp5, pzp6, pzp7, pzp8;
    Samolot s1, s2, s3, s4, s5, s6, s7, s8;
    Rezerwacja r1, r2, r3, r4, r5, r6, r7, r8;
    Terminal t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;

    @EventListener
    public void atStrat(ContextRefreshedEvent event){
        System.out.println("dataInit initialized");
        dataInit();
    }

    private void dataInit() {
        l1 = Lotnisko.builder()
                .name("aaa")
                .location("Warsaw")
                .area(1500)
                .isLocated(pilot1)
                .build();
        l2 = Lotnisko.builder()
                .name("bbb")
                .location("Cracow")
                .area(500)
                .isLocated(pilot2)
                .build();
        l3 = Lotnisko.builder()
                .name("ccc")
                .location("Barcelona")
                .area(2000)
                .isLocated(pilot3)
                .build();
        l4 = Lotnisko.builder()
                .name("ddd")
                .location("Madryt")
                .area(6000)
                .isLocated(pilot4)
                .build();
        l5 = Lotnisko.builder()
                .name("eee")
                .location("Amsterdam")
                .area(10000)
                .isLocated(pilot5)
                .build();
        l6 = Lotnisko.builder()
                .name("fff")
                .location("New York")
                .area(900)
                .isLocated(pilot6)
                .build();
        l7 = Lotnisko.builder()
                .name("ggg")
                .location("Dubaj")
                .area(90000)
                .isLocated(pilot7)
                .build();
        l8 = Lotnisko.builder()
                .name("hhh")
                .location("Berlin")
                .area(2000)
                .isLocated(pilot8)
                .build();

        cz1 = CzlonekZalogi.builder()
                .name("Bartek")
                .surname("Maj")
                .PESEL("11111111111")
                .birth_date(LocalDate.of(1994,3,28))
                .hours_worked(160) // Ensure these are set
                .paid(35)          // Ensure these are set
                .stanowisko("Operator")
                .build();
        cz2 = CzlonekZalogi.builder()
                .name("Marek")
                .surname("Olejnik")
                .PESEL("22222222222")
                .birth_date(LocalDate.of(1975,5,28))
                .hours_worked(174) // Ensure these are set
                .paid(27)          // Ensure these are set
                .stanowisko("Inzynier pokladowy")
                .build();
        cz3 = CzlonekZalogi.builder()
                .name("Joanna")
                .surname("Piotrowski")
                .PESEL("33333333333")
                .birth_date(LocalDate.of(1998,2,8))
                .hours_worked(177) // Ensure these are set
                .paid(25)          // Ensure these are set
                .stanowisko("Technik")
                .build();
        cz4 = CzlonekZalogi.builder()
                .name("Andrzej")
                .surname("Nowak")
                .PESEL("44444444444")
                .birth_date(LocalDate.of(2000,3,18))
                .hours_worked(150) // Ensure these are set
                .paid(31)          // Ensure these are set
                .stanowisko("Steward")
                .build();
        cz5 = CzlonekZalogi.builder()
                .name("Sylwia")
                .surname("Kowalska")
                .PESEL("55555555555")
                .birth_date(LocalDate.of(1984,9,22))
                .hours_worked(140) // Ensure these are set
                .paid(30)          // Ensure these are set
                .stanowisko("Nawigator")
                .build();
        cz6 = CzlonekZalogi.builder()
                .name("Marek")
                .surname("Lis")
                .PESEL("66666666666")
                .birth_date(LocalDate.of(2001,1,12))
                .hours_worked(168) // Ensure these are set
                .paid(29)          // Ensure these are set
                .stanowisko("Inzynier pokladowy")
                .build();
        cz7 = CzlonekZalogi.builder()
                .name("Anna")
                .surname("Krupa")
                .PESEL("77777777777")
                .birth_date(LocalDate.of(1994,3,4))
                .hours_worked(157) // Ensure these are set
                .paid(32)          // Ensure these are set
                .stanowisko("Radiotelegrafista")
                .build();
        cz8 = CzlonekZalogi.builder()
                .name("Marek")
                .surname("Baran")
                .PESEL("88888888888")
                .birth_date(LocalDate.of(1990,10,7))
                .hours_worked(145) // Ensure these are set
                .paid(31)          // Ensure these are set
                .stanowisko("Steward")
                .build();
        pze1 = PojazdZasilanyElektrycznie.builder()
                .name("pze1")
                .production_year(2006)
                .Batery_life(88)
                .electricity_cost(4)
                .build();
        pze2 = PojazdZasilanyElektrycznie.builder()
                .name("pze2")
                .production_year(2000)
                .Batery_life(90)
                .electricity_cost(4)
                .build();
        pze3 = PojazdZasilanyElektrycznie.builder()
                .name("pze3")
                .production_year(1995)
                .Batery_life(71)
                .electricity_cost(4)
                .build();
        pze4 = PojazdZasilanyElektrycznie.builder()
                .name("pze4")
                .production_year(1999)
                .Batery_life(81)
                .electricity_cost(4)
                .build();
        pze5 = PojazdZasilanyElektrycznie.builder()
                .name("pze5")
                .production_year(2015)
                .Batery_life(95)
                .electricity_cost(4)
                .build();
        pze6 = PojazdZasilanyElektrycznie.builder()
                .name("pze6")
                .production_year(2009)
                .Batery_life(93)
                .electricity_cost(4)
                .build();
        pze7 = PojazdZasilanyElektrycznie.builder()
                .name("pze7")
                .production_year(2001)
                .Batery_life(82)
                .electricity_cost(4)
                .build();
        pze8 = PojazdZasilanyElektrycznie.builder()
                .name("pze8")
                .production_year(2002)
                .Batery_life(80)
                .electricity_cost(4)
                .hasIt(l1)
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
                .production_year(2003)
                .capacity(45)
                .fuel_type("DIESEL")
                .fuel_cost(6.5)
                .build();
        pzp3 = PojazdZasilanyPaliwem.builder()
                .name("pzp3")
                .production_year(2013)
                .capacity(100)
                .fuel_type("PETROL")
                .fuel_cost(5.5)
                .build();
        pzp4 = PojazdZasilanyPaliwem.builder()
                .name("pzp4")
                .production_year(2010)
                .capacity(50)
                .fuel_type("PETROL")
                .fuel_cost(5.5)
                .build();
        pzp5 = PojazdZasilanyPaliwem.builder()
                .name("pzp5")
                .production_year(1999)
                .capacity(45)
                .fuel_type("LPG")
                .fuel_cost(2.66)
                .build();
        pzp6 = PojazdZasilanyPaliwem.builder()
                .name("pzp6")
                .production_year(1990)
                .capacity(40)
                .fuel_type("LPG")
                .fuel_cost(2.66)
                .build();
        pzp7 = PojazdZasilanyPaliwem.builder()
                .name("pzp7")
                .production_year(2006)
                .capacity(45)
                .fuel_type("DIESEL")
                .fuel_cost(6.5)
                .build();
        pzp8 = PojazdZasilanyPaliwem.builder()
                .name("pzp8")
                .production_year(2001)
                .capacity(50)
                .fuel_type("LPG")
                .fuel_cost(2.66)
                .hasIt(l1)
                .build();

        s1 = Samolot.builder()
                .name("s1")
                .production_year(2008)
                .plane_type("AVIONET")
                .max_flight_range(200)
                .plane_fuel_cost(20.9)
                .build();
        s2 = Samolot.builder()
                .name("s2")
                .production_year(2000)
                .plane_type("JET")
                .max_flight_range(400)
                .plane_fuel_cost(25.3)
                .build();
        s3 = Samolot.builder()
                .name("s3")
                .production_year(2014)
                .plane_type("BOEING")
                .max_flight_range(1000)
                .plane_fuel_cost(30.1)
                .build();
        s4 = Samolot.builder()
                .name("s4")
                .production_year(2009)
                .plane_type("BOEING")
                .max_flight_range(1100)
                .plane_fuel_cost(30.1)
                .build();
        s5 = Samolot.builder()
                .name("s5")
                .production_year(2006)
                .plane_type("AVIONET")
                .max_flight_range(300)
                .plane_fuel_cost(20.9)
                .build();
        s6 = Samolot.builder()
                .name("s6")
                .production_year(2008)
                .plane_type("BOEING")
                .max_flight_range(2000)
                .plane_fuel_cost(30.1)
                .build();
        s7 = Samolot.builder()
                .name("s7")
                .production_year(2005)
                .plane_type("JET")
                .max_flight_range(600)
                .plane_fuel_cost(25.3)
                .build();
        s8 = Samolot.builder()
                .name("s8")
                .production_year(2009)
                .plane_type("BOEING")
                .max_flight_range(1500)
                .plane_fuel_cost(30.1)
                .build();

        pilot1 = Pilot.builder()
                .name("Marcin")
                .surname("Kwiatkowski")
                .PESEL("12121212121")
                .birth_date(LocalDate.of(1992,3,4 ))
                .hours_worked(179)
                .paid(38)
                .certificate(new HashSet<>(Arrays.asList("BRO", "DOG")))
                .flies(s1)
                .build();
        pilot2 = Pilot.builder()
                .name("Adam")
                .surname("Kowalczyk")
                .PESEL("23232323232")
                .birth_date(LocalDate.of(1985,5,12 ))
                .hours_worked(179)
                .paid(40)
                .certificate(new HashSet<>(Arrays.asList("ALS", "SEA")))
                .flies(s2)
                .build();
        pilot3 = Pilot.builder()
                .name("Jakub")
                .surname("Kowalczyk")
                .PESEL("34343434343")
                .birth_date(LocalDate.of(1985,11,21  ))
                .hours_worked(179)
                .paid(38)
                .certificate(new HashSet<>(Arrays.asList("CAT", "FLY")))
                .flies(s3)
                .build();
        pilot4 = Pilot.builder()
                .name("Krzysztof")
                .surname("Szulc")
                .PESEL("45454545454")
                .birth_date(LocalDate.of(1982,6,21  ))
                .hours_worked(200)
                .paid(36)
                .certificate(new HashSet<>(Arrays.asList("CAT", "DOG")))
                .flies(s4)
                .build();
        pilot5 = Pilot.builder()
                .name("Piotr")
                .surname("Wojciechowski")
                .PESEL("56565656565")
                .birth_date(LocalDate.of(1978,11,17 ))
                .hours_worked(108)
                .paid(38 )
                .certificate(new HashSet<>(Arrays.asList("BRO", "FLY")))
                .flies(s5)
                .build();
        pilot6 = Pilot.builder()
                .name("Kacper")
                .surname("Jankowski")
                .PESEL("67676767676")
                .birth_date(LocalDate.of(1973,2,14 ))
                .hours_worked(196)
                .paid(26)
                .certificate(new HashSet<>(Arrays.asList("ALS", "DOG")))
                .flies(s6)
                .build();
        pilot7 = Pilot.builder()
                .name("Robert")
                .surname("Zawadzki")
                .PESEL("78787878787")
                .birth_date(LocalDate.of(1999,8,14 ))
                .hours_worked(135)
                .paid(42)
                .certificate(new HashSet<>(Arrays.asList("BRO", "DOG")))
                .flies(s8)
                .build();
        pilot8 = Pilot.builder()
                .name("Artur")
                .surname("Bielicki")
                .PESEL("89898989898")
                .birth_date(LocalDate.of(1990,9,9 ))
                .hours_worked(118)
                .paid(32)
                .certificate(new HashSet<>(Arrays.asList("BRO", "DOG")))
                .flies(s7)
                .build();

        t1 = Terminal.builder()
                .name("t1")
                .contact_description("Dostępny przez email na adres t1@company.com")
                .owner(l1)
                .build();
        t2 = Terminal.builder()
                .name("t2")
                .contact_description("Kontakt telefoniczny pod numerem +48123456789")
                .owner(l2)
                .build();
        t3 = Terminal.builder()
                .name("t3")
                .contact_description("Wszelkie zapytania proszę kierować przez formularz na naszej stronie.")
                .owner(l3)
                .build();
        t4 = Terminal.builder()
                .name("t4")
                .contact_description("Możliwość kontaktu przez Skype: t4_skype")
                .owner(l4)
                .build();
        t5 = Terminal.builder()
                .name("t5")
                .contact_description("Dostępny dla zapytań przez WhatsApp pod numerem +48111222333")
                .owner(l5)
                .build();
        t6 = Terminal.builder()
                .name("t6")
                .contact_description("Kontakt przez Messenger na Facebooku pod nazwą T6Support")
                .owner(l6)
                .build();
        t7 = Terminal.builder()
                .name("t7")
                .contact_description("Kontakt bezpośredni w godzinach 9-17 pod numerem biura +48987654321")
                .owner(l7)
                .build();
        t8 = Terminal.builder()
                .name("t8")
                .contact_description("Dyspozycyjność mailowa przez całą dobę na t8@helpdesk.com")
                .owner(l8)
                .build();
        t9 = Terminal.builder()
                .name("t9")
                .contact_description("Brak")
                .owner(l1)
                .build();
        t10 = Terminal.builder()
                .name("t10")
                .contact_description("Kontakt telefoniczny pod numerem +99999999")
                .owner(l2)
                .build();

        f1 = Lot.builder()
                .flight_number("s111")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l1)
                .arrivalAirport(l6)
                .totalSeats(60)
                .build();
        f2 = Lot.builder()
                .flight_number("s112")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l2)
                .arrivalAirport(l1)
                .totalSeats(80)
                .build();
        f3 = Lot.builder()
                .flight_number("s121")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l3)
                .arrivalAirport(l6)
                .totalSeats(120)
                .build();
        f4 = Lot.builder()
                .flight_number("s211")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l8)
                .arrivalAirport(l4)
                .totalSeats(90)
                .build();
        f5 = Lot.builder()
                .flight_number("s122")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l1)
                .arrivalAirport(l3)
                .totalSeats(93)
                .build();
        f6 = Lot.builder()
                .flight_number("s212")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l6)
                .arrivalAirport(l5)
                .totalSeats(78)
                .build();
        f7 = Lot.builder()
                .flight_number("s221")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l7)
                .arrivalAirport(l2)
                .totalSeats(83)
                .build();
        f8 = Lot.builder()
                .flight_number("s222")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l2)
                .arrivalAirport(l8)
                .totalSeats(47)
                .build();
        f9 = Lot.builder()
                .flight_number("s123")
                .departure_date(LocalDate.of(2004,12,12))
                .arrival_date(LocalDate.of(2004,12,13))
                .departureAirport(l7)
                .arrivalAirport(l4)
                .totalSeats(88)
                .build();
        f10 = Lot.builder()
                .flight_number("s213")
                .departure_date(LocalDate.of(2004,6,6))
                .arrival_date(LocalDate.of(2004,6,6))
                .departureAirport(l7)
                .arrivalAirport(l4)
                .totalSeats(99)
                .build();
        f11 = Lot.builder()
                .flight_number("s312")
                .departure_date(LocalDate.of(2004,9,23))
                .arrival_date(LocalDate.of(2004,9,24))
                .departureAirport(l5)
                .arrivalAirport(l6)
                .totalSeats(123)
                .build();
        f12 = Lot.builder()
                .flight_number("s321")
                .departure_date(LocalDate.of(2004,11,11))
                .arrival_date(LocalDate.of(2004,11,11))
                .departureAirport(l1)
                .arrivalAirport(l7)
                .totalSeats(50)
                .build();

        r1 = Rezerwacja.builder()
                .ticketClass("Business")
                .reservation_date(LocalDate.now())
                .status("Opłacona")
                .seats(12)
                .hasIt(f1)
                .build();
        r2 = Rezerwacja.builder()
                .ticketClass("Normal")
                .reservation_date(LocalDate.of(2024,8,19))
                .status("Nieopłacona")
                .seats(52)
                .hasIt(f2)
                .build();
        r3 = Rezerwacja.builder()
                .ticketClass("Normal")
                .reservation_date(LocalDate.of(2024,7,20))
                .status("Opłacona")
                .seats(1)
                .hasIt(f3)
                .build();
        r4 = Rezerwacja.builder()
                .ticketClass("VIP")
                .reservation_date(LocalDate.of(2024,8,24))
                .status("Opłacona")
                .seats(33)
                .hasIt(f4)
                .build();
        r5 = Rezerwacja.builder()
                .ticketClass("Business")
                .reservation_date(LocalDate.of(2024,8,10))
                .status("Opłacona")
                .seats(12)
                .hasIt(f5)
                .build();
        r6 = Rezerwacja.builder()
                .ticketClass("Business")
                .reservation_date(LocalDate.of(2024,9,20))
                .status("Opłacona")
                .seats(47)
                .hasIt(f6)
                .build();
        r7 = Rezerwacja.builder()
                .ticketClass("Normal")
                .reservation_date(LocalDate.of(2024,8,21))
                .status("Opłacona")
                .seats(14)
                .hasIt(f7)
                .build();
        r8 = Rezerwacja.builder()
                .ticketClass("Normal")
                .reservation_date(LocalDate.of(2024,8,20))
                .status(null)
                .seats(17)
                .hasIt(f8)
                .build();

        p1 = Pasazer.builder()
                .name("Anna")
                .surname("Zielinski")
                .PESEL("98048977441")
                .birth_date(LocalDate.of(1997,4,12))
                .luggage(9.65)
                .book(r3)
                .build();
        p2 = Pasazer.builder()
                .name("Anna ")
                .surname("Dabrowska")
                .PESEL("98048977442")
                .birth_date(LocalDate.of(1990,5,13))
                .luggage(7.9)
                .book(r1)
                .build();
        p3 = Pasazer.builder()
                .name("Piotr ")
                .surname("Kowalski")
                .PESEL("98048977443")
                .birth_date(LocalDate.of(1961,2,11))
                .luggage(10.5)
                .book(r2)
                .build();
        p4 = Pasazer.builder()
                .name("Jan")
                .surname("Nowak")
                .PESEL("98048977444")
                .birth_date(LocalDate.of(1964,6,19))
                .luggage(23.2)
                .book(r4)
                .build();
        p5 = Pasazer.builder()
                .name("Katarzyna")
                .surname("Lewandowski")
                .PESEL("98048977445")
                .birth_date(LocalDate.of(1992,3,28))
                .luggage(15.3)
                .book(r5)
                .build();
        p6 = Pasazer.builder()
                .name("Kacper")
                .surname("Knot")
                .PESEL("98048977446")
                .birth_date(LocalDate.of(1996,6,5))
                .luggage(12.12)
                .book(r6)
                .build();
        p7 = Pasazer.builder()
                .name("Piotr")
                .surname("Potter")
                .PESEL("98048977447")
                .birth_date(LocalDate.of(2003,7,1))
                .luggage(34.65)
                .book(r7)
                .build();
        p8 = Pasazer.builder()
                .name("Norbert")
                .surname("Wick")
                .PESEL("98048977448")
                .birth_date(LocalDate.of(2000,12,15))
                .luggage(2.9)
                .book(r8)
                .build();

//        lotniskoRepo.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8));
//        czlonekZalogiRepo.saveAll(Arrays.asList(cz1, cz2, cz3, cz4, cz5, cz6, cz7, cz8));
//        pojazdZasilanyElektrycznieRepo.saveAll(Arrays.asList(pze1, pze2, pze3, pze4, pze5, pze6, pze7, pze8));
//        pojazdZasilanyPaliwemRepo.saveAll(Arrays.asList(pzp1, pzp2, pzp3, pzp4, pzp5, pzp6, pzp7, pzp8));
//        samolotRepo.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8));
//        pilotRepo.saveAll(Arrays.asList(pilot1, pilot2, pilot3, pilot4, pilot5, pilot6, pilot7, pilot8));
//        terminalRepo.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10));
//        lotRepo.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8,f9, f10, f11, f12));
//        rezerwacjaRepo.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8));
//        pasazerRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
    }
}
