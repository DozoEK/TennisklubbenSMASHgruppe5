package SmashTennisClub.FileSystem;

import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaWriter;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;

public class FileHandler {

    MemberReader memberReader = new MemberReader();
    MemberWriter memberWriter = new MemberWriter();
    QuotaWriter quotaWriter = new QuotaWriter();
    QuotaReader quotaReader = new QuotaReader();


    public void printAllMembers() {
        ArrayList<Member> members = memberReader.readFromFile();
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void printAllQuotas() {
        ArrayList<Quota> quotas = quotaReader.readFromFile();
        for (Quota quota : quotas) {
            System.out.println(quota);
        }
    }


    public Member findMemberById(int memberId) {
        MemberReader reader = new MemberReader();
        ArrayList<Member> members = reader.readFromFile();

        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        System.out.println("Medlem: " + memberId + " er ikke fundet!");
        return null;
    }


    public void saveMembers(ArrayList<Member> members) {
        memberWriter.writeToFile(members);
    }

    public void saveQuotas(ArrayList<Quota> quotas) {
        quotaWriter.writeToFile(quotas);
    }

}
