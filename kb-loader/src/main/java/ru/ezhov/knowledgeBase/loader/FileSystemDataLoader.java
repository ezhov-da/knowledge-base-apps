package ru.ezhov.knowledgeBase.loader;

import ru.ezhov.knowledgeBase.common.classes.raw.KnowledgeRaw;
import ru.ezhov.knowledgeBase.common.interfaces.DataLoader;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileSystemDataLoader implements DataLoader<KnowledgeRaw, GitInfo> {


    public Set<KnowledgeRaw> load(GitInfo gitInfo) throws Exception {

        String pathToKb = gitInfo.getNameRepo() + File.separator + gitInfo.getFolderKb();

        File fileKb = new File(pathToKb);

        File[] files = fileKb.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".kb");
            }
        });

        Set<KnowledgeRaw> knowledgeRaws = new HashSet<KnowledgeRaw>(1000);

        for (File f : files) {
            KnowledgeRaw knowledgeRaw = new KnowledgeRaw();
            knowledgeRaw.setLink(f.getAbsolutePath());
            knowledgeRaw.setName(f.getName());
            String text = getText(new FileInputStream(f));
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
