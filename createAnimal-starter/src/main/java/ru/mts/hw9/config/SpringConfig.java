package ru.mts.hw9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw9.factory.AnimalFactory;
import ru.mts.hw9.service.CreateAnimalService;
import ru.mts.hw9.service.impl.CreateAnimalServiceImpl;

@Configuration
public class SpringConfig {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }

    @Bean()
    @Scope("prototype")
    public CreateAnimalService createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }
}
