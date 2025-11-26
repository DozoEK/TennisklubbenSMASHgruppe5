package SmashTennisClub.MainPackage;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Member> Members = new ArrayList<Member>();
        Members.add(new Member(1, "TestName01", Gender.MALE, LocalDate.of(2024, 11, 22), 21, 22222, true, MembershipPricelist.JUNIOR,
                LocalDate.now(), true));
        Members.add(new Member(2, "TestName02", Gender.FEMALE, LocalDate.parse("2024-09-13"), 23, 11111, true,
                MembershipPricelist.PASSIVT, LocalDate.now(), true));


        for (Member m : Members) {
            System.out.println(m);


        }
    }


}
