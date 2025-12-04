package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.FileSystem.FileSystemSubClasses.CompetitivePlayerStatsReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.CompetitivePlayerStatsWriter;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.ErrorAndValidation.CustomExceptions.SmashException;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationInterface;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationMethods;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.util.ArrayList;
import java.util.Scanner;

import static SmashTennisClub.MainPackage.EnumLists.DisciplineType.SINGLE;

public class CompetitivePlayerHelper {


    private final MemberReader mr = new MemberReader();
    private final CompetitivePlayerStatsWriter cpsw = new CompetitivePlayerStatsWriter();
    private final CompetitivePlayerStatsReader cpsr = new CompetitivePlayerStatsReader();
    private final PlayerEntryReader per = new PlayerEntryReader();
    ValidationInterface validator = new ValidationMethods();


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

        if (exists) {
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


    public void showTop5AllByDiscipline() {
        ArrayList<CompetitivePlayerStats> cpsAll = cpsr.readFromFile();
        Scanner scanner = new Scanner(System.in);

        String categoryChoice;

        System.out.println();
        System.out.println("Vælg kategori:");
        System.out.println("1. Senior Male");
        System.out.println("2. Senior Female");
        System.out.println("3. Junior Male");
        System.out.println("4. Junior Female");
        System.out.print("Indtast valg (1-4): ");


        categoryChoice = scanner.nextLine().trim();

        try {
            validator.validateLettersOrNumbersOnly(categoryChoice);
        }
        catch (SmashException e) {
            System.out.println(e.getMessage());
        }



        MemberType type = null;
        Gender gender = null;
        String title = null;

        switch (categoryChoice) {
            case "1" -> {
                type = MemberType.SENIOR;
                gender = Gender.MALE;
                title = "SENIOR HERRE";
            }
            case "2" -> {
                type = MemberType.SENIOR;
                gender = Gender.FEMALE;
                title = "SENIOR KVINDE";
            }
            case "3" -> {
                type = MemberType.JUNIOR;
                gender = Gender.MALE;
                title = "JUNIOR DRENG";
            }
            case "4" -> {
                type = MemberType.JUNIOR;
                gender = Gender.FEMALE;
                title = "JUNIOR PIGE";
            }
            default -> {
                System.out.println("Ugyldigt valg. Afslutter.");
                return;
            }
        }

        int disciplineChoice = -1;

        while (true) {
            System.out.println("\nVælg disciplin:");

            DisciplineType[] allDisciplines = DisciplineType.values();

            for (int i = 0; i < allDisciplines.length; i++) {
                System.out.println((i + 1) + ". " + allDisciplines[i]);
            }

            System.out.print("Indtast valg (1-" + allDisciplines.length + "): ");
            String input = scanner.nextLine().trim();

            try {
                disciplineChoice = validator.validateInt(input);

                if (disciplineChoice >= 1 && disciplineChoice <= allDisciplines.length) {
                    break;
                } else {
                    System.out.println("Ugyldigt valg. Prøv igen.");
                }
            }
            catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }

        DisciplineType selectedDiscipline = DisciplineType.values()[disciplineChoice - 1];

        printTop5ByCategoryAndDiscipline(title, cpsAll, type, gender, selectedDiscipline);
    }


    private void printTop5ByCategoryAndDiscipline(String title, ArrayList<CompetitivePlayerStats> all,
                                                  MemberType type, Gender gender, DisciplineType discipline) {
        ArrayList<CompetitivePlayerStats> filtered = new ArrayList<>();

        for (CompetitivePlayerStats s : all) {
            if (s.getMemberType() == type &&
                    s.getGender() == gender &&
                    s.getDisciplineType() == discipline) {
                filtered.add(s);
            }
        }

        filtered.sort((a, b) -> Double.compare(b.getSetWinRate(), a.getSetWinRate()));

        System.out.println("\n===== TOP 5 " + title + " - " + discipline + " =====");

        // Header
        System.out.printf("%-5s | %-20s | %-12s | %-25s%n", "Plads", "Navn", "Set Winrate", "Gennemsnitlig Turneringsplacering");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < 5; i++) {
            if (i < filtered.size()) {
                CompetitivePlayerStats player = filtered.get(i);
                System.out.printf("%-5d | %-20s | %-12.2f | %-25.2f%n",
                        (i + 1),
                        player.getMemberName(),
                        player.getSetWinRate(),
                        player.getAvgTournamentPlacement());
            } else {
                System.out.printf("%-5d | %-20s | %-12s | %-25s%n",
                        (i + 1),
                        "-",
                        "-",
                        "-");
            }
        }

        System.out.println("--------------------------------------------------------------------------");
    }


    public void printAllCompetitivePlayerStats() {
        ArrayList<CompetitivePlayerStats> statsList = cpsr.readFromFile();

        if (statsList.isEmpty()) {
            System.out.println("Ingen CompetitivePlayerStats fundet.");
            return;
        }

        System.out.println("\n===== ALLE COMPETITIVE PLAYER STATS =====");

        System.out.printf("%-5s | %-20s | %-10s | %-12s | %-12s | %-12s | %-12s | %-15s | %-15s | %-15s | %-15s%n",
                "ID", "Navn", "Køn", "Medlemstype", "Disciplin", "Sets Spillet", "Sets Vundet",
                "Set Winrate", "Turneringer", "Turneringssejre", "Genn. Placering");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (CompetitivePlayerStats stats : statsList) {
            System.out.printf("%-5d | %-20s | %-10s | %-12s | %-12s | %-12d | %-12d | %-15.2f | %-15d | %-15d | %-15.2f%n",
                    stats.getMemberId(),
                    stats.getMemberName(),
                    stats.getGender(),
                    stats.getMemberType(),
                    stats.getDisciplineType(),
                    stats.getTotalSetsPlayed(),
                    stats.getTotalSetsWon(),
                    stats.getSetWinRate(),
                    stats.getTournamentCount(),
                    stats.getTournamentWins(),
                    stats.getAvgTournamentPlacement());
        }


        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
