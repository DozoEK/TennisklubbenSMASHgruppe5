package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class UserHelperClass {

    MemberReader reader = new MemberReader();
    MemberWriter writer = new MemberWriter();

    private ArrayList<Member> members = reader.readFromFile();


    //logik til at finde memeber id
    private Member findMemberById(int memberId) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        return null;
    }


    //logik til at finde member via navn
    private Member findMemberByName(String memberName) {
        for (Member m : members) {
            if (m.getMemberName().equalsIgnoreCase(memberName)) {
                return m;
            }
        }
        return null;
    }



    public void searchForMember() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Søg efter medlem ---");
            System.out.println("Indtast medlems ID eller navn: ");
            System.out.println("(skriv 'EXIT' for at afslutte!)");

            if (input.hasNextInt()) {
                int memberId = input.nextInt();
                input.nextLine();
                Member fundetMember = findMemberById(memberId);

                if (fundetMember != null) {
                    System.out.println("Medlem fundet:");
                    System.out.println(fundetMember);

                } else {
                    System.out.println("Ingen medlemmer fundet med ID: " + memberId);
                }


            } else {
                String name = input.next();

                Member fundetMember = findMemberByName(name);

                if (fundetMember != null) {
                    System.out.println("Fundet medlem:");
                    System.out.println(fundetMember);

                } else {
                    System.out.println("Ingen medlemmer med navnet: " + name + " er fundet.");
                }

                if (name.equalsIgnoreCase("exit")) {
                    System.out.println("Afslutter søgning...");
                    break;
                }
            }
        }
    }


    public void printAllMembers () {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllMembers();
    }


    //vi skal sætte den et eller andet sted så den kører hver gang vi åbner programmet!
    public void updateAllMemberAges(ArrayList<Member> members) {
        for (Member m : members) {
            m.updateAge();
        }
    }


}
