package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.util.ArrayList;

public class PlayerEntryWriter extends SuperWriter<PlayerEntry> {

    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Player_Entry_Index.csv";
    }

    @Override
    protected String[] objectToParts(PlayerEntry object) {
        String[] parts = new String[6];

        parts[0] = String.valueOf(object.getPlayerEntryId());
        parts[1] = String.valueOf(object.getMemberId());
        parts[2] = object.getMemberName();
        parts[3] = String.valueOf(object.getSetsPlayed());
        parts[4] = String.valueOf(object.getSetsWon());
        parts[5] = String.valueOf(object.isMatchWinner());

        return parts;
    }
}
