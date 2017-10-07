package ru.ezhov.knowledgeBase.common.classes;

import java.util.Set;

public class Knowledge {
    private String name;
    private String namePart;
    private Set<String> tags;
    private Set<String> links;
    private String linkToFile;

    public String getLinkToFile() {
        return linkToFile;
    }

    public void setLinkToFile(String linkToFile) {
        this.linkToFile = linkToFile;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getLinks() {
        return links;
    }

    public void setLinks(Set<String> links) {
        this.links = links;
    }

    @Override
    public String toString() {

        return "Knowledge: \n" +
                "\tname: " + name + "\n" +
                "\tnamePart: " + namePart + "\n" +
                "\ttags: " + tags + "\n" +
                "\tlinks: " + links + "\n" +
                "\tlinkToFile: " + linkToFile;
    }
}
