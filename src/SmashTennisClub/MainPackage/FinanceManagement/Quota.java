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
        this.actualDateOfPayment = (actualDateOfPayment != null) ? actualDateOfPayment : yearlyFeeDate;
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

    public void renewYearlyFeeDate() {
        this.yearlyFeeDate = LocalDate.now().plusYears(1);
    }

    public void setActualDateOfPayment(LocalDate actualDateOfPayment) {
        this.actualDateOfPayment = actualDateOfPayment;
    }


    @Override
    public String toString() {
        return String.format(
                "KontingentID:%-3d  |  MedlemsID:%-3d  |  Navn:%-20s  |  KontingentType:%-12s  |  Pris:%-12s  |  Betalt:%-3s  |  Forfaldsdato:%-10s  |  Betalingsdato:%-10s  |",
                quotaId,
                memberId,
                memberName,
                yearlyMembershipFee != null ? yearlyMembershipFee.name() : "-",
                yearlyMembershipFee != null ? String.format("%.2f kr.", yearlyMembershipFee.getPrice()) : "-",
                isPaid ? "Ja" : "Nej",
                yearlyFeeDate != null ? yearlyFeeDate : "-",
                actualDateOfPayment != null ? actualDateOfPayment : "-"
        );
    }

    //Quota q = new Quota(
    //    member.getMemberId(),
    //    member.getMemberName(),
    //);

}
