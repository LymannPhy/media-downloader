package co.cstad.controller;

import co.cstad.storage.Storage;
import co.cstad.util.DbUtil;
import co.cstad.util.DownloadSingleton;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.concurrent.*;

import static co.cstad.util.Validation.isInteger;
public class MenuController {
    private final DownloadController downloadController;
    private final Storage storage;
    private final Connection connection;

    public MenuController() {
        downloadController = DownloadSingleton.downloadController();
        storage = DownloadSingleton.storage();
        connection = DbUtil.getConnection();
    }
    List<String> mediaUrls = new ArrayList<>();
    String outputDirectory = "";
    public void option(String options) {
        boolean isDownload = false;
        switch (options) {
            case "d" -> {
                Scanner scanner = new Scanner(System.in);
                String option;
                System.out.println(storage.getId());
                int concurrentDownloads = 1;
                // Prompt the user for the YouTube video URL
                for(int i = 0; i < concurrentDownloads; i++) {
                    System.out.print("Enter YouTube video URL: ");
                    String url = scanner.nextLine();
                    System.out.println("1. Pop");
                    System.out.println("2. Rock");
                    System.out.println("3. Game");
                    do {
                        System.out.print("Enter the output directory option: ");
                        option = scanner.nextLine();
                    } while (!isInteger(option));
                    System.out.println(url);
                    switch (Integer.parseInt(option)) {
                        case 1 -> {
                            System.out.println(option);
                            String popDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Pop";
                            File directory = new File(popDirectory);
                            if(!directory.exists()) {
                                directory.mkdir();
                                outputDirectory = popDirectory;
                            } else {
                                outputDirectory = popDirectory;
                            }
                        }

                        case 2 -> {
                            String rockDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Rock";
                            File directory = new File(rockDirectory);
                            if(!directory.exists()) {
                                directory.mkdir();
                                outputDirectory = rockDirectory;
                            } else {
                                outputDirectory = rockDirectory;
                            }
                        }

                        case 3 -> {
                            String gameDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Game";
                            File directory = new File(gameDirectory);
                            if(!directory.exists()) {
                                directory.mkdir();
                                outputDirectory = gameDirectory;
                            } else {
                                outputDirectory = gameDirectory;
                            }
                        }
                    }
                    mediaUrls.add(url);

                }
                String format = "mp3";

                // Concurrency
                ExecutorService executorService = Executors.newFixedThreadPool(concurrentDownloads);
                List<Future<Void>> futures = new ArrayList<>();
                for (String mediaUrl : mediaUrls) {
                    Callable<Void> downloadTask = () -> {
                        downloadController.downloadMedia(mediaUrl, outputDirectory, format);
                        return null;
                    };
                    Future<Void> future = executorService.submit(downloadTask);
                    futures.add(future);
                }

                // Wait for all tasks to complete
                for (Future<Void> future : futures) {
                    try {
                        future.get();
                        System.out.println("Download Successfully");
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                // Shut down the executor service
                executorService.shutdown();
            }
            case "f" -> {
                System.out.println("File Management");
            }
            case "h" -> {
                System.out.println("Download History");
            }
        }
    }
}
