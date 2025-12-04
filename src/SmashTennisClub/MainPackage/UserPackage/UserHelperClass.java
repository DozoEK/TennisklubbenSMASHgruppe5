package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.MainPackage.ErrorAndValidation.SmashException;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationInterface;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationMethods;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.FinanceManagement.QuotaController;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UserHelperClass {
    private final ArrayList<Member> members;
    private final QuotaController qc;

    public UserHelperClass(ArrayList<Member> members) {
        this.members = members;
        this.qc = new QuotaController(members);
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

        ValidationInterface validator = new ValidationMethods();

        while (true) {
            System.out.println();
            System.out.print("Søg efter medlems ID eller navn (skriv 'exit' for at afslutte!): ");
            String multiInput = input.nextLine();

            if (multiInput.equalsIgnoreCase("exit")) {
                System.out.println("Afslutter søgning...");
                return null;
            }

            try {
                validator.validateLettersOrNumbersOnly(multiInput);
            } catch (SmashException e) {
                System.out.println(e.getMessage());
                continue;
            }


                if (multiInput.matches("\\d+")) {
                    int memberId = Integer.parseInt(multiInput);
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
                    ArrayList<Member> matchingMembers = findMembersByPartialName(multiInput);
                    if (matchingMembers.isEmpty()) {
                        System.out.println("Ingen medlemmer med navnet: " + multiInput + " er fundet.");
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

        for (Member member : members) {
            checkForChangesInYearlyFeeDate(member.getMemberId());
        }
        System.out.println("(autoCheckAllQuotasForChangeInYearlyFeeDate: completed)");
    }


    public void checkForChangesInYearlyFeeDate(int memberId) {
        ArrayList<Quota> memberQuotas = new ArrayList<>();

        for (Quota q : qc.getAllPayments()) {
            if (q.getMemberId() == memberId) {
                memberQuotas.add(q);
            }
        }

        if (memberQuotas.isEmpty()) {
            System.out.println("No quotas found for member " + memberId);
            qc.logicForCreateQuotaForMember(memberId);
            return;
        }
        memberQuotas.sort(Comparator.comparing(Quota::getActualDateOfPayment, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
        Quota latest = memberQuotas.get(0);


        if (latest.getIsPaid() && LocalDate.now().equals(latest.getActualDateOfPayment())) {
            qc.logicForCreateQuotaForMember(memberId);

        }

    }
}