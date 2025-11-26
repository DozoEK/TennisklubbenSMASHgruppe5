package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.sql.Date;
import java.time.LocalDate;

class Senior extends Member {


    private DisciplineType seniorDiscplineType;

    public Senior(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                              int age, int phoneNumber, Boolean competitivePlayer,
                              MembershipPricelist yearlyMembershipFee, LocalDate yearlyFeeDate,
                              DisciplineType seniorDiscplineType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.seniorDiscplineType = seniorDiscplineType;

    }

    public DisciplineType getSeniorDiscplineType() {
        return seniorDiscplineType;
    }

}
