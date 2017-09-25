package ru.ezhov.knowledgeBase.loader;

import org.junit.Ignore;
import org.junit.Test;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;

import java.util.Set;

import static org.junit.Assert.*;

public class HtmlDataLoaderTest {
    @Test
    @Ignore
    public void load() throws Exception {
        DataLoader htmlDataLoader = DataLoaderFactory.createHtmlDataLoader();

        GitInfo gitInfo = new GitInfo(
                "ezhov-da/knowledge-base",
                "kb"
        );

        Set<KnowledgeRaw> knowledgeRaws = htmlDataLoader.load(gitInfo);

        assertTrue(knowledgeRaws.size() > 0);

    }
}