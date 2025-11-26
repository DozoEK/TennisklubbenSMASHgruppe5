package SmashTennisClub.FileSystem;

import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;

public class FileHandler {

    SuperWriter superwriter = new SuperWriter();
    SuperReader superReader = new SuperReader();


    public void printAllMembers() {
        ArrayList<Member> members = superReader.readFromFile();
        for (Member member : members) {
            System.out.println(member);
        }
    }


    public void saveMembers(ArrayList<Member> members) {
        SuperWriter.writeToFile(members);
    }


}
