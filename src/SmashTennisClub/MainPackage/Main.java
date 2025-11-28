package SmashTennisClub.MainPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaGenerator;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.UserPackage.Chairman;
import SmashTennisClub.MainPackage.UserPackage.Treasurer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chairman chairman = new Chairman();
        Treasurer treasurer = new Treasurer();
        FileHandler fileHandler = new FileHandler();
        QuotaGenerator qg = new QuotaGenerator();
//        Treasurer treasurer = new Treasurer();
//        ArrayList<Quota> embers = new ArrayList<Quota>();
//        embers.add(new Quota(10, 15,"lars", 1500.00,true,
//                2025,03, 05, 2025,06,06"));


        //qg.generateQuotasFromMembers();
        // chairman.createMemberTest();
//        fileHandler.printAllMembers();
//        chairman.deleteMember();
//        treasurer.searchForQuota();




        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) { // 5 will be the exit option
            System.out.println("\n=== Menu ===");
            System.out.println("1. Søg efter medlem");
            System.out.println("2. Slet et medlem");
            System.out.println("3. Vis kontingenter");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    chairman.searchForMember();
                    break;
                case 2:
                    chairman.deleteMember();
                    break;
                case 3:
                    treasurer.printAllQuotas();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }
}

