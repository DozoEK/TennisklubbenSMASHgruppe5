package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;

public class Junior extends Member {

    private final DisciplineType juniorDisciplinType;

    public Junior(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                  int age, int phoneNumber, Boolean competitivePlayer,
                  MemberType membershipType, MembershipPricelist yearlyMembershipFee,
                  LocalDate yearlyFeeDate, DisciplineType juniorDisciplinType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                membershipType, yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.juniorDisciplinType = juniorDisciplinType;
    }

    public DisciplineType getJuniorDisciplinType() {
        return juniorDisciplinType;
    }

    @Override
    public String toString() {
        return "Junior{" +
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
                ", discipline=" + juniorDisciplinType +
                '}';
    }


}
