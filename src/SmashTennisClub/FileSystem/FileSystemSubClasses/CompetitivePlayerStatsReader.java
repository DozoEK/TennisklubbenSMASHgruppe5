package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.PlayerStatistic.CompetitivePlayerStats;

public class CompetitivePlayerStatsReader extends SuperReader<CompetitivePlayerStats> {


    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Competitive_Player_Stats_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 11;
    }

    @Override
    protected CompetitivePlayerStats parseAttributes(String[] parts) {
        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        Gender gender = Gender.valueOf(parts[2]);
        MemberType memberType = MemberType.valueOf(parts[3]);
        DisciplineType disciplineType = DisciplineType.valueOf(parts[4]);
        int totalSetsPlayed = Integer.parseInt(parts[5]);
        int totalSetsWon = Integer.parseInt(parts[6]);
        int tournamentCount = Integer.parseInt(parts[7]);
        int tournamentWins = Integer.parseInt(parts[8]);
        double setWinRate = Double.parseDouble(parts[9]);
        double avgTournamentPlacement = Double.parseDouble(parts[10]);

        return new CompetitivePlayerStats(memberId,memberName, gender, memberType, disciplineType, totalSetsPlayed,
                totalSetsWon, tournamentCount, tournamentWins, setWinRate, avgTournamentPlacement);
    }


}
