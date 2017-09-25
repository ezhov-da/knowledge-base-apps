package ru.ezhov.knowledgeBase.common.classes.creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item {
    private List<Tag> tagList;

    public Item() {
        this.tagList = new ArrayList<Tag>();
    }

    public void addLink(Tag tag) {
        if (!tagList.contains(tag)) {
            tagList.add(tag);
        }
    }

    public void sortTags(){
        Collections.sort(tagList);
    }
}
