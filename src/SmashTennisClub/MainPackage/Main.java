package SmashTennisClub.MainPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.UserPackage.Chairman;
import SmashTennisClub.MainPackage.UserPackage.Treasurer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Chairman chairman = new Chairman();
        FileHandler fileHandler = new FileHandler();
//        Treasurer treasurer = new Treasurer();
//        ArrayList<Quota> embers = new ArrayList<Quota>();
//        embers.add(new Quota(10, 15,"lars", 1500.00,true,
//                2025,03, 05, 2025,06,06"));


       // chairman.createMemberTest();
//        fileHandler.printAllMembers();
//        chairman.deleteMember();
//        treasurer.searchForQuota();


    }
}

