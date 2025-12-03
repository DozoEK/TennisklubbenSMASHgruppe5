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
        Chairman chairman = new Chairman();
        Treasurer treasurer = new Treasurer();
        FileHandler fileHandler = new FileHandler();
        QuotaGenerator qg = new QuotaGenerator();
        UserHelperClass uhc = new UserHelperClass();
        Coach coach = new Coach();
        CompetitivePlayerHelper cph = new CompetitivePlayerHelper();



        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 9) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Søg efter medlem");
            System.out.println("2. Slet et medlem");
            System.out.println("3. Vis kontingenter");
            System.out.println("4. Print alle medlemmer");
            System.out.println("5. Opret nyt medlem");
            System.out.println("6. Opret PlayerEntry");
            System.out.println("7. Rediger medlem");
            System.out.println("8. Create Quota");
            System.out.println("9. Register payment");
            System.out.println("10. CompetitivePlayerHelper - create player stats");
            System.out.println("11. updateSetsStatisticsFromPlayerEntries");
            System.out.println("12. calculateSetWinRates()");
            System.out.println("13. updateTournamentStatistics");
            System.out.println("14. showTop5All");
            System.out.println("0. Exit / slut programmet");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    uhc.searchForMember();
                    break;
                case 2:
                    chairman.deleteMember();
                    break;
                case 3:
                    treasurer.printAllQuotas();
                    break;
                case 4:
                    uhc.printAllMembers();
                    break;
                case 5:
                    chairman.createAnyMember(scanner);
                    break;
                case 6:
                    coach.createPlayerEntry(scanner);
                    break;
                case 7:
                    chairman.editMember();
                    break;
                case 8:
                    treasurer.createQuotaForMember();
                    break;
                case 9:
                    treasurer.registerPaymentForMember();
                    break;
                case 10:
                    cph.createStatsFromCompetitiveMembers();
                    break;
                case 11:
                    cph.updateSetsStatisticsFromPlayerEntries();
                    break;
                case 12:
                    cph.calculateSetWinRates();
                    break;
                case 13:
                    cph.updateTournamentStatistics();
                    break;
                case 14:
                    cph.showTop5All();
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }
}

