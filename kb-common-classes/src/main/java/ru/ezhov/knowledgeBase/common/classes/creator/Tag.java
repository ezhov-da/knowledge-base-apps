package ru.ezhov.knowledgeBase.common.classes.creator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tag implements Comparable<Tag> {
    private String name;
    private List<Link> linkList;

    public Tag(String name) {
        this.name = name;
        this.linkList = new ArrayList<Link>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLink(Link link) {
        if (!linkList.contains(link)) {
            linkList.add(link);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    public int compareTo(Tag o) {
        Collator collator = Collator.getInstance();
        return collator.compare(name, o.name);
    }

    public void sortLinks(){
        Collections.sort(linkList);
    }
}
