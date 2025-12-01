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
        String[] parts = new String[7];

        parts[0] = String.valueOf(object.getMemberId());
        parts[1] = object.getMemberName();
        parts[2] = String.valueOf(object.getSetsPlayed());
        parts[3] = String.valueOf(object.getSetsWon());
        parts[4] = String.valueOf(object.isMatchWinner());

        return parts;
    }
}
