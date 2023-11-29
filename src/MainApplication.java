import co.cstad.controller.DownloadController;
import co.cstad.util.DownloadSingleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MainApplication {
    private final Scanner scanner;
    private final DownloadController downloadController;

    public MainApplication() {
        scanner = DownloadSingleton.scanner();
        downloadController = DownloadSingleton.downloadController();
    }

    public void run() {
        int concurrentDownloads = 1;
        List<String> mediaUrls = new ArrayList<>();
        // Prompt the user for the YouTube video URL
        for(int i = 0; i < concurrentDownloads; i++) {
            System.out.print("Enter YouTube video URL: ");
            String url = scanner.nextLine();
            mediaUrls.add(url);
        }
        String format = "mp3";

        // Concurrency
        ExecutorService executorService = Executors.newFixedThreadPool(concurrentDownloads);
        List<Future<Void>> futures = new ArrayList<>();
        for (String mediaUrl : mediaUrls) {
            Callable<Void> downloadTask = () -> {
                downloadController.downloadMedia(mediaUrl, format);
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

    public static void main(String[] args) {
        new MainApplication().run();
    }

    private static String getFileName(String url) {
        // Extract file name from the URL
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
