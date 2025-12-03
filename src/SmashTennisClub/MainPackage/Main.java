package SmashTennisClub.MainPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaGenerator;
import SmashTennisClub.MainPackage.PlayerStatistic.CompetitivePlayerHelper;
import SmashTennisClub.MainPackage.UserPackage.Chairman;
import SmashTennisClub.MainPackage.UserPackage.Coach;
import SmashTennisClub.MainPackage.UserPackage.Treasurer;
import SmashTennisClub.MainPackage.UserPackage.UserHelperClass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Chairman chairman = new Chairman();
        Coach coach = new Coach();
        Treasurer treasurer = new Treasurer();

        while (running) {
            System.out.println("\n===== SMASH TENNIS CLUB | BRUGER MENU =====");
            System.out.println("1. Log ind som Formand (Chairman)");
            System.out.println("2. Log ind som Coach (Coach)");
            System.out.println("3. Log ind som Kasserer (Treasurer)");
            System.out.println("0. Afslut program");
            System.out.print("Vælg en rolle (0-3): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Logger ind som Formand ---");
                    chairman.chairmanMenu();
                    break;

                case "2":
                    System.out.println("\n--- Logger ind som Coach ---");
                    coach.coachMenu();
                    break;

                case "3":
                    System.out.println("\n--- Logger ind som Kasserer ---");
                    treasurer.treasurerMenu();
                    break;

                case "0":
                    System.out.println("Program afsluttes...");
                    running = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }

        scanner.close();
    }
}

