package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.MemberReader;
import SmashTennisClub.FileSystem.MemberWriter;
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



    public void createMemberTest() {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Member> members = new ArrayList<>();


        members.add(new Member(1, "TestName01", Gender.MALE, LocalDate.of(2024, 11, 22), 21, 22222, true, MembershipPricelist.JUNIOR,
                LocalDate.now(), true));
        members.add(new Member(2, "TestName02", Gender.FEMALE, LocalDate.parse("2024-07-12"), 23, 11111, true,
                MembershipPricelist.PASSIVT, LocalDate.now(), true));
        members.add(new Member(3, "TestNavn03", Gender.MALE, LocalDate.parse("1999-09-13"), 12, 555555, true,
                MembershipPricelist.JUNIOR, LocalDate.now(), true));


        fileHandler.saveMembers(members);
    }


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




    void searchForMember() {}
  //  public editMember() {}


    public void printAllMembers() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllMembers();
    }

}
