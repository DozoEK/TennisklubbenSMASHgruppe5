package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.FinanceManagement.QuotaController;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UserHelperClass {
    private ArrayList<Member> members;

    public UserHelperClass(ArrayList<Member> members) {
        this.members = members;
    }


    //logik til at finde memeber id
    public Member findMemberById(int memberId) {
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
            System.out.println();
            System.out.print("Søg efter medlems ID eller navn (skriv 'exit' for at afslutte!): ");

            if (input.hasNextInt()) {
                int memberId = input.nextInt();
                input.nextLine();

                Member fundetMember = findMemberById(memberId);

                if (fundetMember != null) {
                    System.out.println("\nMedlem fundet:");
                    System.out.println(fundetMember);

                    System.out.print("\nEr dette det korrekte medlem? (ja/nej): ");
                    String confirmation = input.nextLine();

                    if (confirmation.equalsIgnoreCase("ja")) {
                        return fundetMember;
                    } else {
                        System.out.println("Søg igen.\n");
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

                if (matchingMembers.isEmpty()) {
                    System.out.println("Ingen medlemmer med navnet: " + name + " er fundet.");
                    continue;
                }


                if (matchingMembers.size() == 1) {
                    Member selectedMember = matchingMembers.get(0);
                    System.out.println("\nFølgende medlem fundet:");
                    System.out.println(selectedMember);

                    System.out.print("\nEr dette det korrekte medlem? (ja/nej): ");
                    String confirmation = input.nextLine();

                    if (confirmation.equalsIgnoreCase("ja")) {
                        return selectedMember;
                    } else {
                        System.out.println("Søg igen.\n");
                        continue;
                    }
                }

                while (true) {
                    System.out.println("\nFundne medlemmer:");
                    for (int i = 0; i < matchingMembers.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingMembers.get(i));
                    }
                    System.out.print("\nVælg nummer (1-" + matchingMembers.size() + ") eller '0' for at søge igen: ");

                    if (input.hasNextInt()) {
                        int choice = input.nextInt();
                        input.nextLine();

                        if (choice == 0) {
                            System.out.println("Søg igen.\n");
                            break;

                        } else if (choice > 0 && choice <= matchingMembers.size()) {
                            Member selectedMember = matchingMembers.get(choice - 1);

                            System.out.println("\nValgt medlem:");
                            System.out.println(selectedMember);

                            System.out.print("\nEr dette det korrekte medlem? (ja/nej): ");
                            String confirmation = input.nextLine();

                            if (confirmation.equalsIgnoreCase("ja")) {
                                return selectedMember;
                            } else {
                                System.out.println("Søg igen.\n");
                                break;
                            }
                        } else {
                            System.out.println("Ugyldigt valg. Prøv igen.\n");
                        }
                    } else {
                        input.nextLine();
                        System.out.println("Ugyldigt input. Prøv igen.\n");
                    }
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


    public void autoCheckAllQuotasForChangeInYearlyFeeDate() {
        MemberReader memberReader = new MemberReader();
        ArrayList<Member> members = memberReader.readFromFile();

        for (Member member : members) {
            checkForChangesInYearlyFeeDate(member.getMemberId());
        }
        System.out.println("(No new quota needed for any members)");
    }


    public void checkForChangesInYearlyFeeDate(int memberId) {
        QuotaReader qr = new QuotaReader();
        ArrayList<Quota> quotas = qr.readFromFile();
        QuotaController qc = new QuotaController(members);

        ArrayList<Quota> memberQuotas = new ArrayList<>();
        for (Quota q : quotas) {
            if (q.getMemberId() == memberId) {
                memberQuotas.add(q);
            }
        }

        if (memberQuotas.isEmpty()) {
            System.out.println("No quotas found for member " + memberId);
            return;
        }
        memberQuotas.sort(Comparator.comparing(Quota::getActualDateOfPayment, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
        Quota latest = memberQuotas.get(0);


        if (latest.getIsPaid() && LocalDate.now().equals(latest.getActualDateOfPayment())) {
            qc.logicForCreateQuotaForMember(memberId);

        }

    }
}