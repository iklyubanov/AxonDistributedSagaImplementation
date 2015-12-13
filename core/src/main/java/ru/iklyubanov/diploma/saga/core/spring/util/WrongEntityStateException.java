package ru.iklyubanov.diploma.saga.core.spring.util;

/**
 * Ошибка уведомляющая о некорректном состоянии сущности
 * Created by ivan on 12/13/2015.
 */
public class WrongEntityStateException extends Exception{
    public WrongEntityStateException(String message) {
        super(message);
    }
}
