package SmashTennisClub.FileSystem;

import SmashTennisClub.FileSystem.FileSystemSubClasses.*;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.MatchSession;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.util.ArrayList;

public class FileHandler {

    MemberReader memberReader = new MemberReader();
    MemberWriter memberWriter = new MemberWriter();
    QuotaWriter quotaWriter = new QuotaWriter();
    QuotaReader quotaReader = new QuotaReader();
    PlayerEntryWriter playerEntryWriter = new PlayerEntryWriter();
    MatchSessionWriter matchSessionsWriter = new MatchSessionWriter();


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

    public void savePlayerEntries(ArrayList<PlayerEntry> playerEntries) {
        playerEntryWriter.writeToFile(playerEntries);
    }

    public void saveMatchSessions(ArrayList<MatchSession> matchSessions) {
        matchSessionsWriter.writeToFile(matchSessions);
    }



}
