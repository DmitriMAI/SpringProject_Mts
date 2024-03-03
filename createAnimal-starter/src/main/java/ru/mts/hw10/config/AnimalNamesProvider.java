package ru.mts.hw10.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AnimalNamesProvider {
    @Value("${animal.cat.names}")
    private List<String> catNames;
    @Value("${animal.wolf.names}")
    private List<String> wolfNames;
    @Value("${animal.dog.names}")
    private List<String> dogNames;
    @Value("${animal.shark.names}")
    private List<String> sharkNames;

    public String getDogName() {
        if (dogNames.isEmpty()) {
            return "blancDogName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, dogNames.size());
        return dogNames.get(randomIndexForName);
    }

    public String getCatName() {
        if (catNames.isEmpty()) {
            return "blancCatName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, catNames.size());
        return catNames.get(randomIndexForName);
    }

    public String getWolfName() {
        if (wolfNames.isEmpty()) {
            return "blancWolfName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, wolfNames.size());
        return wolfNames.get(randomIndexForName);
    }

    public String getSharkName() {
        if (sharkNames.isEmpty()) {
            return "blancSharkName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, sharkNames.size());
        return sharkNames.get(randomIndexForName);
    }
}
