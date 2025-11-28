package SmashTennisClub.MainPackage.UserPackage.menu;
import SmashTennisClub.MainPackage.UserPackage.Chairman;
import SmashTennisClub.MainPackage.UserPackage.Treasurer;

import java.util.Scanner;

public class menu {
    Chairman chairman = new Chairman();
    Treasurer treasurer = new Treasurer();
    private boolean running;
    Scanner scanner = new Scanner(System.in);

    public void Menu() {
        this.scanner = new Scanner(System.in);
        this.running = true;
    }


    private void displayMenu() {
        System.out.println("\n SMASH Klubben ´test´ menu");
        System.out.println("1. Søg efter medlem");
        System.out.println("2. Slet et medlem");
        System.out.println("3. Vis betalte kontigenter ");
//        System.out.println("4. Vis ");
//        System.out.println("5. Vis Produkter ");
//        System.out.println("6. vis Budget (kræver password)");
        System.out.println("0. Afslut program");
//        System.out.println("Vælg funktion");
    }

    private int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("indtast venligst et tal.");
            return -1;
        }

    }

    //kalder den relevante metode ud fra brugerinput
    private void menuChoise(int choise) {
        switch (choise) {
            case 1 -> chairman.searchForMember();
            case 2 -> chairman.deleteMember();
            case 3 -> treasurer.showAllPaidQuotas();
//            case 4 -> ;
//            case 5 -> ;
//            case 6 -> ;
            case 0 -> {
                running = false;
            }
            default -> System.out.println("Vælg mellem 0-3");

        }
    }
}