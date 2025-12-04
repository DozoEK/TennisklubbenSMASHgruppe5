package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.FinanceManagement.QuotaController;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Treasurer {

    private ArrayList<Member> members;

    public Treasurer(ArrayList<Member> members) {
        this.members = members;
    }

    QuotaController qc = new QuotaController(members);


    public void treasurerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== SMASH TENNIS CLUB | TREASURER MENU =====");
            System.out.println("1. Opret kontingent for medlem");
            System.out.println("2. Auto-opret kontingenter til alle medlemmer");
            System.out.println("3. Registrér betaling for medlem");
            System.out.println("4. Søg efter kontingent");
            System.out.println("5. Vis alle betalte kontingenter");
            System.out.println("6. Vis alle ubetalte kontingenter");
            System.out.println("7. Vis alle medlemmer med restancer (ubetalt)");
            System.out.println("8. Print alle kontingenter (fra CSV)");
            System.out.println("9. Print alle forsent betalte kontingenter");
            System.out.println();
            System.out.println("('x' for afslut menu || 'exit' for afslut program)");
            System.out.print("Vælg en funktion (1-8): ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    createQuotaForMember();
                    break;

                case "2":
                    autoCreateQuotasForAllMembers();
                    System.out.println("Kontingenter er oprettet for alle medlemmer.");
                    break;

                case "3":
                    registerPaymentForMember();
                    break;

                case "4":
                    searchForQuota();
                    break;

                case "5":
                    showAllPaidQuotas();
                    break;

                case "6":
                    showAllUnpaidQuotas();
                    break;

                case "7":
                    showUnpaidMembers();
                    break;

                case "8":
                    printAllQuotas();
                    break;
                case "9":
                    showAllLateUnpaidQuotas();
                    break;

                case "x":
                    System.out.println("Går tilbage til startmenu...");
                    running = false;
                    break;

                case "exit":
                    System.out.println("Program afsluttes...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }




    public void autoCreateQuotasForAllMembers() {
        FileHandler fh = new FileHandler();
        QuotaReader qr = new QuotaReader();
        ArrayList<Quota> quotas = qr.readFromFile();
        MemberReader memberReader = new MemberReader();
        ArrayList<Member> members = memberReader.readFromFile();

        int lastUsedQuotaId = 0;
        for (Quota quota : quotas) {
            if (quota.getQuotaId() > lastUsedQuotaId) {
                lastUsedQuotaId = quota.getQuotaId();
            }
        }
        int nextQuotaId = lastUsedQuotaId + 1;

        LocalDate yearlyFeeDate = LocalDate.now().plusYears(1);
        LocalDate actualDateOfPayment = yearlyFeeDate;

        for (Member member : members) {
            Quota quota = new Quota(nextQuotaId, member.getMemberId(), member.getMemberName(),
                    member.getYearlyMembershipFee(), false, member.getYearlyFeeDate(), actualDateOfPayment);

            quotas.add(quota);
        }
        fh.saveQuotas(quotas);
    }


    //TODO restance metode:
    //hvis isPaid = false og yearlyFeeDate er i datid = isMember (NO)
    //Brug næsten samme logik som fra "CheckForChangesInYearlyFees"


    public Quota createQuotaForMember() {
        UserHelperClass userHelper = new UserHelperClass(members);
        FileHandler fh = new FileHandler();
        QuotaReader qr = new QuotaReader();
        ArrayList<Quota> quotas = qr.readFromFile();

        while (true) {
            int lastUsedQuotaId = 0;
            for (Quota quota : quotas) {
                if (quota.getQuotaId() > lastUsedQuotaId) {
                    lastUsedQuotaId = quota.getQuotaId();
                }
            }
            int quotaId = lastUsedQuotaId + 1;

            System.out.println("--- Opret kontingent ---");

            Member selectedMember = userHelper.searchForMember();


            if (selectedMember == null) {
                System.out.println("Ingen medlem valgt. Prøv igen.");
                continue;
            }


            int memberId = selectedMember.getMemberId();
            String memberName = selectedMember.getMemberName();
            MembershipPricelist yearlyMembershipFee = selectedMember.getYearlyMembershipFee();
            boolean isPaid = false;

            LocalDate yearlyFeeDate = LocalDate.now().plusYears(1);
            LocalDate actualDateOfPayment = yearlyFeeDate;


            Quota quota = new Quota(quotaId, memberId, memberName, yearlyMembershipFee,
                    isPaid, yearlyFeeDate, actualDateOfPayment);


            quotas.add(quota);
            fh.saveQuotas(quotas);

            System.out.println("Følgende Kontingent er oprettet og gemt til CSV:");
            System.out.println(quota);

            return quota;
        }
    }



    public void showAllPaidQuotas() {
        System.out.println("--- Betalte kontingenter ---");
        for (Quota b : qc.getPaidPayments()){
            System.out.println(b);
        }
    }


    public void showAllUnpaidQuotas() {
        System.out.println("---Ubetalte kontigent--- ");
        for (Quota u : qc.getUnpaidPayments()){
            System.out.println(u);
        }
    }

   public void searchForQuota() {
        Scanner scanner = new Scanner (System.in);
       System.out.println("Indtast venligst MemberId: ");
       int searchForMemberId = scanner.nextInt();
       scanner.nextLine();

       boolean isfound = false;
       for (Quota m : qc.getAllPayments()){
           if (m.getMemberId() == searchForMemberId) {
               System.out.println("Følgende kontingenter er fundet for medlemmet: ");
               System.out.println(m);
               isfound = true;

           }
       }
       if(!isfound){
           System.out.println("ingen betaling fundet for dette medlem!");
       }
   }


   public void registerPaymentForMember() {
       UserHelperClass userHelper = new UserHelperClass(members);
       System.out.println("--- Register payment for single user ---");

       Member selectedMember = userHelper.searchForMember();

       int memberId = selectedMember.getMemberId();
       registerPayments(memberId);

   }


    public boolean registerPayments(int memberId) {
        FileHandler fh = new FileHandler();
        QuotaReader reader = new QuotaReader();
        ArrayList<Quota> quotas = reader.readFromFile();

        for (Quota quota : quotas) {
            if (quota.getMemberId() == memberId) {
                if (quota.getIsPaid()){
                    System.out.println("Medlemmet har allerede betalt for følgende regning: " + quota);
                    return false;
                }
                quota.setPaid(true);
                quota.setActualDateOfPayment(LocalDate.now());



                System.out.println("Betaling registret for " + quota.getMemberName());
                System.out.println("Beløb: " + quota.getYearlyMembershipFee().getPrice() + "Kr.");
                System.out.println("Dato: " + LocalDate.now());
                System.out.println(quota);

                quotas.add(quota);
                fh.saveQuotas(quotas);
                return true;
            }
        }
        System.out.println("kontingent for Medlem er ikke fundet!" + memberId);
        return false;
    }


    public void showUnpaidMembers(){
        QuotaController qc = new QuotaController(members);
        ArrayList<Quota> unpaidQuotas = qc.getUnpaidPayments();

        if (unpaidQuotas.isEmpty()){
            System.out.println("Ingen medlemmer mangler betaling.");
            return;
        }

        System.out.println("\n --- Medlemmer der mangler at betale ---");

        System.out.printf("%-10s | %-20s |   %-10s | %-12s%n", "MemberID", "Name", "Amount", "DueDate");
        System.out.println("---------------------------------------------------------------");

        for (Quota quota : unpaidQuotas) {
            System.out.printf(
                    "%-10d | %-20s | %-10.2f kr | %-12s%n",
                    quota.getMemberId(),
                    quota.getMemberName(),
                    quota.getYearlyMembershipFee().getPrice(),
                    quota.getYearlyFeeDate() != null ? quota.getYearlyFeeDate() : "-"
            );
        }

        System.out.println();
        System.out.println("Total antal ubetalte kontingenter: " + unpaidQuotas.size());
        System.out.println("---------------------------------------------------------------");
    }



    public void showAllLateUnpaidQuotas(){
        QuotaController qc = new QuotaController(members);
        ArrayList<Quota> lateUnpaidQuotas = qc.getLateUnpaidQuotas();

        if (lateUnpaidQuotas.isEmpty()){
            System.out.println("Ingen medlemmer mangler betaling.");
            return;
        }

        System.out.println("\n --- Medlemmer i restance ---");

        System.out.printf("%-10s | %-20s | %-12s | %-15s | %-15s |%n",
                "MemberID", "Name", "Amount", "DueDate", "PaymentDate");
        System.out.println("--------------------------------------------------------------------------------");

        for (Quota quota : lateUnpaidQuotas) {
            System.out.printf(
                    "%-10d | %-20s | %-10.2f kr | %-15s | %-15s |%n",
                    quota.getMemberId(),
                    quota.getMemberName(),
                    quota.getYearlyMembershipFee().getPrice(),
                    quota.getYearlyFeeDate() != null ? quota.getYearlyFeeDate() : "-",
                    quota.getActualDateOfPayment() != null ? quota.getActualDateOfPayment() : "-"
            );
        }

        System.out.println();
        System.out.println("Total antal ubetalte kontingenter: " + lateUnpaidQuotas.size());
        System.out.println("--------------------------------------------------------------------------------");
    }



    public void printAllQuotas() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllQuotas();
    }


}
