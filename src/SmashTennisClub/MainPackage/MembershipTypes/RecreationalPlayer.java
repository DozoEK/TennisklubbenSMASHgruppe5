package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.sql.Date;
import java.time.LocalDate;

public class RecreationalPlayer extends Member {

    public RecreationalPlayer(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                              int age, int phoneNumber, Boolean competitivePlayer,
                              MemberType membershipType, MembershipPricelist yearlyMembershipFee,
                              LocalDate yearlyFeeDate, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                membershipType, yearlyMembershipFee, yearlyFeeDate, activeMembership);
    }

    @Override
    public String toString() {
        return "RecreationalPlayer{" +
                "memberId=" + getMemberId() +
                ", memberName='" + getMemberName() + '\'' +
                ", genderOfMember=" + getGenderOfMember() +
                ", dateOfBirth=" + getDateOfBirth() +
                ", age=" + getAge() +
                ", phoneNumber=" + getPhoneNumber() +
                ", competitivePlayer=" + getCompetitivePlayer() +
                ", membershipType=" + getMembershipType() +
                ", yearlyMembershipFee=" + getYearlyMembershipFee() +
                ", yearlyFeeDate=" + getYearlyFeeDate() +
                ", activeMembership=" + getActiveMembership() +
                '}';
    }


}
