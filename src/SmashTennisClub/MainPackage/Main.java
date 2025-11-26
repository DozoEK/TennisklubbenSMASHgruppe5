package SmashTennisClub.MainPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.UserPackage.Chairman;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Chairman chairman = new Chairman();
        FileHandler fileHandler = new FileHandler();
//        ArrayList<Member> Members = new ArrayList<Member>();


       // chairman.createMemberTest();
        fileHandler.printAllMembers();


    }
}

