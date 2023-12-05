package co.cstad.util;

import java.io.File;

public class DownloadExtension {
    public static String getFileType(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');

        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();

            // Check for known music file extensions
            if (fileExtension.equals("mp3")) {
                return "MP3";
            } else if (fileExtension.equals("wav")) {
                return "WAV";
            } else if (fileExtension.equals("ogg")) {
                return "OGG";
            } else if (fileExtension.equals("m4a")) {
                return "m4a";
            }
            // If the file type is not recognized
            return "Unknown";
        } else {
            // No file extension found
            return "This file extension doesn't existed.";
        }
    }
}
