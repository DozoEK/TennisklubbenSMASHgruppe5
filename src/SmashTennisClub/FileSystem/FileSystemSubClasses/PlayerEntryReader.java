package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.time.LocalDate;

public class PlayerEntryReader extends SuperReader<PlayerEntry> {

    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Player_Entry_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 11;
    }

    @Override
    protected PlayerEntry parseAttributes(String[] parts) {
        int playerEntryId = Integer.parseInt(parts[0]);
        int memberId = Integer.parseInt(parts[1]);
        String memberName = parts[2];
        boolean isTrainingMatch = Boolean.parseBoolean(parts[3]);
        boolean isTournamentMatch = Boolean.parseBoolean(parts[4]);
        int tournamentPlacement = Integer.parseInt(parts[5]);
        DisciplineType playerEntryDiscipline = DisciplineType.valueOf(parts[6]);
        LocalDate playerEntryDate = LocalDate.parse(parts[7]);
        int setsPlayed = Integer.parseInt(parts[8]);
        int setsWon = Integer.parseInt(parts[9]);
        boolean matchWinner = Boolean.parseBoolean(parts[10]);

        return new PlayerEntry(playerEntryId, memberId, memberName, isTrainingMatch, isTournamentMatch,
                tournamentPlacement, playerEntryDiscipline, playerEntryDate,
                setsPlayed, setsWon, matchWinner);
    }
}
