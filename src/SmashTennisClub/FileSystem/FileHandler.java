package SmashTennisClub.FileSystem;

import SmashTennisClub.FileSystem.FileSystemSubClasses.*;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.CompetitivePlayerStats;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.util.ArrayList;

public class FileHandler {

    MemberReader memberReader = new MemberReader();
    MemberWriter memberWriter = new MemberWriter();
    QuotaWriter quotaWriter = new QuotaWriter();
    QuotaReader quotaReader = new QuotaReader();
    PlayerEntryWriter playerEntryWriter = new PlayerEntryWriter();
    CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
    CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();


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


    public void printAllStats() {
        ArrayList<CompetitivePlayerStats> stats = cpsr.readFromFile();
        for (CompetitivePlayerStats stat : stats) {
            System.out.println(stat);
        }
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

    public void saveCompetitivePlayerStatsWriter(ArrayList<CompetitivePlayerStats> stats) {
        cpsw.writeToFile(stats);
    }


}
