package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import java.sql.Date;
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
    private MembershipPricelist membershipType;
    private MembershipPricelist yearlyMembershipFee;
    private final LocalDate yearlyFeeDate;
    private Boolean activeMembership = false;

    public Member(int memberId, String memberName, Gender genderOfMember, LocalDate dateOfBirth, int age,
                  int phoneNumber, Boolean competitivePlayer, MembershipPricelist membershipType, MembershipPricelist yearlyMembershipFee,
                  LocalDate yearlyFeeDate, Boolean activeMembership) {

        this.memberId = memberId;
        this.memberName = memberName;
        this.genderOfMember = genderOfMember;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();;
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

    public String getMemberName() {
        return memberName;
    }

    public int getAge() {
        return age;
    }

    public void updateAge() {
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGenderOfMember() {
        return genderOfMember;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getCompetitivePlayer() {
        return competitivePlayer;
    }

    public MembershipPricelist getMembershipType() {
        return membershipType;
    }

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    public LocalDate getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public Boolean getActiveMembership() {
        return activeMembership;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", genderOfMember=" + genderOfMember +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", competitivePlayer=" + competitivePlayer +
                ", membershipType=" + membershipType +
                ", yearlyMembershipFee=" + yearlyMembershipFee +
                ", yearlyFeeDate=" + yearlyFeeDate +
                ", activeMembership=" + activeMembership +
                '}';
    }


}
