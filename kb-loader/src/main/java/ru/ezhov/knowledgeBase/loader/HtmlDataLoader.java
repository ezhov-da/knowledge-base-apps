package ru.ezhov.knowledgeBase.loader;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;

class HtmlDataLoader implements DataLoader<KnowledgeRaw, GitInfo> {


    public Set<KnowledgeRaw> load(GitInfo gitInfo) throws Exception {
        GitHub github = GitHub.connect();
        GHRepository ghRepository = github.getRepository(gitInfo.getNameRepo());
        List<GHContent> ghContentList = ghRepository.getDirectoryContent(gitInfo.getFolderKb());
        Set<KnowledgeRaw> knowledgeRaws = new HashSet<KnowledgeRaw>(1000);
        for (GHContent ghContent : ghContentList) {
            KnowledgeRaw knowledgeRaw = new KnowledgeRaw();
            knowledgeRaw.setLink(ghContent.getHtmlUrl());
            knowledgeRaw.setName(ghContent.getName());
            String text = getText(ghContent.read());
            knowledgeRaw.setText(text);
            knowledgeRaws.add(knowledgeRaw);
        }
        return knowledgeRaws;
    }

    private String getText(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(
                    new BufferedInputStream(inputStream),
                    "UTF-8"
            );
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                stringBuilder.append(row);
                stringBuilder.append("\n");
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return stringBuilder.toString();
    }
}
