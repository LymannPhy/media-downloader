package co.istad.util;

import co.istad.controller.DownloadController;
import co.istad.service.DownloadServiceImp;

import java.util.Scanner;

public class DownloadSingleton {
    public static Scanner scanner;
    public static DownloadController downloadController;
    public static DownloadServiceImp downloadServiceImp;

    public static DownloadController downloadController() {
        if(downloadController == null) {
            downloadController = new DownloadController();
        }
        return downloadController;
    }

    public static DownloadServiceImp downloadServiceImp() {
        if(downloadServiceImp == null) {
            downloadServiceImp = new DownloadServiceImp();
        }
        return downloadServiceImp;
    }

    public static Scanner scanner() {
        if(scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
