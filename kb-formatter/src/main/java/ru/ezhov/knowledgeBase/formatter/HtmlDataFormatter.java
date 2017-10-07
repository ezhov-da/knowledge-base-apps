package ru.ezhov.knowledgeBase.formatter;

import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.interfaces.DataFormatter;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

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


        File file = new File(pathToFolderHtml);
        File[] files = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".html");
            }
        });

        for (File file1 : files) {
            file1.delete();
        }

        createIndex(set);
        createSource(set);
        createTags(set);
        createTagKnowledge(set);


        return null;
    }

    private void createIndex(Set<Knowledge> set) throws UnsupportedEncodingException {

        Map<String, Integer> part = new HashMap<String, Integer>();

        for (Knowledge knowledge : set) {
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

        File file = new File(pathToFolderHtml, "index.html");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(htmlCreator(stringBuilder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void createSource(Set<Knowledge> set) throws UnsupportedEncodingException {
        Map<String, List<String>> listMap = new HashMap<String, List<String>>();

        for (Knowledge knowledge : set) {
            String namePart = knowledge.getNamePart();
            List<String> strings;
            if (listMap.containsKey(namePart)) {
                strings = listMap.get(namePart);
            } else {
                strings = new ArrayList<String>();
                listMap.put(namePart, strings);
            }
            String href = "<a href=\"%s\">%s</a>";
            strings.add(
                    String
                            .format(
                                    href,

                                    "https://github.com/ezhov-da/knowledge-base/blob/master/kb/"
                                            + toEncode(knowledge.getName()),
                                    knowledge.getName()));
        }

        Set<Map.Entry<String, List<String>>> entries = listMap.entrySet();

        for (Map.Entry<String, List<String>> stringListEntry : entries) {
            String tag = stringListEntry.getKey();

            StringBuilder stringBuilder = new StringBuilder();
            List<String> listLinks = stringListEntry.getValue();
            for (String s : listLinks) {
                stringBuilder.append(s);
                stringBuilder.append("<br/>\n");
            }

            File file = new File(pathToFolderHtml, tag + "_source.html");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(htmlCreator(stringBuilder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    private void createTags(Set<Knowledge> set) throws UnsupportedEncodingException {
        Map<String, Set<String>> listMap = new HashMap<String, Set<String>>();

        for (Knowledge knowledge : set) {
            String namePart = knowledge.getNamePart();
            Set<String> strings;
            if (listMap.containsKey(namePart)) {
                strings = listMap.get(namePart);
            } else {
                strings = new HashSet<String>();
                listMap.put(namePart, strings);
            }
            Set<String> tags = knowledge.getTags();

            for (String tag : tags) {
                strings.add(tag);
            }
        }

        Set<Map.Entry<String, Set<String>>> entries = listMap.entrySet();

        for (Map.Entry<String, Set<String>> stringListEntry : entries) {
            String partName = stringListEntry.getKey();

            StringBuilder stringBuilder = new StringBuilder();

            Set<String> valueTags = stringListEntry.getValue();
            for (String s : valueTags) {
                stringBuilder.append("<a href=\"/" + partName + "-" + toEncode(s) + ".html\">" + s + "</a>");
                stringBuilder.append("<br/>\n");
            }

            File file = new File(pathToFolderHtml, partName + "_tags.html");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(htmlCreator(stringBuilder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    private void createTagKnowledge(Set<Knowledge> set) throws UnsupportedEncodingException {
        Map<String, Set<Knowledge>> listMap = new HashMap<String, Set<Knowledge>>();

        for (Knowledge knowledge : set) {
            String namePart = knowledge.getNamePart();

            Set<String> tags = knowledge.getTags();

            for (String tag : tags) {

                String fileName = namePart + "-" + tag + ".html";

                Set<Knowledge> strings;
                if (listMap.containsKey(fileName)) {
                    strings = listMap.get(fileName);
                } else {
                    strings = new HashSet<Knowledge>();
                    listMap.put(fileName, strings);
                }

                strings.add(knowledge);
            }
        }


        Set<Map.Entry<String, Set<Knowledge>>> entries = listMap.entrySet();

        for (Map.Entry<String, Set<Knowledge>> stringListEntry : entries) {
            String nameFileConstruct = stringListEntry.getKey();

            StringBuilder stringBuilder = new StringBuilder();

            Set<Knowledge> valueTags = stringListEntry.getValue();
            for (Knowledge s : valueTags) {
                stringBuilder.append("<a href=\"" +
                        "https://github.com/ezhov-da/knowledge-base/blob/master/kb/" +
                        toEncode(s.getName()
                        ) + "\">" + s.getName() + "</a>");
                stringBuilder.append("<br/>\n");
            }

            File file = new File(pathToFolderHtml, nameFileConstruct);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(htmlCreator(stringBuilder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    private String htmlCreator(String body) {
        String text = "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"utf-8\">\n" +
                "<!-- Latest compiled and minified CSS -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css\" integrity=\"sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd\" crossorigin=\"anonymous\">\n" +
                "\t</head>\n" +
                "\t\n" +
                "\t<body>\n" +
                "\t\t%s\n" +

                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\"></script>\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js\" integrity=\"sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7\" crossorigin=\"anonymous\"></script>" +

                "\t</body>\n" +
                "\n" +
                "</html>";
        return String.format(text, body);
    }

    private String toEncode(String source) throws UnsupportedEncodingException {
        return URLEncoder.encode(source, "UTF-8");
    }
}
