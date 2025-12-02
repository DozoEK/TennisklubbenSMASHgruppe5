package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

public class PlayerEntryReader extends SuperReader<PlayerEntry> {

    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Player_Entry_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 6;
    }

    @Override
    protected PlayerEntry parseAttributes(String[] parts) {
        int playerEntryId = Integer.parseInt(parts[0]);
        int memberId = Integer.parseInt(parts[1]);
        String memberName = parts[2];
        int setsPlayed = Integer.parseInt(parts[3]);
        int setsWon = Integer.parseInt(parts[4]);
        boolean matchWinner = Boolean.parseBoolean(parts[5]);

        return new PlayerEntry(playerEntryId, memberId, memberName, setsPlayed, setsWon, matchWinner);
    }
}
