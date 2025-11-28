package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Chairman {
    MemberReader reader = new MemberReader();
    MemberWriter writer = new MemberWriter();

    private ArrayList<Member> members = reader.readFromFile();
//    private int memberId = members.getLast().getMemberId() + 1;




//    public createMemberTest() {
//        ArrayList<Member> members = new ArrayList<>();
//        Member member = new Member(memberId++, customerName, customerPhone, date, time, selectedProducts, totalPrice);
//        members.add(member);
//        System.out.println("New member added: " + member);
//        writer.saveMembers(members);
//    }




   public void deleteMember() {
        FileHandler fileHandler = new FileHandler();
       Scanner input = new Scanner(System.in);

       while (true) {
           System.out.println("--- Slet medlem ---");
           System.out.println("Indtast medlemsID: ");

           if (input.hasNextInt()) {
               int memberIdToDelete = input.nextInt();
               input.nextLine();


               boolean isFound = false;
               for (Member m : members) {
                   if (m.getMemberId() == memberIdToDelete) {
                       isFound = true;
                       System.out.println("Følgende medlem vil blive slettet:");
                       System.out.println(m);
                       System.out.print("Er du sikker? (ja/nej): ");
                       String confirmation = input.nextLine();

                       if (confirmation.equalsIgnoreCase("ja")) {
                           members.remove(m);
                           System.out.println("Medlem er nu slettet!\n");
                           fileHandler.saveMembers(members);
                           return;
                       } else {
                           System.out.println("Sletning annulleret.");
                           return;
                       }
                   }
               }

               if (!isFound) {
                   System.out.println("Ingen medlemmer er fundet med følgende id: " + memberIdToDelete);
               }
           } else {
               System.out.println("Ugyldigt input! Indtast et tal.");
               input.nextLine();
           }
       }
   }




    public void searchForMember() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Søg efter medlem ---");
            System.out.println("Indtast venligst medlems ID på personen som du ønsker at søge efter!");
            System.out.println("(For at stoppe søgning, søg efter 0)");
            System.out.println("Indtast medlemsID: ");

            if (input.hasNextInt()) {
                int memberIdToSearchFor = input.nextInt();
                input.nextLine();

                // Exit condition
                if (memberIdToSearchFor == 0) {
                    System.out.println("Afslutter søgning...");
                    break;
                }

                boolean isFound = false;
                for (Member m : members) {
                    if (m.getMemberId() == memberIdToSearchFor) {
                        isFound = true;
                        System.out.println("Følgende medlem er fundet: ");
                        System.out.println(m);
                        System.out.println();
                    }

            }
                if (isFound == false) {
                    System.out.println("Ingen medlemmer er fundet med følgende id: " + memberIdToSearchFor);
                    return;
                }
            } else {
                System.out.println("Ugyldigt input! Indtast et tal.");
                input.nextLine();
            }

        }
    }



    public void editMember() {

        //fileHandler.saveMembers(members);
    }


    public void printAllMembers() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllMembers();
    }

}
