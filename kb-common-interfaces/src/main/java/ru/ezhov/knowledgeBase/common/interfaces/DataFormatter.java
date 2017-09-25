package ru.ezhov.knowledgeBase.common.interfaces;

import java.util.Set;

/**
 * Интерфейс, описывающий преобразование данных из объектов со знаниями
 * к итоговому виду для отображения пользователю
 * @param <T1> - отдаваемый тип
 * @param <T2> - получаемый тип
 */
public interface DataFormatter<T1, T2> {
    T1 format(Set<T2> source) throws Exception;
}
