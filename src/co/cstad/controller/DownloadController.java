package co.cstad.controller;

import co.cstad.service.DownloadService;
import co.cstad.service.DownloadServiceImp;

public class DownloadController {
    private final DownloadService downloadService;
    
    public DownloadController() {
        downloadService = new DownloadServiceImp();
    }
    public void downloadMedia(String mediaUrl, String format) throws Exception {
        downloadService.downloadMedia(mediaUrl, format);
    }
}
