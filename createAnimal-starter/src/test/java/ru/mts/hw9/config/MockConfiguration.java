package ru.mts.hw9.config;


import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.mts.hw9.animal.impl.Shark;
import ru.mts.hw9.factory.AnimalFactory;
import ru.mts.hw9.factory.AnimalTypes;

import java.time.LocalDate;

@Profile("test")
@TestConfiguration
public class MockConfiguration {
    @Bean
    @Primary
    public AnimalFactory animalFactoryMock() {
        AnimalFactory impl = Mockito.mock(AnimalFactory.class);
        Mockito.when(impl.getAnimal(AnimalTypes.WOLF)).thenReturn(new Shark("S", 0.1, LocalDate.of(2020, 1, 8)));
        Mockito.when(impl.getAnimal(AnimalTypes.DOG)).thenReturn(new Shark("S", 0.1, LocalDate.of(2020, 1, 8)));
        Mockito.when(impl.getAnimal(AnimalTypes.CAT)).thenReturn(new Shark("S", 0.1, LocalDate.of(2020, 1, 8)));
        Mockito.when(impl.getAnimal(AnimalTypes.SHARK)).thenReturn(new Shark("S", 0.1, LocalDate.of(2020, 1, 8)));
        return impl;
    }
}
