package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.sql.Date;

class Senior extends Member {


    private DisciplineType seniorDiscplineType;

    public Senior(int memberId, String name, Gender genderOfMember, Date dateOfBirth,
                              int age, int phoneNumber, Boolean competitivePlayer,
                              MembershipPricelist yearlyMembershipFee, Date yearlyFeeDate,
                              DisciplineType seniorDiscplineType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.seniorDiscplineType = seniorDiscplineType;

    }

    public DisciplineType getSeniorDiscplineType() {
        return seniorDiscplineType;
    }

}
