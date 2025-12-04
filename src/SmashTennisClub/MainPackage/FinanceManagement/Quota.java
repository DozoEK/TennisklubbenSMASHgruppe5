package SmashTennisClub.MainPackage.FinanceManagement;

import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;

public class Quota {
    private final int quotaId;
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

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getQuotaId() {
        return quotaId;
    }

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    //setters
    public void setYearlyMembershipFee(MembershipPricelist yearlyMembershipFee) {
        this.yearlyMembershipFee = yearlyMembershipFee;
    }

    public LocalDate getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public void setYearlyFeeDate(LocalDate yearlyFeeDate) {
        this.yearlyFeeDate = yearlyFeeDate;
    }

    public LocalDate getActualDateOfPayment() {
        return actualDateOfPayment;
    }

    public void setActualDateOfPayment(LocalDate actualDateOfPayment) {
        this.actualDateOfPayment = actualDateOfPayment;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public void renewYearlyFeeDate() {
        this.yearlyFeeDate = LocalDate.now().plusYears(1);
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
