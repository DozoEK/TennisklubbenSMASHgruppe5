package SmashTennisClub.MainPackage.FinanceManagement;


import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.UserPackage.UserHelperClass;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuotaController {

    LocalDate today = LocalDate.now();

    private final ArrayList<Member> members;
    private final ArrayList<Quota> quotas;

    public QuotaController(ArrayList<Member> members) {
        this.members = members;


        QuotaReader reader = new QuotaReader();
        this.quotas = reader.readFromFile();
    }


    public ArrayList<Quota> getAllPayments() {
        return quotas;
    }

    public ArrayList<Quota> getUnpaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota quota : quotas) {
            if (quota.getIsPaid() == false && quota.getActualDateOfPayment().isAfter(today))
                paymentResult.add(quota);
        }
        return paymentResult;
    }

    public ArrayList<Quota> getLateUnpaidQuotas() {
        ArrayList<Quota> lateUnpaidQuotas = new ArrayList<>();
        QuotaReader reader = new QuotaReader();
        ArrayList<Quota> quotas = reader.readFromFile();


        for (Quota quota : quotas) {
            if (quota.getIsPaid() == false && quota.getActualDateOfPayment().isBefore(today) && (quota.getYearlyFeeDate() != quota.getActualDateOfPayment()))
                lateUnpaidQuotas.add(quota);
        }
        return lateUnpaidQuotas;
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
        UserHelperClass uhc = new UserHelperClass(members);

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
        LocalDate actualDateOfPayment = null;


        Quota newQuota = new Quota(quotaId, memberId, memberName, yearlyMembershipFee,
                isPaid, yearlyFeeDate, actualDateOfPayment);

        quotas.add(newQuota);
        fh.saveQuotas(quotas);

        System.out.println();
        System.out.println("Følgende Kontingent er oprettet og gemt til CSV:");
        System.out.println(newQuota);

        return newQuota;
    }


}
