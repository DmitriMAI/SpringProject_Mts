package ru.mts.hw6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.config.SpringConfig;
import ru.mts.hw6.repository.impl.AnimalsRepositoryImpl;
import ru.mts.hw6.service.CreateAnimalService;
import ru.mts.hw6.service.impl.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AnimalsRepositoryImpl animalsRepository = context.getBean(AnimalsRepositoryImpl.class);

        System.out.println("***   Demonstration for prototype bean");
        CreateAnimalService animalService = context.getBean(CreateAnimalServiceImpl.class);
        System.out.println(animalService.getAnimal());
        animalService = context.getBean(CreateAnimalServiceImpl.class);
        System.out.println(animalService.getAnimal());

        System.out.println("***   Demonstration for findOlderAnimal method");
        Animal[] agedOlderAnimals = animalsRepository.findOlderAnimal(3);
        for (Animal animal : agedOlderAnimals) {
            System.out.println(animal);
        }
        System.out.println("***   Demonstration for findLeapYearNames method");
        String[] leapYearAnimalNames = animalsRepository.findLeapYearNames();
        if(leapYearAnimalNames.length == 0){
            System.out.println("There is no leap year animal");
        }
        for (String animalName : leapYearAnimalNames) {
            System.out.println(animalName);
        }
        System.out.println("***   Demonstration for findDuplicate method");
        animalsRepository.printDuplicate();
        System.out.println("***   Map<Animal,Integer> for duplicates used in findDuplicate method");
    }
}