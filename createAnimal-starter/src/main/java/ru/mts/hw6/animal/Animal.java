package ru.mts.hw6.animal;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс для создания животного
 * обладает методами указывающими на получение информации о полях
 */
public interface Animal {
    /**
     * Возвращает породу животного
     *
     * @return имя породы
     */
    String getBreed();

    /**
     * Возвращает имя животного
     *
     * @return имя животного
     */
    String getName();

    /**
     * Возвращает цену одного животного
     *
     * @return цену одного животного
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного
     *
     * @return характер животного
     */
    String getCharacter();

    /**
     * Возвращает дату рождения животного
     *
     * @return дата рождения животного
     */
    LocalDate getBirthDate();
}
