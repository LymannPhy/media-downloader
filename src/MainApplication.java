import co.cstad.controller.DownloadController;
import co.cstad.util.DownloadSingleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

//Import User Authenication
import co.cstad.controller.UserController;
import co.cstad.dao.UserDao;
import co.cstad.dao.UserDaoImpl;
import co.cstad.service.UserService;
import co.cstad.service.UserServiceImpl;
import co.cstad.view.LoginView;

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
//        new MainApplication().run();
        UserDao userDao = new UserDaoImpl(); // Update with your database implementation
        UserService userService = new UserServiceImpl(userDao);
        UserController userController = new UserController(userService);
        LoginView loginView = new LoginView(userController);

        Scanner scanner = new Scanner(System.in);
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
                    loginView.showRegistrationMenu();
                }
                case 2 -> {
                    loginView.showLoginMenu();
                }
                case 3 -> {
                    System.out.println("Existing the application. Goodbye!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice. Please enter a valid option.");

                }
            }

            // Ask the user if they want to continue
            System.out.print("Do you want to continue? (y/n): ");
            String continueInput = scanner.next();

            if (!continueInput.equalsIgnoreCase("y")) {
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    private static String getFileName(String url) {
        // Extract file name from the URL
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
