package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.PlayerStatistic.CompetitivePlayerStats;

public class CompetitivePlayerStatsWriter extends SuperWriter<CompetitivePlayerStats> {


    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Competitive_Player_Stats_Index.csv";
    }

    @Override
    protected String[] objectToParts(CompetitivePlayerStats object) {
        String[] parts = new String[11];

        parts[0] = String.valueOf(object.getMemberId());
        parts[1] = object.getMemberName();
        parts[2] = object.getGender().toString();
        parts[3] = object.getMemberType().toString();
        parts[4] = object.getDisciplineType().toString();
        parts[5] = String.valueOf(object.getTotalSetsPlayed());
        parts[6] = String.valueOf(object.getTotalSetsWon());
        parts[7] = String.valueOf(object.getTournamentCount());
        parts[8] = String.valueOf(object.getTournamentWins());
        parts[9] = String.valueOf(object.getSetWinRate());
        parts[10] = String.valueOf(object.getAvgTournamentPlacement());

        return parts;
    }
}
