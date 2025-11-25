package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.sql.Date;

class RecreationalPlayer extends Member {

    public RecreationalPlayer(int memberId, String name, Gender genderOfMember, Date dateOfBirth,
                              int age, int phoneNumber, Boolean competitivePlayer,
                              MembershipPricelist yearlyMembershipFee, Date yearlyFeeDate,
                              DisciplineType disciplineTypeForMember, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                yearlyMembershipFee, yearlyFeeDate, activeMembership);
    }

}
