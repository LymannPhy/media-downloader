package co.cstad.controller;

import co.cstad.service.DownloadService;
import co.cstad.service.DownloadServiceImpl;

public class DownloadController {
    private final DownloadService downloadService;
    
    public DownloadController() {
        downloadService = new DownloadServiceImpl();
    }
    public void downloadMedia(String mediaUrl, String outputDirectory) throws Exception {
        downloadService.downloadMedia(mediaUrl, outputDirectory);
    }
}
