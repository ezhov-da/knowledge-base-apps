package ru.ezhov.knowledgeBase.transformer;

import ru.ezhov.knowledgeBase.common.classes.raw.Knowledge;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataTransformer;

import java.util.*;

public class SimpleDataTransformer implements DataTransformer<Knowledge, KnowledgeRaw> {
    private static final String KEY_TAG = "#kb:t";
    private static final String KEY_LINK = "#kb:l";


    public Set<Knowledge> transform(Set<KnowledgeRaw> set) throws Exception {
        return startTreatment(set);
    }

    private Set<Knowledge> startTreatment(Set<KnowledgeRaw> set) {
        Iterator<KnowledgeRaw> knowledgeRawIterator = set.iterator();
        Set<Knowledge> knowledges = new HashSet<Knowledge>();
        while (knowledgeRawIterator.hasNext()) {
            KnowledgeRaw knowledgeRaw = knowledgeRawIterator.next();
            Knowledge knowledge = createKnowledge(knowledgeRaw);
            knowledges.add(knowledge);
        }
        return knowledges;
    }

    private Knowledge createKnowledge(KnowledgeRaw knowledgeRaw) {
        Knowledge knowledge = new Knowledge();

        knowledge.setName(knowledgeRaw.getName());
        knowledge.setLinkToFile(knowledgeRaw.getLink());

        String textFromFile = knowledgeRaw.getText();

        Set<String> tagSet = new HashSet<String>();
        Set<String> linkSet = new HashSet<String>();

        Scanner scanner = new Scanner(textFromFile);

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            boolean containsTag = isContainsTag(row);
            if (containsTag) {
                fillList(row, KEY_TAG, tagSet);
            } else {
                boolean containsLinks = isContainsLink(row);
                if (containsLinks) {
                    fillList(row, KEY_LINK, linkSet);
                }
            }
        }

        treatmentNameToNamePartAndAddAnotherPartToTags(
                knowledge,
                knowledgeRaw.getName(),
                tagSet
        );

        knowledge.setLinks(linkSet);
        knowledge.setTags(tagSet);

        return knowledge;
    }

    private boolean isContainsTag(String row) {
        return isContainsKeyWord(row, KEY_TAG);
    }

    private boolean isContainsLink(String row) {
        return isContainsKeyWord(row, KEY_LINK);
    }

    private void treatmentNameToNamePartAndAddAnotherPartToTags(
            Knowledge knowledgeForFill,
            String name,
            Set<String> tagsForFill) {

        name = name.replaceAll("\\.kb", "");

        String[] strings = name.split("-");

        int length = strings.length;

        if (length > 0) {
            knowledgeForFill.setNamePart(strings[1]);

            tagsForFill.addAll(Arrays.asList(strings).subList(2, length));
        }
    }

    private boolean isContainsKeyWord(String row, String keyWord) {
        boolean flagContains;
        if (row != null && row.length() >= keyWord.length()) {
            flagContains = row.substring(0, keyWord.length()).equals(keyWord);
        } else {
            flagContains = false;
        }

        return flagContains;
    }

    private void fillList(String row, String keyWorld, Set<String> keyWordList) {
        String clearRow = row.substring(keyWorld.length() + 1);
        String[] keyWords = clearRow.split(" ");
        keyWordList.addAll(Arrays.asList(keyWords));
    }

}
