package ru.mts.hw9.animal.impl;

import ru.mts.hw9.animal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    /**
     * Новый equals для сравнения животных
     *
     * @param obj Принимает объект
     * @return возвращает true если он equals изначальному
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        AbstractAnimal abstractAnimal = (AbstractAnimal) obj;
        return breed.equals(abstractAnimal.breed) && name.equals(abstractAnimal.name)
                && cost.equals(abstractAnimal.cost) && character.equals(abstractAnimal.character)
                && birthDate.equals(abstractAnimal.birthDate);
    }

    /**
     * Новый hash для животных
     *
     * @return возвращает int посчитанный hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}
