package SmashTennisClub.MainPackage.FinanceManagement;

import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.Date;

public class Quota {
    int quotaId;
    Member member;
    MembershipPricelist yearlyMembershipFee;
    Boolean isPaid;
    Date yearlyFeeDate;
    Date actualDateOfPayment;


    public Quota(Member member, int quotaId, MembershipPricelist yearlyMembershipFee, Boolean isPaid, Date yearlyFeeDate, Date actualDateOfPayment) {
        this.member = member;
        this.quotaId = quotaId;
        this.yearlyMembershipFee = yearlyMembershipFee;
        this.isPaid = isPaid;
        this.yearlyFeeDate = yearlyFeeDate;
        this.actualDateOfPayment = actualDateOfPayment;
    }


    //getters
    public Boolean getIsPaid() {
        return isPaid;
    }

    public int getQuotaId() {
        return quotaId;
    }

    public Member getMember() {
        return member;
    }

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    public Date getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public Date getActualDateOfPayment() {
        return actualDateOfPayment;
    }

    //setters

    public void setYearlyMembershipFee(MembershipPricelist yearlyMembershipFee) {
        this.yearlyMembershipFee = yearlyMembershipFee;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public void setYearlyFeeDate(Date yearlyFeeDate) {
        this.yearlyFeeDate = yearlyFeeDate;
    }

    public void setActualDateOfPayment(Date actualDateOfPayment) {
        this.actualDateOfPayment = actualDateOfPayment;
    }



    @Override
    public String toString() {
        return "Quota{" +
                "quotaId=" + quotaId +
                ", member=" + member +
                ", yearlyMembershipFee=" + yearlyMembershipFee +
                ", isPaid=" + isPaid +
                ", yearlyFeeDate=" + yearlyFeeDate +
                ", actualDateOfPayment=" + actualDateOfPayment +
                '}';
    }


}
