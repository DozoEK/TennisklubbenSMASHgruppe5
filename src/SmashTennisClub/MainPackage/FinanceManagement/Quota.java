package SmashTennisClub.MainPackage.FinanceManagement;

import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.util.Date;

public class Quota {
    public static int getMemberId;
    int quotaId;
    int memberId; //“medlem det omhandler”;
    String memberName; //“medlem det omhandler”;
    MembershipPricelist yearlyMembershipFee; //= “tages fra Member”;

   static Boolean isPaid;
    Date yearlyFeeDate;
    Date actualDateOfPayment;

    public Quota(int quotaId, int memberId, String memberName, MembershipPricelist yearlyMembershipFee,
                 Boolean isPaid, Date yearlyFeeDate, Date actualDateOfPayment) {

        this.quotaId = quotaId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.yearlyMembershipFee = yearlyMembershipFee;
        this.isPaid = isPaid;
        this.yearlyFeeDate = yearlyFeeDate;
        this.actualDateOfPayment = actualDateOfPayment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setIsPaid() {
        this.isPaid = isPaid;
    }

    public static Boolean getIsPaid() {
        return isPaid;
    }
}
