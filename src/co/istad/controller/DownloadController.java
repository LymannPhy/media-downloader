package co.istad.controller;

import co.istad.service.DownloadService;
import co.istad.service.DownloadServiceImp;

public class DownloadController {
    private final DownloadService downloadService;
    
    public DownloadController() {
        downloadService = new DownloadServiceImp();
    }
    public void downloadMedia(String mediaUrl, String format) throws Exception {
        downloadService.downloadMedia(mediaUrl, format);
    }
}
