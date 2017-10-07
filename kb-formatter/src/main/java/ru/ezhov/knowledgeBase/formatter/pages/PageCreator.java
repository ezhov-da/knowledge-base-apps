package ru.ezhov.knowledgeBase.formatter.pages;

public interface PageCreator<T> {
    T ctreate() throws Exception;
}
