package ru.ezhov.knowledgeBase.common.interfaces;

import java.util.Set;

/**
 * Данный интерфейс реализую кллассы, которые хотят получать
 * грязные данные и преобразовывать их в знания
 * @param <T1> - отдаваемый тип
 * @param <T2> - получаемый тип
 */
public interface DataTransformer<T1, T2> {
    Set<T1> transform(Set<T2> source) throws Exception;
}
