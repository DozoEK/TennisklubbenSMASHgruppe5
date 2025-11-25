package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import java.sql.Date;

public class Member {

    int memberId;                      //bliver a utomatisk oprettet og inkrementeret i metoden for oprettelse af nye medlemmer.
    String name;                     //skal udfyldes via en scanner, af brugeren.
    Gender genderOfMember;
    Date dateOfBirth = null;            //skal udfyldes via en scanner, af brugeren.
    int age;                             //“dateOfBirth”  //automatisk udregnes udfra “dateOfBirth”.
    int phoneNumber;                     //skal udfyldes via en scanner, af brugeren.
    Boolean competitivePlayer = false;          //skal udfyldes via en scanner, af brugeren.
    MembershipPricelist yearlyMembershipFee;        // “metode som tjekker AGEGROUPTYPE mod en ENUM priceList”
    Date yearlyFeeDate;                         //“todayDate + 1 year” se “todayDate” metoden.

    Boolean activeMembership = false;  //automatisk sættes til true, når alle andre oplysninger er udfyldt.


    public Member(int memberId, String name, Gender genderOfMember, Date dateOfBirth, int age,
                  int phoneNumber, Boolean competitivePlayer, MembershipPricelist yearlyMembershipFee,
                  Date yearlyFeeDate, Boolean activeMembership) {

        this.memberId = memberId;
        this.name = name;
        this.genderOfMember = genderOfMember;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.competitivePlayer = competitivePlayer;
        this.yearlyMembershipFee = yearlyMembershipFee;
        this.yearlyFeeDate = yearlyFeeDate;
        this.activeMembership = activeMembership;
    }



    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDateOfBirth() {
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

    public MembershipPricelist getYearlyMembershipFee() {
        return yearlyMembershipFee;
    }

    public Date getYearlyFeeDate() {
        return yearlyFeeDate;
    }

    public Boolean getActiveMembership() {
        return activeMembership;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", genderOfMember=" + genderOfMember +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", competitivePlayer=" + competitivePlayer +
                ", yearlyMembershipFee=" + yearlyMembershipFee +
                ", yearlyFeeDate=" + yearlyFeeDate +
                ", activeMembership=" + activeMembership +
                '}';
    }
}
