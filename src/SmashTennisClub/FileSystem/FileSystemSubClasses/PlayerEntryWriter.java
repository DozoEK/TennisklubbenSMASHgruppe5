package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

public class PlayerEntryWriter extends SuperWriter<PlayerEntry> {

    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Player_Entry_Index.csv";
    }

    @Override
    protected String[] objectToParts(PlayerEntry object) {
        String[] parts = new String[11];

        parts[0] = String.valueOf(object.getPlayerEntryId());
        parts[1] = String.valueOf(object.getMemberId());
        parts[2] = object.getMemberName();
        parts[3] = String.valueOf(object.isTrainingMatch());
        parts[4] = String.valueOf(object.isTournamentMatch());
        parts[5] = String.valueOf(object.getTournamentPlacement());
        parts[6] = object.getPlayerEntryDiscipline().toString();
        parts[7] = object.getPlayerEntryDate().toString();
        parts[8] = String.valueOf(object.getSetsPlayed());
        parts[9] = String.valueOf(object.getSetsWon());
        parts[10] = String.valueOf(object.isMatchWinner());

        return parts;
    }
}
