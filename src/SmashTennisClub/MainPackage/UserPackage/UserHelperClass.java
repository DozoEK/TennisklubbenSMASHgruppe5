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

    private final ArrayList<Member> members = reader.readFromFile();


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


    public Member searchForMember() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Søg efter medlem ---");
            System.out.println("Indtast medlems ID eller navn: ");
            System.out.println("(skriv 'exit' for at afslutte!)");

            if (input.hasNextInt()) {
                int memberId = input.nextInt();
                input.nextLine();
                Member fundetMember = findMemberById(memberId);

                if (fundetMember != null) {
                    System.out.println("\nMedlem fundet:");
                    System.out.println(fundetMember);


                    System.out.print("\nEr dette det rigtige medlem? (ja/nej): ");
                    String confirmation = input.nextLine();

                    if (confirmation.equalsIgnoreCase("ja")) {
                        return fundetMember;
                    } else {
                        System.out.println("Søg igen:\n");
                    }

                } else {
                    System.out.println("Ingen medlemmer fundet med ID: " + memberId);
                }

            } else {
                String name = input.nextLine();

                if (name.equalsIgnoreCase("exit")) {
                    System.out.println("Afslutter søgning...");
                    return null;
                }


                ArrayList<Member> matchingMembers = findMembersByPartialName(name);

                if (!matchingMembers.isEmpty() && matchingMembers.size() <= 1) {
                    System.out.println("Fundet følgende medlem: ");
                    System.out.println(matchingMembers);
                    System.out.println("Tast '1' for at fortsætte - Tast '0' for at søge igen: ");


                    if (input.hasNextInt()) {
                        int choice = input.nextInt();
                        input.nextLine();

                        if (choice > 0 && choice <= matchingMembers.size()) {
                            Member selectedMember = matchingMembers.get(choice - 1);


                            System.out.println("\nValgt medlem:");
                            System.out.println(selectedMember);
                            System.out.print("\nEr dette det korrekte medlem? (ja/nej): ");
                            String confirmation = input.nextLine();

                            if (confirmation.equalsIgnoreCase("ja")) {
                                return selectedMember;
                            } else {
                                System.out.println("Søg igen:\n");
                            }
                        } else if (choice == 0) {
                            System.out.println("Søg igen:\n");
                        } else {
                            System.out.println("Ugyldigt valg. Søg igen: \n");
                        }
                    } else {
                        input.nextLine();
                        System.out.println("Ugyldigt input. Søg igen:\n");
                    }

                } else if (!matchingMembers.isEmpty() && matchingMembers.size() >= 2) {

                    System.out.println("\nFundne medlemmer:");
                    for (int i = 0; i < matchingMembers.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingMembers.get(i));
                    }
                    System.out.print("\nVælg nummer (1-" + matchingMembers.size() + ") eller '0' for at søge igen: ");


                    if (input.hasNextInt()) {
                        int choice = input.nextInt();
                        input.nextLine();

                        if (choice > 0 && choice <= matchingMembers.size()) {
                            Member selectedMember = matchingMembers.get(choice - 1);


                            System.out.println("\nValgt medlem:");
                            System.out.println(selectedMember);
                            System.out.print("\nEr dette det korrekte medlem? (ja/nej): ");
                            String confirmation = input.nextLine();

                            if (confirmation.equalsIgnoreCase("ja")) {
                                return selectedMember;
                            } else {
                                System.out.println("Søg igen:\n");
                            }
                        } else if (choice == 0) {
                            System.out.println("Søg igen:\n");
                        } else {
                            System.out.println("Ugyldigt valg. Søg igen: \n");
                        }
                    } else {
                        input.nextLine();
                        System.out.println("Ugyldigt input. Søg igen:\n");
                    }

                }else {
                    System.out.println("Ingen medlemmer med navnet: " + name + " er fundet.");
                }
            }
        }
    }


    private ArrayList<Member> findMembersByPartialName(String partialName) {
        ArrayList<Member> matchingMembers = new ArrayList<>();
        String searchedName = partialName.toLowerCase();

        for (Member m : members) {
            String memberName = m.getMemberName().toLowerCase();

            if (memberName.contains(searchedName)) {
                matchingMembers.add(m);
            }
        }
        return matchingMembers;
    }


    public void printAllMembers() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllMembers();
    }


    //vi skal sætte den et eller andet sted så den kører hver gang vi åbner programmet!
    public void updateAllMemberAges(ArrayList<Member> members) {
        for (Member m : members) {
            m.updateAge();
        }
    }


    //søg efter medlem og hav retur af medlem efter det er fundet!
    //        Member selectedMember = userHelper.searchForMember();
    //
    //        if (selectedMember == null) {
    //            System.out.println("Ingen medlem valgt. Afbryder oprettelse af spiller beretning.");
    //            return null;
    //        }
    //
    //        int memberId = selectedMember.getMemberId();
    //        String memberName = selectedMember.getMemberName();


}