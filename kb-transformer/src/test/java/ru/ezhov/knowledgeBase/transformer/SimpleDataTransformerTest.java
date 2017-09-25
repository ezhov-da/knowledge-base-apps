package ru.ezhov.knowledgeBase.transformer;

import org.junit.Test;
import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;
import ru.ezhov.knowledgeBase.common.interfaces.DataTransformer;
import ru.ezhov.knowledgeBase.loader.DataLoaderFactory;
import ru.ezhov.knowledgeBase.loader.GitInfo;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class SimpleDataTransformerTest {
    @Test
    public void transform() throws Exception {

        DataTransformer<Knowledge, KnowledgeRaw> rawDataTransformer
                = new SimpleDataTransformer();

        DataLoader<KnowledgeRaw, GitInfo> dataLoader = DataLoaderFactory.createFileSystemDataLoader();

        GitInfo gitInfo = new GitInfo("C:\\Users\\rrnezh\\programmer\\git\\knowledge-base", "kb");

        Set<KnowledgeRaw> knowledgeRawSet = dataLoader.load(gitInfo);

        Set<Knowledge> knowledges = rawDataTransformer.transform(knowledgeRawSet);

        for (Knowledge knowledge : knowledges) {
            System.out.println("================================================");
            System.out.println(knowledge);
        }
    }

}