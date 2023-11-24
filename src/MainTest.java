//import java.io.IOException;
//import java.net.URL;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the YouTube video URL: ");
//        String videoUrl = scanner.nextLine();
//        System.out.print("Enter the output file name: ");
//        String fileName = scanner.nextLine();
//        System.out.print("Enter the output file format (mp3 or mp4): ");
//        String format = scanner.nextLine();
//
//        String command = "yt-dlp -o \""+ ".%(ext)s\" --audio-format " + format + " " + videoUrl;
//        Process process = Runtime.getRuntime().exec(command);
//        scanner.close();
//    }
//}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Prompt the user for the YouTube video URL
            System.out.print("Enter YouTube video URL: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String videoUrl = reader.readLine();
            System.out.print("Enter the output file format (mp3 or mp4): ");
            String format = scanner.nextLine();

            // Download the video using youtube-dl command
            downloadVideo(videoUrl, format);

            System.out.println("Video downloaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadVideo(String videoUrl, String format) throws Exception {
        String command = "";
        if(format.equalsIgnoreCase("mp3")) {
            // mp3
            command = "yt-dlp.exe -f 140 " + videoUrl;
        } else if(format.equalsIgnoreCase("mp4")) {
            // mp4
            command = "yt-dlp.exe -S ext " + format + " " + videoUrl;
        }

        Process process = Runtime.getRuntime().exec(command);

        // Wait for the download process to finish
        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Failed to download video. Exit code: " + exitCode);
//        }
    }
}

