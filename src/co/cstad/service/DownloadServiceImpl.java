package co.cstad.service;

import co.cstad.dao.DownloadDao;
import co.cstad.dao.DownloadDaoImpl;

public class DownloadServiceImpl implements DownloadService {
    private final DownloadDao downloadDao;

    public DownloadServiceImpl() {
        downloadDao = new DownloadDaoImpl();
    }
    @Override
    public void downloadMedia(String mediaUrl, String outputDirectory) throws Exception {
        downloadDao.save(mediaUrl, outputDirectory);
    }
}
