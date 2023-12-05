package co.cstad.dao;

import co.cstad.util.DbUtil;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class DownloadDaoImpl implements DownloadDao {
    public final Connection connection;

    public DownloadDaoImpl() {
        connection = DbUtil.getConnection();
    }

    @Override
    public void save(String mediaUrl, String outputDirectory, String format) throws Exception {
        String command = "";
        if(format.equalsIgnoreCase("mp3")) {
            // mp3
            command = "yt-dlp.exe -o \"" + outputDirectory + "/%(title)s.%(ext)s\" -f 140 " + mediaUrl;
        }

        Process process = Runtime.getRuntime().exec(command);
        Thread.sleep(10000);
        // Get a list of all files in the directory
        File directory = new File(outputDirectory);
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            // Sort the files based on the last modified timestamp in descending order
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            // Get the first element, which represents the last added (or modified) file
            File lastAddedFile = files[0];

            // Now you can read or process the last added file as needed
            System.out.println("Last added file: " + lastAddedFile.getAbsolutePath());
        } else {
            System.out.println("No files found in the directory.");
        }
//        Thread.sleep(10000);
//
//        Optional<File> latestFile = Stream.of(new File(outputDirectory).listFiles())
//                .filter(File::isFile)
//                .max(Comparator.comparingLong(File::lastModified));
//
//        if (latestFile.isPresent()) {
//            File file = findUsingIOApi(outputDirectory);
//            Path path = file.toPath();
//
//            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
//            Instant creationTime = attributes.creationTime().toInstant();
//            LocalDateTime dateTime = LocalDateTime.ofInstant(creationTime, ZoneId.systemDefault());
//
//            System.out.println("Latest file: " + file.getName());
//            System.out.println("Creation time: " + dateTime);
//        } else {
//            System.out.println("No files found in the folder.");
//        }
//        // Create a File object representing the folder
//        File folder = new File(outputDirectory);
//        if (folder.exists() && folder.isDirectory()) {
//            File lastAddedFile = getLastAddedFile(folder);
//
//            if (lastAddedFile != null) {
//                // Process or read the last added file here
//                try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medias (title, artists, album, url, media_type, file_format, category_id, user_id, uploaded_at, file_size, description) VALUES ('test', 'test', 'test', 'test.com', 'music', 'mp3', 1, 1, '2023-12-01 16:45:16', 3, 'song') RETURNING *")) {
//                // Read audio file and extract metadata
//                AudioFile audioFile = AudioFileIO.read(lastAddedFile);
//                Tag tag = audioFile.getTag();
//
//                // Print metadata
//                System.out.println("File: " + lastAddedFile.getName());
//                System.out.println("Title: " + tag.getFirst(FieldKey.TITLE));
//                System.out.println("Artist: " + tag.getFirst(FieldKey.ARTIST));
//                System.out.println("Album: " + tag.getFirst(FieldKey.ALBUM));
//                // Add more fields as needed
//                preparedStatement.executeQuery();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            } else {
//                System.out.println("No files found in the folder.");
//            }
//        } else {
//            System.out.println("Invalid folder path.");
//        }
//        // List all files in the folder
//        File[] files = folder.listFiles();
//
//        // Sort files by last modified timestamp to get the last file
//        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
//
//        if (files.length > 0) {
//            // Get the last file
//            File lastFile = files[files.length - 1];
//            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medias (title, artists, album, url, media_type, file_format, category_id, user_id, uploaded_at, file_size, description) VALUES ('test', 'test', 'test', 'test.com', 'music', 'mp3', 1, 1, '2023-12-01 16:45:16', 3, 'song') RETURNING *")) {
//                // Read audio file and extract metadata
//                AudioFile audioFile = AudioFileIO.read(lastFile);
//                Tag tag = audioFile.getTag();
//
//                // Print metadata
//                System.out.println("File: " + lastFile.getName());
//                System.out.println("Title: " + tag.getFirst(FieldKey.TITLE));
//                System.out.println("Artist: " + tag.getFirst(FieldKey.ARTIST));
//                System.out.println("Album: " + tag.getFirst(FieldKey.ALBUM));
//                // Add more fields as needed
//                preparedStatement.executeQuery();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("No files found in the folder");
//        }
//        // Create a File object representing the folder
//        File folder = new File(outputDirectory);
//
//        // List all files in the folder
//        File[] files = folder.listFiles();
//
//        // Sort files by last modified timestamp to get the last file
//        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
//
//        if (files.length > 0) {
//            // Get the last file
//            File lastFile = files[files.length - 1];
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medias (title, artists, album, url, media_type, file_format, category_id, user_id, uploaded_at, file_size, description) VALUES ('test', 'test', 'test', 'test.com', 'music', 'mp3', 1, 1, '2023-12-01 16:45:16', 3, 'song')")) {
//                // Read audio file and extract metadata
//                AudioFile audioFile = AudioFileIO.read(lastFile);
//                Tag tag = audioFile.getTag();
//
//                // Print metadata
//                System.out.println("File: " + lastFile.getName());
//                System.out.println("Title: " + tag.getFirst(FieldKey.TITLE));
//                System.out.println("Artist: " + tag.getFirst(FieldKey.ARTIST));
//                System.out.println("Album: " + tag.getFirst(FieldKey.ALBUM));
//                // Add more fields as needed
//                preparedStatement.executeQuery();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("No files found in the folder");
//        }
    }
    public static Path findUsingNIOApi(String sdir) throws IOException {
        Path dir = Paths.get(sdir);
        if (Files.isDirectory(dir)) {
            Optional<Path> opPath = Files.list(dir)
                    .filter(p -> !Files.isDirectory(p))
                    .sorted((p1, p2)-> Long.valueOf(p2.toFile().lastModified())
                            .compareTo(p1.toFile().lastModified()))
                    .findFirst();

            if (opPath.isPresent()){
                return opPath.get();
            }
        }

        return null;
    }
}
