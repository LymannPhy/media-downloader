package co.cstad.dao;

import co.cstad.storage.Storage;
import co.cstad.util.DbUtil;
import co.cstad.util.DownloadSingleton;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class DownloadDaoImpl implements DownloadDao {
    private final Connection connection;
    private final Storage storage;

    public DownloadDaoImpl() {
        connection = DbUtil.getConnection();
        storage = DownloadSingleton.storage();
    }

    @Override
    public void save(String mediaUrl, String outputDirectory) throws Exception {
        String command = "yt-dlp.exe mp3 -o \"" + outputDirectory + "/%(title)s.%(ext)s\" -f 140 --parse-metadata \"title:%(artist)s - %(title)s\" " + mediaUrl;

        // Process to execute command
        Process process = Runtime.getRuntime().exec(command);
        Thread.sleep(20000);

        // Get a list of all files in the directory
        File directory = new File(outputDirectory);
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            // Sort the files based on the last modified timestamp in descending order
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            // Get the first element, which represents the last added (or modified) file
            File lastAddedFile = files[0];

            // Get File Title and Extension
            String fileName = lastAddedFile.getName();
            int lastDotIndex = fileName.lastIndexOf('.');
            String fileTitle = (lastDotIndex <= 0) ? fileName : fileName.substring(0, lastDotIndex);
            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();

            // Check for known music file extensions
            fileExtension = switch (fileExtension) {
                case "mp3" -> "mp3";
                case "wav" -> "wav";
                case "ogg" -> "ogg";
                default -> fileExtension;
            };

            // Insert into medias, media_folders, and downloads in a single transaction
            try (PreparedStatement mediaStatement = connection.prepareStatement("INSERT INTO medias (title, artists, album, url, media_type, file_format, category_id, user_id, uploaded_at, file_size, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
                 PreparedStatement folderStatement = connection.prepareStatement("INSERT INTO media_folders (name, user_id, folder_id) VALUES (?, ?, ?) RETURNING *");
                 PreparedStatement downloadStatement = connection.prepareStatement("INSERT INTO downloads (status, is_deleted, media_id, user_id) VALUES (?, ?, ?, ?) RETURNING *")) {

                connection.setAutoCommit(false); // Start a transaction

                // Set parameters for medias table
                long fileSizeInBytes = lastAddedFile.length();
                double fileSizeInMB = (double) fileSizeInBytes / (1024 * 1024);
                mediaStatement.setString(1, fileTitle);
                mediaStatement.setString(2, null);
                mediaStatement.setString(3, null);
                mediaStatement.setString(4, mediaUrl);
                mediaStatement.setString(5, "Music");
                mediaStatement.setString(6, fileExtension);
                mediaStatement.setInt(7, storage.getOption());
                mediaStatement.setInt(8, storage.getId());
                mediaStatement.setDate(9, Date.valueOf(LocalDate.now()));
                mediaStatement.setInt(10, (int) fileSizeInMB);
                mediaStatement.setString(11, "Download from Youtube");

                // Execute INSERT for medias and get generated ID
                ResultSet mediaResultSet = mediaStatement.executeQuery();
                if (mediaResultSet.next()) {
                    storage.setMediaId(mediaResultSet.getInt("id"));
                }

                // Set parameters for media_folders
                folderStatement.setString(1, storage.getOptionFilePath());
                folderStatement.setInt(2, storage.getId());
                folderStatement.setInt(3, storage.getOption());
                folderStatement.executeQuery();

                // Set parameters for downloads
                downloadStatement.setString(1, "Success");
                downloadStatement.setBoolean(2, false);
                downloadStatement.setInt(3, storage.getMediaId());
                downloadStatement.setInt(4, storage.getId());
                downloadStatement.executeQuery();

                connection.commit(); // Commit the transaction
            } catch (Exception e) {
                connection.rollback(); // Rollback in case of an exception
                System.out.println(e.getMessage());
            } finally {
                connection.setAutoCommit(true); // Reset auto-commit mode
            }
        } else {
            System.out.println("No files found in the directory.");
        }
    }

//    public void save(String mediaUrl, String outputDirectory) throws Exception {
//        String command = "yt-dlp.exe mp3 -o \"" + outputDirectory + "/%(title)s.%(ext)s\" -f 140 --parse-metadata \"title:%(artist)s - %(title)s\" " + mediaUrl;
//
//        // Process to execute command
//        Process process = Runtime.getRuntime().exec(command);
//        Thread.sleep(20000);
//
//        // Get a list of all files in the directory
//        File directory = new File(outputDirectory);
//        File[] files = directory.listFiles();
//
//        if (files != null && files.length > 0) {
//            // Sort the files based on the last modified timestamp in descending order
//            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
//
//            // Get the first element, which represents the last added (or modified) file
//            File lastAddedFile = files[0];
//
//            // Get File Title
//            String fileName = lastAddedFile.getName();
//
//            // Get the index of the last dot to find the position of the file extension
//            int lastDotIndex = fileName.lastIndexOf('.');
//
//            // If there is no dot or the dot is at the beginning of the file name, return the entire name
//            String fileTitle = (lastDotIndex <= 0) ? fileName : fileName.substring(0, lastDotIndex);
//            // Get File Extension
//            String fileExtensionName = lastAddedFile.getName();
//            int lastDotIndex1 = fileExtensionName.lastIndexOf('.');
//            String fileExtension = fileExtensionName.substring(lastDotIndex1 + 1).toLowerCase();
//
//            // Check for known music file extensions
//            fileExtension = switch (fileExtension) {
//                case "mp3" -> "mp3";
//                case "wav" -> "wav";
//                case "ogg" -> "ogg";
//                default -> fileExtension;
//            };
//            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medias (title, artists, album, url, media_type, file_format, category_id, user_id, uploaded_at, file_size, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id")) {
//                long fileSizeInBytes = lastAddedFile.length();
//                double fileSizeInMB = (double) fileSizeInBytes / (1024 * 1024);
//
//                preparedStatement.setString(1, fileTitle);
//                preparedStatement.setString(2, null);
//                preparedStatement.setString(3, null);
//                preparedStatement.setString(4, mediaUrl);
//                preparedStatement.setString(5, "Music");
//                preparedStatement.setString(6, fileExtension);
//                preparedStatement.setInt(7, storage.getOption());
//                preparedStatement.setInt(8, storage.getId());
//                preparedStatement.setDate(9, Date.valueOf(LocalDate.now()));
//                preparedStatement.setInt(10, (int) fileSizeInMB);
//                preparedStatement.setString(11, "Download from Youtube");
//
//                preparedStatement.executeQuery();
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//                if(resultSet.next()) {
//                    storage.setMediaId(resultSet.getInt("id"));
//                }
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO media_folders (name, user_id, folder_id) VALUES (?, ?, ?) RETURNING *")) {
//                preparedStatement.setString(1, storage.getOptionFilePath());
//                preparedStatement.setInt(2, storage.getId());
//                preparedStatement.setInt(3, storage.getOption());
//                preparedStatement.executeQuery();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO downloads (status, is_deleted, media_id, user_id) VALUES (?, ?, ?, ?) RETURNING *")) {
//                preparedStatement.setString(1, "Success");
//                preparedStatement.setBoolean(2, false);
//                preparedStatement.setInt(3, storage.getMediaId());
//                preparedStatement.setInt(4, storage.getId());
//
//
//                preparedStatement.executeQuery();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } else {
//            System.out.println("No files found in the directory.");
//        }
//    }
}
