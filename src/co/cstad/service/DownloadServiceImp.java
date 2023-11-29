package co.cstad.service;

public class DownloadServiceImp implements DownloadService {
    @Override
    public void downloadMedia(String mediaUrl, String format) throws Exception {
        String command = "";
        if(format.equalsIgnoreCase("mp3")) {
            // mp3
            command = "yt-dlp.exe -f 140 -o \"%(title)s.%(ext)s\" " + mediaUrl;
        }

        Process process = Runtime.getRuntime().exec(command);

        // Wait for the download process to finish
        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Failed to download video. Exit code: " + exitCode);
//        }
    }
}
