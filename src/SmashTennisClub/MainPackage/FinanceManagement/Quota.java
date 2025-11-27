package SmashTennisClub.MainPackage.FinanceManagement;

import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.Date;

public class Quota {
    private int quotaId;
    private int memberId;
    private String memberName;
    private MembershipPricelist yearlyMembershipFee;
    private boolean isPaid;
    private LocalDate yearlyFeeDate;
    private LocalDate actualDateOfPayment;


    public Quota(int quotaId, int memberId, String memberName, MembershipPricelist yearlyMembershipFee,
                 Boolean isPaid, LocalDate yearlyFeeDate, LocalDate actualDateOfPayment) {

        this.quotaId = quotaId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.yearlyMembershipFee = yearlyMembershipFee;
        this.isPaid = isPaid;
        this.yearlyFeeDate = yearlyFeeDate;
        this.actualDateOfPayment = actualDateOfPayment;
    }


    //getters
    public Boolean getIsPaid() {
        return isPaid;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getQuotaId() {
        return quotaId;
    }

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    public LocalDate getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public LocalDate getActualDateOfPayment() {
        return actualDateOfPayment;
    }

    //setters
    public void setYearlyMembershipFee(MembershipPricelist yearlyMembershipFee) {
        this.yearlyMembershipFee = yearlyMembershipFee;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public void setYearlyFeeDate(LocalDate yearlyFeeDate) {
        this.yearlyFeeDate = yearlyFeeDate;
    }

    public void setActualDateOfPayment(LocalDate actualDateOfPayment) {
        this.actualDateOfPayment = actualDateOfPayment;
    }


    @Override
    public String toString() {
        return "Quota{" +
                "quotaId=" + quotaId +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", yearlyMembershipFee=" + yearlyMembershipFee +
                ", isPaid=" + isPaid +
                ", yearlyFeeDate=" + yearlyFeeDate +
                ", actualDateOfPayment=" + actualDateOfPayment +
                '}';
    }

}
