package ru.ezhov.knowledgeBase.loader;

public class GitInfo {
    private String nameRepo;
    private String folderKb;

    public GitInfo() {
    }

    public GitInfo(String nameRepo, String folderKb) {
        this.nameRepo = nameRepo;
        this.folderKb = folderKb;
    }

    public String getNameRepo() {
        return nameRepo;
    }

    public void setNameRepo(String nameRepo) {
        this.nameRepo = nameRepo;
    }

    public String getFolderKb() {
        return folderKb;
    }

    public void setFolderKb(String folderKb) {
        this.folderKb = folderKb;
    }
}
