package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;

public class Senior extends Member {


    private final DisciplineType seniorDisciplinType;

    public Senior(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                  int age, int phoneNumber, Boolean competitivePlayer,
                  MembershipPricelist membershipType, MembershipPricelist yearlyMembershipFee,
                  LocalDate yearlyFeeDate, DisciplineType seniorDisciplinType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                membershipType, yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.seniorDisciplinType = seniorDisciplinType;
    }

    public DisciplineType getSeniorDisciplinType() {
        return seniorDisciplinType;
    }

}
