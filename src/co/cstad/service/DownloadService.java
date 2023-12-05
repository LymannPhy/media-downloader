package co.cstad.service;

public interface DownloadService {
    void downloadMedia(String mediaUrl, String outputDirectory, String format) throws Exception;
}
