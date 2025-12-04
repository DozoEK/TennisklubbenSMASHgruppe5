package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;
import java.time.Period;

public class Member {
    private int memberId;
    private String memberName;
    private Gender genderOfMember;
    private LocalDate dateOfBirth;
    private int age;
    private int phoneNumber;
    private Boolean competitivePlayer = false;
    private MemberType membershipType;
    private MembershipPricelist yearlyMembershipFee;
    private LocalDate yearlyFeeDate;
    private Boolean activeMembership = false;

    public Member(int memberId, String memberName, Gender genderOfMember, LocalDate dateOfBirth, int age,
                  int phoneNumber, Boolean competitivePlayer, MemberType membershipType, MembershipPricelist yearlyMembershipFee,
                  LocalDate yearlyFeeDate, Boolean activeMembership) {

        this.memberId = memberId;
        this.memberName = memberName;
        this.genderOfMember = genderOfMember;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        this.phoneNumber = phoneNumber;
        this.competitivePlayer = competitivePlayer;
        this.membershipType = membershipType;
        this.yearlyMembershipFee = yearlyMembershipFee;
        this.yearlyFeeDate = yearlyFeeDate;
        this.activeMembership = activeMembership;
    }


    public int getMemberId() {
        return memberId;
    }

    //Setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void updateAge() {
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGenderOfMember() {
        return genderOfMember;
    }

    public void setGenderOfMember(Gender genderOfMember) {
        this.genderOfMember = genderOfMember;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getCompetitivePlayer() {
        return competitivePlayer;
    }

    public void setCompetitivePlayer(Boolean competitivePlayer) {
        this.competitivePlayer = competitivePlayer;
    }

    public MemberType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MemberType membershipType) {
        this.membershipType = membershipType;
    }

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    public void setYearlyMembershipFee(MembershipPricelist yearlyMembershipFee) {
        this.yearlyMembershipFee = yearlyMembershipFee;
    }

    public LocalDate getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public void setYearlyFeeDate(LocalDate yearlyFeeDate) {
        this.yearlyFeeDate = yearlyFeeDate;
    }

    public void renewYearlyFeeDate() {
        this.yearlyFeeDate = LocalDate.now().plusYears(1);
    }

    public Boolean getActiveMembership() {
        return activeMembership;
    }

    public void setActiveMembership(Boolean activeMembership) {
        this.activeMembership = activeMembership;
    }

    @Override
    public String toString() {
        return String.format(
                "MemberID:%-3d  |  Name:%-20s  |  Gender:%-6s  |  DOB:%-10s  |  Age:%-3d  |  Phone:%-8s  |  Competitive:%-3s  |  Type:%-19s  |  Membership:%-10s  |  DueDate:%-10s  |  Active:%-3s  |",
                memberId,
                memberName,
                genderOfMember,
                dateOfBirth,
                age,
                phoneNumber,
                competitivePlayer ? "Yes" : "No",
                membershipType,
                yearlyMembershipFee != null ? yearlyMembershipFee.name() : "-",
                yearlyFeeDate != null ? yearlyFeeDate : "-",
                activeMembership ? "Yes" : "No"
        );
    }


}
