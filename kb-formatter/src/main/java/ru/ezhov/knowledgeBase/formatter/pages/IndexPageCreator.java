package ru.ezhov.knowledgeBase.formatter.pages;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexPageCreator implements PageCreator<String> {
    private Set<Knowledge> knowledgeSet;

    public IndexPageCreator(Set<Knowledge> knowledgeSet) {
        this.knowledgeSet = knowledgeSet;
    }

    public String ctreate() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        URL url = this.getClass().getResource("/templates");
        cfg.setDirectoryForTemplateLoading(new File(url.toURI()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        Template temp = cfg.getTemplate("index.html");
        Map<String, Object> root = new HashMap<String, Object>();
        StringWriter stringWriter = new StringWriter();

        root.put("title", "Привет");

        temp.process(root, stringWriter);

        String page = stringWriter.toString();


        Map<String, Integer> part = new HashMap<String, Integer>();

        for (Knowledge knowledge : knowledgeSet) {
            String partName = knowledge.getNamePart();

            if (part.containsKey(partName)) {
                Integer integer = part.get(partName);
                integer++;
                part.put(partName, integer);
            } else {
                part.put(partName, 1);
            }
        }

        Set<Map.Entry<String, Integer>> entries = part.entrySet();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Last build date: " + new Date());
        stringBuilder.append("<br/>\n");
        stringBuilder.append("<br/>\n");
        stringBuilder.append("<br/>\n");


        for (Map.Entry<String, Integer> integerEntry : entries) {
            String tag = integerEntry.getKey();
            Integer count = integerEntry.getValue();

            stringBuilder.append(
                    "<a href=\"/" + tag + "_tags.html\">" + tag + "</a> " +
                            "<a href =\"/" + tag + "_source.html\">" + "<span class=\"badge\">" + count + "</span></a>");
            stringBuilder.append("<br/>\n");
        }
        return page;
    }
}
