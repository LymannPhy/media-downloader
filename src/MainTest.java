import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import static co.cstad.util.Validation.isInteger;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option, outputDirectory = "";
        try {
            // Prompt the user for the YouTube video URL
            System.out.print("Enter YouTube video URL: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String videoUrl = reader.readLine();
            System.out.println("1. Pop");
            System.out.println("2. Rock");
            System.out.println("3. Game");
            do {
                System.out.print("Enter the output directory option: ");
                option = scanner.nextLine();
            } while (!isInteger(option));

            switch (Integer.parseInt(option)) {
                case 1 -> {
                    String popDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Pop";
                    File directory = new File(popDirectory);
                    if(!directory.exists()) {
                        directory.mkdir();
                        outputDirectory = popDirectory;
                    }
                }

                case 2 -> {
                    String rockDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Rock";
                    File directory = new File(rockDirectory);
                    if(!directory.exists()) {
                        directory.mkdir();
                        outputDirectory = rockDirectory;
                    }
                }

                case 3 -> {
                    String gameDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Game";
                    File directory = new File(gameDirectory);
                    if(!directory.exists()) {
                        directory.mkdir();
                        outputDirectory = gameDirectory;
                    }
                }
            }
            System.out.print("Enter the output file format (mp3 or mp4): ");
            String format = scanner.nextLine();

            // Download the video using youtube-dl command
            downloadVideo(videoUrl, outputDirectory, format);

            System.out.println("Video downloaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadVideo(String videoUrl, String outputDirectory, String format) throws Exception {
        String command = "";
        if(format.equalsIgnoreCase("mp3")) {
            // mp3
            command = "yt-dlp.exe -o \"" + outputDirectory + "/%(title)s.%(ext)s\" -f 140 " + videoUrl;
        } else if(format.equalsIgnoreCase("mp4")) {
            // mp4
            command = "yt-dlp.exe -o \"" + outputDirectory + "/%(title)s.%(ext)s\" -f mp4 " + videoUrl;
        }

        Process process = Runtime.getRuntime().exec(command);

        // Wait for the download process to finish
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to download video. Exit code: " + exitCode);
        }
    }
}
