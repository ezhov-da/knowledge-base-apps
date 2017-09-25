package ru.ezhov.knowledgeBase.loader;

import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;

public class DataLoaderFactory {
    public static DataLoader<KnowledgeRaw, GitInfo> createHtmlDataLoader() {
        return new HtmlDataLoader();
    }

    public static DataLoader<KnowledgeRaw, GitInfo> createFileSystemDataLoader () {
        return new FileSystemDataLoader ();
    }
}
