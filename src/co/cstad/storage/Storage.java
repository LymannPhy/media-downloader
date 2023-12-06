package co.cstad.storage;

public class Storage {
    private Integer id;
    private Integer option;
    private String optionFilePath;
    private Integer mediaId;

    public Storage(Integer id, Integer option, String optionFilePath, Integer mediaId) {
        this.id = id;
        this.option = option;
        this.optionFilePath = optionFilePath;
        this.mediaId = mediaId;
    }

    public Storage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public String getOptionFilePath() {
        return optionFilePath;
    }

    public void setOptionFilePath(String optionFilePath) {
        this.optionFilePath = optionFilePath;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
