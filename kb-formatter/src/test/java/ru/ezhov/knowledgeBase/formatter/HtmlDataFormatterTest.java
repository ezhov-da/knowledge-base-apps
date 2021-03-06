package ru.ezhov.knowledgeBase.formatter;

import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataFormatter;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;
import ru.ezhov.knowledgeBase.common.interfaces.DataTransformer;
import ru.ezhov.knowledgeBase.loader.DataLoaderFactory;
import ru.ezhov.knowledgeBase.loader.GitInfo;
import ru.ezhov.knowledgeBase.transformer.SimpleDataTransformer;
import ru.ezhov.propertiesReader.Properties;
import ru.ezhov.propertiesReader.PropertiesFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class HtmlDataFormatterTest {
    @org.junit.Test
    public void format() throws Exception {
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

        DataFormatter<Object, Knowledge> dataFormatter = new HtmlDataFormatter(path);

        dataFormatter.format(knowledges);

    }

}