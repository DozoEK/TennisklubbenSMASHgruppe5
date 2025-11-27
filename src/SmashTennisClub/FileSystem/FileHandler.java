package SmashTennisClub.FileSystem;

import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;

public class FileHandler {

    MemberWriter superwriter = new MemberWriter();
    MemberReader memberReader = new MemberReader();


    public void printAllMembers() {
        ArrayList<Member> members = memberReader.readFromFile();
        for (Member member : members) {
            System.out.println(member);
        }
    }


    public void saveMembers(ArrayList<Member> members) {
        MemberWriter.writeToFile(members);
    }


}
