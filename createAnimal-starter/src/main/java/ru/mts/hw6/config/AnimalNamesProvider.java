package ru.mts.hw6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AnimalNamesProvider {
    @Value("${animal.cat.names}")
    private String[] catNames;
    @Value("${animal.wolf.names}")
    private String[] wolfNames;
    @Value("${animal.dog.names}")
    private String[] dogNames;
    @Value("${animal.shark.names}")
    private String[] sharkNames;

    public String getDogName(){
        if (dogNames.length == 0){
            return "blancDogName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, dogNames.length);
        return dogNames[randomIndexForName];
    }
    public String getCatName(){
        if (catNames.length == 0){
            return "blancCatName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, catNames.length);
        return catNames[randomIndexForName];
    }
    public String getWolfName(){
        if (wolfNames.length == 0){
            return "blancWolfName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, wolfNames.length);
        return wolfNames[randomIndexForName];
    }
    public String getSharkName(){
        if (sharkNames.length == 0){
            return "blancSharkName";
        }
        int randomIndexForName = ThreadLocalRandom.current().nextInt(1, sharkNames.length);
        return sharkNames[randomIndexForName];
    }
}
