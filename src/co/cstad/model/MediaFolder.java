package co.cstad.model;

public class MediaFolder {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer folderTd;

    public MediaFolder() {
    }

    public MediaFolder(Integer id, String name, Integer userId, Integer folderTd) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.folderTd = folderTd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFolderId() {
        return folderTd;
    }

    public void setFolderId(Integer folderTd) {
        this.folderTd = folderTd;
    }
}
