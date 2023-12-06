package co.cstad.dao;

public interface DownloadDao {
    void save(String mediaUrl, String outputDirectory) throws Exception;

}
