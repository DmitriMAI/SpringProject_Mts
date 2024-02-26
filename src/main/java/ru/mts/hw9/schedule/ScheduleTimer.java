package ru.mts.hw9.schedule;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw9.animal.Animal;
import ru.mts.hw9.repository.impl.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

@Component
@EnableScheduling
@Profile("!test")
public class ScheduleTimer {
    private final AnimalsRepositoryImpl animalsRepository;

    public ScheduleTimer(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void printEverySecond() {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("***   Demonstration for findOlderAnimal method");
        Map<Animal, Integer> agedOlderAnimals = animalsRepository.findOlderAnimal(3);

        Set<Animal> keysMapOlder = agedOlderAnimals.keySet();
        for (Animal animal : keysMapOlder) {
            int value = agedOlderAnimals.get(animal);
            System.out.println(animal + " Aged: " + value);
        }
        System.out.println("***   Demonstration for findLeapYearNames method");
        Map<String, LocalDate> leapYearAnimalNames = animalsRepository.findLeapYearNames();
        if (leapYearAnimalNames.isEmpty()) {
            System.out.println("There is no leap year animal");
        }
        Set<String> keysMapLeapYear = leapYearAnimalNames.keySet();
        for (String animalName : keysMapLeapYear) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String bornDate = leapYearAnimalNames.get(animalName).format(pattern);
            System.out.println(animalName + " " + bornDate);
        }
        System.out.println("***   Demonstration for findDuplicate method");
        animalsRepository.printDuplicate();
        System.out.println("***   Map<Animal,Integer> for duplicates used in findDuplicate method");
    }
}
