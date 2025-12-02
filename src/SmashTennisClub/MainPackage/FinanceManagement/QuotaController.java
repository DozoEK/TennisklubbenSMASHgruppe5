package SmashTennisClub.MainPackage.FinanceManagement;


import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.UserPackage.UserHelperClass;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuotaController {
    QuotaReader reader = new QuotaReader();
    ArrayList<Quota> quotas = reader.readFromFile();


    public ArrayList<Quota> getAllPayments() {
        return quotas;
    }

    public ArrayList<Quota> getUnpaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota quota : quotas) {
            if (quota.getIsPaid() == false)  paymentResult.add(quota);
        }
        return paymentResult;
    }

    public ArrayList<Quota> getPaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota quota : quotas) {
            if (quota.getIsPaid() == true) paymentResult.add(quota);
        }
        return paymentResult;
    }


    public Quota logicForCreateQuotaForMember(int memberId) {
        FileHandler fh = new FileHandler();
        QuotaReader qr = new QuotaReader();
        UserHelperClass uhc = new UserHelperClass();
        ArrayList<Quota> quotas = qr.readFromFile();

        int lastUsedQuotaId = 0;
        for (Quota quota : quotas) {
            if (quota.getQuotaId() > lastUsedQuotaId) {
                lastUsedQuotaId = quota.getQuotaId();
            }
        }
        int quotaId = lastUsedQuotaId + 1;


        Member selectedMember = uhc.findMemberById(memberId);

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




//    public void updateYearlyFeeDateOnMember() {
//    FileHandler fh = new FileHandler();
//    MemberReader mr = new MemberReader();
//    QuotaReader reader = new QuotaReader();
//    ArrayList<Quota> quotas = reader.readFromFile();
//
//
//
//            quota.setPaid(true);
//            quota.setYearlyFeeDate(LocalDate.now());
//
//            System.out.println("Betaling registret for " + quota.getMemberName());
//            System.out.println("Beløb: " + quota.getYearlyMembershipFee().getPrice() + "Kr.");
//            System.out.println("Dato: " + LocalDate.now());
//            System.out.println(quota);
//
//            quotas.add(quota);
//            fh.saveQuotas(quotas);
//            return true;
//        }
//    }
//        System.out.println("kontingent for Medlem er ikke fundet!" + memberId);
//        return false;
//}

}
