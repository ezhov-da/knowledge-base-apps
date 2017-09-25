package ru.ezhov.knowledgeBase.common.interfaces;

/**
 * Интерфейс для деплоя информации пользователю
 *
 * @param <T> - получаемые данные для деплоя
 */
public interface DataDeployer<T> {
    void deploy(T source) throws Exception;
}
