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

    //Skal kun bruges hver gang vi skal have nyt CSV fil!
    public void createStatsFromCompetitiveMembers() {
        MemberReader mr = new MemberReader();
        CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
        CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();

        ArrayList<Member> members = mr.readFromFile();
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();

        for (Member member : members) {
            if (member.getCompetitivePlayer() == true) {
                CompetitivePlayerStats stats = new CompetitivePlayerStats(member.getMemberId(), member.getMemberName(), member.getGenderOfMember(), member.getMembershipType(), SINGLE,
                        0, 0, 0, 0, 0.0, 0.0
                );
                statsList.add(stats);
            }
        }
        cpsw.writeToFile(statsList);
    }


    public void updateDisciplineTypeForAllCompetitivePlayerStats() {
            PlayerEntryReader per = new PlayerEntryReader();
            ArrayList<PlayerEntry> playerEntries = per.readFromFile();

            CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
            CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
            ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();


            for (CompetitivePlayerStats stats : statsList) {

                DisciplineType disciplineType = SINGLE;

                for (PlayerEntry entry : playerEntries) {
                    if (entry.getMemberId() == stats.getMemberId()) {

                        disciplineType = entry.getPlayerEntryDiscipline();
                    }
                }
                stats.setDisciplineType(disciplineType);
            }
        cpsw.writeToFile(statsList);
    }


    public void updateSetsStatisticsFromPlayerEntries() {
        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
        CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();


        for (CompetitivePlayerStats stats : statsList) {

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
        cpsw.writeToFile(statsList);
    }




    public void calculateSetWinRates() {
        CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
        CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();


        for (CompetitivePlayerStats stats : statsList) {

            if (stats.getTotalSetsPlayed() > 0) {
                double rawWinRate = ((double) stats.getTotalSetsWon() / stats.getTotalSetsPlayed()) * 100;
                double winRate = Math.round(rawWinRate * 10.0) / 10.0;

                stats.setSetWinRate(winRate);

            }
        }
        cpsw.writeToFile(statsList);
    }


    public void updateTournamentStatistics() {
        CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
        CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();

        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        for (CompetitivePlayerStats stats : statsList) {
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
        cpsw.writeToFile(statsList);
    }


    public void showTop5All() {
        CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
        ArrayList<CompetitivePlayerStats> cpsAll = cpsr.readFromFile();

        printCategory("SENIOR MALE", cpsAll, MemberType.SENIOR, Gender.MALE);
        printCategory("SENIOR FEMALE", cpsAll, MemberType.SENIOR, Gender.FEMALE);
        printCategory("JUNIOR MALE", cpsAll, MemberType.JUNIOR, Gender.MALE);
        printCategory("JUNIOR FEMALE", cpsAll, MemberType.JUNIOR, Gender.FEMALE);
    }

    private void printCategory(String title, ArrayList<CompetitivePlayerStats> all,
                               MemberType type, Gender gender) {
        ArrayList<CompetitivePlayerStats> filtered = new ArrayList<>();

        for (CompetitivePlayerStats s : all) {
            if (s.getMemberType() == type && s.getGender() == gender)
                filtered.add(s);
        }

        filtered.sort((a, b) -> Double.compare(b.getSetWinRate(), a.getSetWinRate()));

        System.out.println("\n===== TOP 5 " + title + " =====");

        for (int i = 0; i < Math.min(5, filtered.size()); i++) {
            System.out.println( (i+1) + ". " + filtered.get(i).getMemberName() +
                    " - Set winrate: " + filtered.get(i).getSetWinRate() + "%" + "  -  " +
                    "Gennemsnitlig turneringsplacering: " + filtered.get(i).getAvgTournamentPlacement());
        }
    }


}
