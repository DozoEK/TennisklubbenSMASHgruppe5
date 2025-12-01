package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;

import java.time.LocalDate;
import java.util.ArrayList;


public class MatchSession {

    private int sessionId;
    private boolean isTournament;
    private int tournamentPlacement;
    private DisciplineType sessionDiscipline;   // SINGLE / DOUBLE / MIXED_DOUBLE
    private LocalDate sessionDate;
    private ArrayList<PlayerEntry> playerEntries; // ALWAYS size 1, 2 or 4


    public MatchSession(int sessionId, boolean isTournament, int tournamentPlacement, DisciplineType sessionDiscipline,
                        LocalDate sessionDate, ArrayList<PlayerEntry> playerEntries) {

        if (isTournament == true && sessionDiscipline == DisciplineType.SINGLE && playerEntries.size() != 1)
            throw new IllegalArgumentException("Turneringer kan kun indskrives ");

        if (sessionDiscipline == DisciplineType.SINGLE && playerEntries.size() != 2)
            throw new IllegalArgumentException("Singles requires exactly 2 players");

        if (sessionDiscipline == DisciplineType.DOUBLE && playerEntries.size() != 4)
            throw new IllegalArgumentException("Doubles requires exactly 4 players");


        this.sessionId = sessionId;
        this.isTournament = isTournament;
        this.tournamentPlacement = tournamentPlacement;
        this.sessionDiscipline = sessionDiscipline;
        this.sessionDate = sessionDate;
        this.playerEntries = playerEntries;
    }



    //getters
    public int getSessionId() {
        return sessionId;
    }

    public boolean isTournament() {
        return isTournament;
    }

    public int getTournamentPlacement() {
        return tournamentPlacement;
    }

    public DisciplineType getSessionDiscipline() {
        return sessionDiscipline;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public ArrayList<PlayerEntry> getPlayerEntries() {
        return playerEntries;
    }


    //Setters
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setTournament(boolean tournament) {
        isTournament = tournament;
    }

    public void setTournamentPlacement(int tournamentPlacement) {
        this.tournamentPlacement = tournamentPlacement;
    }

    public void setSessionDiscipline(DisciplineType sessionDiscipline) {
        this.sessionDiscipline = sessionDiscipline;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setPlayerEntries(ArrayList<PlayerEntry> playerEntries) {
        this.playerEntries = playerEntries;
    }


    //toString
    @Override
    public String toString() {
        return "MatchSession{" +
                "sessionId=" + sessionId +
                ", isTournament=" + isTournament +
                ", tournamentPlacement=" + tournamentPlacement +
                ", sessionDiscipline=" + sessionDiscipline +
                ", sessionDate=" + sessionDate +
                ", playerEntries=" + playerEntries +
                '}';
    }

}
