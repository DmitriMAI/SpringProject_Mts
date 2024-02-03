package ru.mts.hw6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw6.repository.impl.AnimalsRepository;
import ru.mts.hw6.service.CreateAnimalService;
import ru.mts.hw6.service.impl.CreateAnimalServiceImpl;

@Configuration
public class SpringConfig {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }

    @Bean()
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalsRepository createAnimalRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepository(createAnimalService);
    }
}
