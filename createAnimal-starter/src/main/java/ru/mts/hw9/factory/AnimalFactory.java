package ru.mts.hw9.factory;

import org.springframework.stereotype.Component;
import ru.mts.hw9.animal.Animal;
import ru.mts.hw9.animal.impl.Cat;
import ru.mts.hw9.animal.impl.Dog;
import ru.mts.hw9.animal.impl.Shark;
import ru.mts.hw9.animal.impl.Wolf;
import ru.mts.hw9.config.AnimalNamesProvider;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AnimalFactory {
    private final AnimalNamesProvider animalNamesProvider;

    public AnimalFactory(AnimalNamesProvider animalNamesProvider) {
        this.animalNamesProvider = animalNamesProvider;
    }

    /**
     * Метод фабрики, создает нужный тип животного
     *
     * @param type Enum с типом животного
     * @return Объект созданного животного
     */
    public Animal getAnimal(AnimalTypes type) {
        int randomNumForName = ThreadLocalRandom.current().nextInt(1, 300);
        double randomCost = ThreadLocalRandom.current().nextDouble(0.001, 300);
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));

        switch (type) {
            case WOLF:
                return new Wolf(animalNamesProvider.getWolfName() + randomNumForName, randomCost, randomDate);
            case SHARK:
                return new Shark(animalNamesProvider.getSharkName() + randomNumForName, randomCost, randomDate);
            case DOG:
                return new Dog(animalNamesProvider.getDogName() + randomNumForName, randomCost, randomDate);
            case CAT:
                return new Cat(animalNamesProvider.getCatName() + randomNumForName, randomCost, randomDate);
            case UNDEFINED_ANIMAL:
                throw new IllegalStateException("Can't create undefined animal in factory");
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
