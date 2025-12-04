package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.FileSystem.FileSystemSubClasses.CompetitivePlayerStatsReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.CompetitivePlayerStatsWriter;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;

import static SmashTennisClub.MainPackage.EnumLists.DisciplineType.SINGLE;

public class CompetitivePlayerHelper {


    private MemberReader mr = new MemberReader();
    private CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
    private CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
    private PlayerEntryReader per = new PlayerEntryReader();



    public void createStatsFromCompetitiveMembers() {
        ArrayList<Member> members = mr.readFromFile();
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();

        for (Member member : members) {
            if (member.getCompetitivePlayer()) {
                CompetitivePlayerStats stats = new CompetitivePlayerStats(
                        member.getMemberId(),
                        member.getMemberName(),
                        member.getGenderOfMember(),
                        member.getMembershipType(),
                        SINGLE,
                        0, 0, 0, 0, 0.0, 0.0
                );
                statsList.add(stats);
            }
        }

        cpsw.writeToFile(statsList);
    }



    public void createStatsForSingleCompetitiveMember(Member member) {
        if (!member.getCompetitivePlayer()) {
            System.out.println("Medlemmet " + member.getMemberName() + " er ikke en konkurrencespiller. Stats oprettes ikke.");
            return;
        }

        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();


        boolean exists = false;
        for (CompetitivePlayerStats s : statsList) {
            if (s.getMemberId() == member.getMemberId()) {
                exists = true;
                break;
            }
        }

        if (exists == true) {
            System.out.println("Stats for medlem " + member.getMemberName() + " findes allerede.");
            return;
        }

        CompetitivePlayerStats stats = new CompetitivePlayerStats(
                member.getMemberId(),
                member.getMemberName(),
                member.getGenderOfMember(),
                member.getMembershipType(),
                SINGLE,
                0, 0, 0, 0, 0.0, 0.0
        );

        statsList.add(stats);

        ArrayList<PlayerEntry> playerEntries = per.readFromFile();
        updateSinglePlayerStats(stats, playerEntries);

        cpsw.writeToFile(statsList);

        System.out.println("CompetitivePlayerStats oprettet og opdateret for medlem: " + member.getMemberName());
    }



    public void updateAllCompetitiveStats() {
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        for (CompetitivePlayerStats stats : statsList) {
            updateSinglePlayerStats(stats, playerEntries);
        }

        cpsw.writeToFile(statsList);
    }




    public void updateSinglePlayerStats(CompetitivePlayerStats stats, ArrayList<PlayerEntry> playerEntries) {
        updateDisciplineType(stats, playerEntries);
        updateSetsStatistics(stats, playerEntries);
        calculateSetWinRate(stats);

        updateTournamentStatistics(stats, playerEntries);
    }


    private void updateDisciplineType(CompetitivePlayerStats stats, ArrayList<PlayerEntry> playerEntries) {
        DisciplineType disciplineType = SINGLE;

        for (PlayerEntry entry : playerEntries) {
            if (entry.getMemberId() == stats.getMemberId()) {
                disciplineType = entry.getPlayerEntryDiscipline();
            }
        }

        stats.setDisciplineType(disciplineType);
    }


    private void updateSetsStatistics(CompetitivePlayerStats stats, ArrayList<PlayerEntry> playerEntries) {
        int totalSetsPlayed = 0;
        int totalSetsWon = 0;

        for (PlayerEntry entry : playerEntries) {
            if (entry.getMemberId() == stats.getMemberId()) {
                totalSetsPlayed += entry.getSetsPlayed();
                totalSetsWon += entry.getSetsWon();
            }
        }

        stats.setTotalSetsPlayed(totalSetsPlayed);
        stats.setTotalSetsWon(totalSetsWon);
    }


    private void calculateSetWinRate(CompetitivePlayerStats stats) {
        if (stats.getTotalSetsPlayed() > 0) {
            double rawWinRate = ((double) stats.getTotalSetsWon() / stats.getTotalSetsPlayed()) * 100;
            double winRate = Math.round(rawWinRate * 10.0) / 10.0;
            stats.setSetWinRate(winRate);
        } else {
            stats.setSetWinRate(0.0);
        }
    }


    private void updateTournamentStatistics(CompetitivePlayerStats stats, ArrayList<PlayerEntry> playerEntries) {
        int tournamentCount = 0;
        int tournamentWins = 0;
        int totalPlacement = 0;

        for (PlayerEntry entry : playerEntries) {
            if (entry.getMemberId() == stats.getMemberId() && entry.isTournamentMatch()) {
                tournamentCount++;
                totalPlacement += entry.getTournamentPlacement();

                if (entry.getTournamentPlacement() == 1) {
                    tournamentWins++;
                }
            }
        }

        stats.setTournamentCount(tournamentCount);
        stats.setTournamentWins(tournamentWins);

        if (tournamentCount > 0) {
            double rawAvgPlacement = (double) totalPlacement / tournamentCount;
            double avgPlacement = Math.round(rawAvgPlacement * 10.0) / 10.0;
            stats.setAvgTournamentPlacement(avgPlacement);
        }
    }




    public void showTop5All() {
        ArrayList<CompetitivePlayerStats> cpsAll = cpsr.readFromFile();

        printCategory("SENIOR MALE", cpsAll, MemberType.SENIOR, Gender.MALE);
        printCategory("SENIOR FEMALE", cpsAll, MemberType.SENIOR, Gender.FEMALE);
        printCategory("JUNIOR MALE", cpsAll, MemberType.JUNIOR, Gender.MALE);
        printCategory("JUNIOR FEMALE", cpsAll, MemberType.JUNIOR, Gender.FEMALE);
    }


    private void printCategory(String title, ArrayList<CompetitivePlayerStats> all, MemberType type, Gender gender) {
        ArrayList<CompetitivePlayerStats> filtered = new ArrayList<>();

        for (CompetitivePlayerStats s : all) {
            if (s.getMemberType() == type && s.getGender() == gender) {
                filtered.add(s);
            }
        }

        filtered.sort((a, b) -> Double.compare(b.getSetWinRate(), a.getSetWinRate()));

        System.out.println("\n===== TOP 5 " + title + " =====");

        for (int i = 0; i < Math.min(5, filtered.size()); i++) {
            System.out.println((i + 1) + ". " + filtered.get(i).getMemberName() +
                    " - Set winrate: " + filtered.get(i).getSetWinRate() + "%" + "  -  " +
                    "Gennemsnitlig turneringsplacering: " + filtered.get(i).getAvgTournamentPlacement());
        }
    }
}
