package SmashTennisClub.FileSystem;

import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;

public class FileHandler {

    MemberReader memberReader = new MemberReader();
    MemberWriter memberWriter = new MemberWriter();


    public void printAllMembers() {
        ArrayList<Member> members = memberReader.readFromFile();
        for (Member member : members) {
            System.out.println(member);
        }
    }


    public void saveMembers(ArrayList<Member> members) {
        memberWriter.writeToFile(members);
    }
}
