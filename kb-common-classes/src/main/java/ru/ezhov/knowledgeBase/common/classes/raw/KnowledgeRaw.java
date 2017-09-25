package ru.ezhov.knowledgeBase.common.classes.raw;

public class KnowledgeRaw {
    private String name;
    private String link;
    private String text;


    public KnowledgeRaw(String name, String link, String text) {
        this.name = name;
        this.link = link;
        this.text = text;
    }

    public KnowledgeRaw() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
