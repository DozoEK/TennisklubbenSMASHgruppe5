package SmashTennisClub.MainPackage;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import java.sql.Date;

public class Member {

    int memberId;  //bliver a utomatisk oprettet og inkrementeret i metoden for oprettelse af nye medlemmer.
    String name; //skal udfyldes via en scanner, af brugeren.
    Gender genderOfMember;
    Date dateOfBirth = null; //skal udfyldes via en scanner, af brugeren.
    int age = “dateOfBirth”  //automatisk udregnes udfra “dateOfBirth”.
    int phoneNumber; //skal udfyldes via en scanner, af brugeren.
    Boolean competitivePlayer = false;  //skal udfyldes via en scanner, af brugeren.
    MembershipPricelist yearlyMembershipFee = “metode som tjekker AGEGROUPTYPE mod en ENUM priceList”
    Date yearlyFeeDate = “todayDate + 1 year” //se “todayDate” metoden.
    DisciplineType disciplineTypeForMember;

    Boolean activeMembership = false;  //automatisk sættes til true, når alle andre oplysninger er udfyldt.

}
