package ru.ezhov.knowledgeBase.common.interfaces;

import java.util.Set;

/**
 * Интерфейс, который получает сами даннные и предоставляет их в грязном виде
 * @param <T1> - тип для отдачи
 * @param <T2> - тип для получения
 */

public interface DataLoader<T1, T2> {
    Set<T1> load(T2 source) throws Exception;
}
