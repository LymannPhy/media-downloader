package co.cstad.model;

import java.time.LocalDate;

public class Download {
    private Integer id;
    private String status;
    private Boolean isDeleted;
    private Integer mediaId;
    private Integer userId;
    private LocalDate downloadTime;

    public Download() {
    }

    public Download(Integer id, String status, Boolean isDeleted, Integer mediaId, Integer userId, LocalDate downloadTime) {
        this.id = id;
        this.status = status;
        this.isDeleted = isDeleted;
        this.mediaId = mediaId;
        this.userId = userId;
        this.downloadTime = downloadTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDownloadTime() {
        return downloadTime;
    }

    public void setDownload_time(LocalDate download_time) {
        this.downloadTime = download_time;
    }
}
