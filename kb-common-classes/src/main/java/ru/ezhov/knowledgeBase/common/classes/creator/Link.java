package ru.ezhov.knowledgeBase.common.classes.creator;

import java.text.Collator;

public class Link implements Comparable<Link> {
    private String name;
    private String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int compareTo(Link o) {
        Collator collator = Collator.getInstance();
        return collator.compare(name, o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return name.equals(link.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}