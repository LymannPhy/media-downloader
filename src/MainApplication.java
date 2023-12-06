import co.cstad.util.CategorySeeder;
import co.cstad.util.DownloadSingleton;

import java.util.*;

import co.cstad.util.FolderSeeder;
import co.cstad.view.UserView;

public class MainApplication {
    private final Scanner scanner;
    private final CategorySeeder categorySeeder;
    private final FolderSeeder folderSeeder;
    private final UserView userView;
    public MainApplication() {
        scanner = DownloadSingleton.scanner();
        categorySeeder = DownloadSingleton.categorySeeder();
        categorySeeder.categorySeeder();
        folderSeeder = DownloadSingleton.folderSeeder();
        folderSeeder.folderSeeder();
        userView = DownloadSingleton.userView();
    }

    public void run() {
        int userInput;

        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1. Registration");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            // Validate user input in the same loop
            while (true) {
                System.out.print("Please an option: ");

                if (scanner.hasNextInt()) {
                    userInput = scanner.nextInt();
                    break; // Break out of the inner loop if a valid integer is entered
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Use the validated input in the switch statement
            switch (userInput) {
                case 1 -> {
                    userView.showRegistrationMenu();
                }
                case 2 -> {
                    userView.showLoginMenu();
                    categorySeeder.categorySeeder();
                    folderSeeder.folderSeeder();
                }
                case 3 -> {
                    System.out.println("Existing the application. Goodbye!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice. Please enter a valid option.");

                }
            }

            System.out.print("Do you want to continue? (y/n): ");
            String continueInput = scanner.next();

            if (!continueInput.equalsIgnoreCase("y")) {
                break;
            }
        }
//        String option;
//        int concurrentDownloads = 1;
//        List<String> mediaUrls = new ArrayList<>();
//        // Prompt the user for the YouTube video URL
//        for(int i = 0; i < concurrentDownloads; i++) {
//            System.out.print("Enter YouTube video URL: ");
//            String url = scanner.nextLine();
//            System.out.println("1. Pop");
//            System.out.println("2. Rock");
//            System.out.println("3. Game");
//            do {
//                System.out.print("Enter the output directory option: ");
//                option = scanner.nextLine();
//            } while (!isInteger(option));
//
//            switch (Integer.parseInt(option)) {
//                case 1 -> {
//                    String popDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Pop";
//                    File directory = new File(popDirectory);
//                    if(!directory.exists()) {
//                        directory.mkdir();
//                        outputDirectory = popDirectory;
//                    } else {
//                        outputDirectory = popDirectory;
//                    }
//                }
//
//                case 2 -> {
//                    String rockDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Rock";
//                    File directory = new File(rockDirectory);
//                    if(!directory.exists()) {
//                        directory.mkdir();
//                        outputDirectory = rockDirectory;
//                    } else {
//                        outputDirectory = rockDirectory;
//                    }
//                }
//
//                case 3 -> {
//                    String gameDirectory = System.getProperty("user.home") + File.separatorChar + "Music\\EasyMp3\\Game";
//                    File directory = new File(gameDirectory);
//                    if(!directory.exists()) {
//                        directory.mkdir();
//                        outputDirectory = gameDirectory;
//                    } else {
//                        outputDirectory = gameDirectory;
//                    }
//                }
//            }
//            mediaUrls.add(url);
//
//        }
//        String format = "mp3";
//
//        // Concurrency
//        ExecutorService executorService = Executors.newFixedThreadPool(concurrentDownloads);
//        List<Future<Void>> futures = new ArrayList<>();
//        for (String mediaUrl : mediaUrls) {
//            Callable<Void> downloadTask = () -> {
//                downloadController.downloadMedia(mediaUrl, outputDirectory, format);
//                return null;
//            };
//            Future<Void> future = executorService.submit(downloadTask);
//            futures.add(future);
//        }
//
//        // Wait for all tasks to complete
//        for (Future<Void> future : futures) {
//            try {
//                future.get();
//                System.out.println("Download Successfully");
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Shut down the executor service
//        executorService.shutdown();
//
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
//            try(Connection connection = DbUtil.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"public\".\"medias\" (\"title\", \"artists\", \"album\", \"url\", \"media_type\", \"file_format\", \"category_id\", \"user_id\", \"uploaded_at\", \"file_size\", \"description\") VALUES ('test', 'test', 'test', 'test.com', 'music', 'mp3', 1, 1, '2023-12-01 16:45:16', 3, 'song')")) {
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

    public static void main(String[] args) {
        new MainApplication().run();
    }
}
