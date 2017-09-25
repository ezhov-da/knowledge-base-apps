package ru.ezhov.knowledgeBase.formatter;

import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.interfaces.DataFormatter;
import ru.ezhov.propertiesReader.Properties;
import ru.ezhov.propertiesReader.PropertiesFactory;

import static org.junit.Assert.*;

public class HtmlDataFormatterTest {
    @org.junit.Test
    public void format() throws Exception {
        Properties<String, String> stringProperties =
                PropertiesFactory.getPropertiesFromUserDirectory(".test-path-html-kb-formatter");

        String path = stringProperties.getProperty("path.create.html.files");

        System.out.println(path);

        DataFormatter<Object, Knowledge> dataFormatter = new HtmlDataFormatter(path);

    }

}