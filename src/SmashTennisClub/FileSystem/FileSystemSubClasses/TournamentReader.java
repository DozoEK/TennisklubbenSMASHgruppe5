package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.PlayerStatistic.Tournament;

import java.time.LocalDate;

public class TournamentReader extends SuperReader<Tournament> {


    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Tournament_index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 8;
    }

    @Override
    protected Tournament parseAttributes(String[] parts) {

        int tournamentId = Integer.parseInt(parts[0]);
        String tournamentName = parts[1];
        DisciplineType disciplineType = DisciplineType.valueOf(parts[2]);
        LocalDate dateOfTournament = LocalDate.parse(parts[3]);
        int memberId = Integer.parseInt(parts[4]);
        String memberName = parts[5];
        int amountOfWonSets = Integer.parseInt(parts[6]);
        int tournamentPlacement = Integer.parseInt(parts[7]);

        return new Tournament(  memberId, tournamentId, tournamentName, disciplineType,
                dateOfTournament, memberName, amountOfWonSets, tournamentPlacement);
    }



}
