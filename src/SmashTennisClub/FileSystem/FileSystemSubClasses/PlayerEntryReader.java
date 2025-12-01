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
        return 5;
    }

    @Override
    protected PlayerEntry parseAttributes(String[] parts) {

        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        int setsPlayed = Integer.parseInt(parts[2]);
        int setsWon = Integer.parseInt(parts[3]);
        boolean matchWinner = Boolean.parseBoolean(parts[4]);

        return new PlayerEntry(memberId, memberName, setsPlayed, setsWon, matchWinner);
    }
}
