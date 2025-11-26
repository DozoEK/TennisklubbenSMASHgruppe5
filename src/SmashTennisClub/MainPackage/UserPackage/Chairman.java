package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileHandler.SuperReader;
import SmashTennisClub.FileHandler.SuperWriter;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chairman {
    SuperReader reader = new SuperReader();
    SuperWriter writer = new SuperWriter();

    private ArrayList<Member> members = reader.readFromFile();
//    private int memberId = members.getLast().getMemberId() + 1;



    public void createMemberTest() {
        ArrayList<Member> members = new ArrayList<>();

        members.add(new Member(1, "TestName01", Gender.MALE, LocalDate.of(2024, 11, 22), 21, 22222, true, MembershipPricelist.JUNIOR,
                LocalDate.now(), true));
        members.add(new Member(2, "TestName02", Gender.FEMALE, LocalDate.parse("2024-07-12"), 23, 11111, true,
                MembershipPricelist.PASSIVT, LocalDate.now(), true));
        members.add(new Member(3, "TestNavn03", Gender.MALE, LocalDate.parse("1999-09-13"), 12, 555555, true,
                MembershipPricelist.JUNIOR, LocalDate.now(), true));


        writer.saveMembers(members);
    }


//    public createMemberTest() {
//        ArrayList<Member> members = new ArrayList<>();
//        Member member = new Member(memberId++, customerName, customerPhone, date, time, selectedProducts, totalPrice);
//        members.add(member);
//        System.out.println("New member added: " + member);
//        writer.saveMembers(members);
//    }




   // public deleteMember() {}
    void searchForMember() {}
  //  public editMember() {}
    void showAllMembers() {}

}
