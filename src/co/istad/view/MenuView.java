package co.istad.view;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;

import java.util.Scanner;

public class MenuView {
    public static void welcomeMsg() {
        System.out.println(
                "\n" + "      \n" +
                        "███    ███ ██    ██ ███████ ██  ██████     ██████   ██████  ██     ██ ███    ██ ██       ██████   █████  ██████  ███████ ██████  \n" +
                        "████  ████ ██    ██ ██      ██ ██          ██   ██ ██    ██ ██     ██ ████   ██ ██      ██    ██ ██   ██ ██   ██ ██      ██   ██ \n" +
                        "██ ████ ██ ██    ██ ███████ ██ ██          ██   ██ ██    ██ ██  █  ██ ██ ██  ██ ██      ██    ██ ███████ ██   ██ █████   ██████  \n" +
                        "██  ██  ██ ██    ██      ██ ██ ██          ██   ██ ██    ██ ██ ███ ██ ██  ██ ██ ██      ██    ██ ██   ██ ██   ██ ██      ██   ██ \n" +
                        "██      ██  ██████  ███████ ██  ██████     ██████   ██████   ███ ███  ██   ████ ███████  ██████  ██   ██ ██████  ███████ ██   ██ \n"
        );
    }
    public static void createTable() {
        Scanner sc = new Scanner(System.in);
        org.nocrala.tools.texttablefmt.Table table = new org.nocrala.tools.texttablefmt.Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.addCell("".repeat(4) + "| D)->Download" + "".repeat(4));
        table.addCell("".repeat(4) + " | F)->File Management" + "".repeat(4));
        table.addCell("".repeat(4) + " | H)->Download History |" + "".repeat(4));
        System.out.println(table.render());
        System.out.print("Enter option ->");
        String options = sc.nextLine();
    }
}
