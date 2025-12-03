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
    private QuotaController controller;


    public void autoCreateQuotasForAllMembers() {
        UserHelperClass userHelper = new UserHelperClass();
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
        UserHelperClass userHelper = new UserHelperClass();
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
        for (Quota b : controller.getPaidPayments()){
            System.out.println(b);
        }
    }


    public void showAllUnpaidQuotas() {
        System.out.println("---Ubetalte kontigent--- ");
        for (Quota u : controller.getUnpaidPayments()){
            System.out.println(u);
        }
    }

   public void searchForQuota() {
        Scanner scanner = new Scanner (System.in);
       System.out.println("Indtast venligst MemberId: ");
       int searchForMemberId = scanner.nextInt();
       scanner.nextLine();

       boolean isfound = false;
       for (Quota m : controller.getAllPayments()){
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
       UserHelperClass userHelper = new UserHelperClass();
       System.out.println("--- Register payment for single user ---");

       Member selectedMember = userHelper.searchForMember();

       int memberId = selectedMember.getMemberId();
       registerPayments(memberId);

   }


    public boolean registerPayments(int memberId) {
        FileHandler fh = new FileHandler();
        QuotaReader reader = new QuotaReader();
        ArrayList<Quota> quotas = reader.readFromFile();
        MemberReader mr = new MemberReader();
        MemberWriter mw = new MemberWriter();


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
        QuotaController qc = new QuotaController();
        ArrayList<Quota> unpaidQuotas = qc.getUnpaidPayments();

        if (unpaidQuotas.isEmpty()){
            System.out.println("ingen medlemmer mangler betaling.");
            return;
        }

        System.out.println("\n --- Medlemmer der mangler at betale ---");
        for (Quota quota : unpaidQuotas) {
            System.out.println("Medlems-ID: " + quota.getMemberId() +
                    "\n Navn: " + quota.getMemberName() +
                    "\n Beløb: " + quota.getYearlyMembershipFee().getPrice() + "kr"+
                    "\n Forfalder: " + quota.getYearlyFeeDate());
        }

        System.out.println("Total: " + unpaidQuotas.size() + "Ubetalte kontigenter.");
    }


    public void printAllQuotas() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllQuotas();
    }


}
