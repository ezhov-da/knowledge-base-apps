package ru.ezhov.knowledgeBase.formatter;

import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.interfaces.DataFormatter;

import java.util.Set;

public class HtmlDataFormatter implements DataFormatter<Object, Knowledge> {
    private String pathToFolderHtml;

    public HtmlDataFormatter(String pathToFolderHtml) {
        this.pathToFolderHtml = pathToFolderHtml;
    }

    /**
     * Формирование html файлов
     *
     * @param set
     * @return не используется
     * @throws Exception
     */
    public Object format(Set<Knowledge> set) throws Exception {







        return null;
    }
}
