package ru.ezhov.knowledgeBase.formatter.pages;

import org.junit.Test;
import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataFormatter;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;
import ru.ezhov.knowledgeBase.common.interfaces.DataTransformer;
import ru.ezhov.knowledgeBase.formatter.HtmlDataFormatter;
import ru.ezhov.knowledgeBase.loader.DataLoaderFactory;
import ru.ezhov.knowledgeBase.loader.GitInfo;
import ru.ezhov.knowledgeBase.transformer.SimpleDataTransformer;
import ru.ezhov.propertiesReader.Properties;
import ru.ezhov.propertiesReader.PropertiesFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class IndexPageCreatorTest {
    @Test
    public void ctreate() throws Exception {
        Properties<String, String> stringProperties =
                PropertiesFactory.getPropertiesFromUserDirectory(
                        ".test-path-html-kb-formatter");

        String path = stringProperties.getProperty("path.create.html.files");

        System.out.println(path);

        DataLoader<KnowledgeRaw, GitInfo> stringDataLoader =
                DataLoaderFactory.createFileSystemDataLoader();
        Set<KnowledgeRaw> knowledgeRawSet = stringDataLoader.load(
                new GitInfo(
                        stringProperties.getProperty("path.files.source.knowledge"),
                        stringProperties.getProperty("name.foder.source.knowledge")));


        DataTransformer<Knowledge, KnowledgeRaw> knowledgeRawDataTransformer =
                new SimpleDataTransformer();

        Set<Knowledge> knowledges = knowledgeRawDataTransformer.transform(knowledgeRawSet);

        PageCreator<String> stringPageCreator = new IndexPageCreator(knowledges);

        String str = stringPageCreator.ctreate();

        System.out.println(str);
    }

}