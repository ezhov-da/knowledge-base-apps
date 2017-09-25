package ru.ezhov.knowledgeBase.loader;

import org.junit.Test;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;

import java.util.Set;

import static org.junit.Assert.*;

public class FileSystemDataLoaderTest {
    @Test
    public void load() throws Exception {
        DataLoader fileSystemDataLoader = DataLoaderFactory.createFileSystemDataLoader();

        GitInfo gitInfo = new GitInfo(
                "C:\\Users\\rrnezh\\programmer\\git\\knowledge-base",
                "kb"
        );

        Set<KnowledgeRaw> knowledgeRaws = fileSystemDataLoader.load(gitInfo);

        assertTrue(knowledgeRaws.size() > 0);
    }

}