package ru.mts.hw6.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.animal.impl.Cat;
import ru.mts.hw6.animal.impl.Dog;
import ru.mts.hw6.animal.impl.Shark;
import ru.mts.hw6.animal.impl.Wolf;
import ru.mts.hw6.config.AnimalNamesProvider;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AnimalFactory {
    final AnimalNamesProvider animalNamesProvider;

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
            default:
                throw new IllegalArgumentException("Wrong animal type:" + type);
        }
    }
}
