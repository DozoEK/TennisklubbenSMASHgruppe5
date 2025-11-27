package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;

import java.sql.Date;

import java.util.ArrayList;

public class Tournament {

    int tournamentId;
    String tournamentName = "";
    DisciplineType disciplineTypeForTournament;
    Date dateOfTournament;
    int memberId; //(for alle spillere)
    String memberName;
    int amountOfWonSets;
    int tournamentPlacement;

    public Tournament(int memberId, int tournamentId, String tournamentName, DisciplineType disciplineTypeForTournament, Date dateOfTournament, String memberName, int amountOfWonSets, int tournamentPlacement) {
        this.memberId = memberId;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.disciplineTypeForTournament = disciplineTypeForTournament;
        this.dateOfTournament = dateOfTournament;
        this.memberName = memberName;
        this.amountOfWonSets = amountOfWonSets;
        this.tournamentPlacement = tournamentPlacement;
    }
    public int getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public int getTournamentId() { return tournamentId; }
    public int getTournamentPlacement() { return tournamentPlacement; }

    @Override
    public String toString() {
        return memberName + " (" + memberId + ") - placement: " + tournamentPlacement;
    }

}