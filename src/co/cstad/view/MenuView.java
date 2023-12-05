package co.cstad.view;

import co.cstad.controller.MenuController;
import co.cstad.util.DownloadSingleton;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.Scanner;

public class MenuView {
    private final MenuController menuController;

    public MenuView() {
        menuController = DownloadSingleton.menuController();
    }
    public void showMenuView() {
        String options;
        System.out.println(
                        "      \n" +
                        "███    ███ ██    ██ ███████ ██  ██████     ██████   ██████  ██     ██ ███    ██ ██       ██████   █████  ██████  ███████ ██████  \n" +
                        "████  ████ ██    ██ ██      ██ ██          ██   ██ ██    ██ ██     ██ ████   ██ ██      ██    ██ ██   ██ ██   ██ ██      ██   ██ \n" +
                        "██ ████ ██ ██    ██ ███████ ██ ██          ██   ██ ██    ██ ██  █  ██ ██ ██  ██ ██      ██    ██ ███████ ██   ██ █████   ██████  \n" +
                        "██  ██  ██ ██    ██      ██ ██ ██          ██   ██ ██    ██ ██ ███ ██ ██  ██ ██ ██      ██    ██ ██   ██ ██   ██ ██      ██   ██ \n" +
                        "██      ██  ██████  ███████ ██  ██████     ██████   ██████   ███ ███  ██   ████ ███████  ██████  ██   ██ ██████  ███████ ██   ██ \n"
        );
        Scanner sc = new Scanner(System.in);
        Table table = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.addCell("".repeat(4) + "| D) -> Download" + "".repeat(4));
        table.addCell("".repeat(4) + " | F) -> File Management" + "".repeat(4));
        table.addCell("".repeat(4) + " | H) -> Download History |" + "".repeat(4));
        System.out.println(table.render());
        System.out.print("Enter option -> ");
        options = sc.nextLine().toLowerCase();
        menuController.option(options);
    }
}
