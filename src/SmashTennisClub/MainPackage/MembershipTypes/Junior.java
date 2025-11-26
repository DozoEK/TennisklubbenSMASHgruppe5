package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.sql.Date;
import java.time.LocalDate;

class Junior extends Member {

    private DisciplineType juniorDiscplineType;

    public Junior(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                              int age, int phoneNumber, Boolean competitivePlayer,
                              MembershipPricelist yearlyMembershipFee, LocalDate yearlyFeeDate,
                              DisciplineType juniorDiscplineType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.juniorDiscplineType = juniorDiscplineType;
    }

    public DisciplineType getJuniorDiscplineType() {
        return juniorDiscplineType;
    }
}
