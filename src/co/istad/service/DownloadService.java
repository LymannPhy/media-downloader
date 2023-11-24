package co.istad.service;

import java.io.IOException;

public interface DownloadService {
    void downloadMedia(String mediaUrl, String format) throws Exception;
}
