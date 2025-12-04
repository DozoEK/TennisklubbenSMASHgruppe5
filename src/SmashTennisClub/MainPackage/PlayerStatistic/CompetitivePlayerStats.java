package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;

public class CompetitivePlayerStats {
    private int memberId;
    private String memberName;
    private Gender gender;
    private MemberType memberType;
    private DisciplineType disciplineType;

    private int totalSetsPlayed;
    private int totalSetsWon;
    private int tournamentCount;
    private int tournamentWins;
    private double setWinRate;
    private double avgTournamentPlacement;


    public CompetitivePlayerStats(int memberId, String memberName, Gender gender, MemberType memberType,
                                  DisciplineType disciplineType, int totalSetsPlayed, int totalSetsWon,
                                  int tournamentCount, int tournamentWins, double setWinRate,
                                  double avgTournamentPlacement) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.gender = gender;
        this.memberType = memberType;
        this.disciplineType = disciplineType;

        this.totalSetsPlayed = totalSetsPlayed;
        this.totalSetsWon = totalSetsWon;
        this.tournamentCount = tournamentCount;
        this.tournamentWins = tournamentWins;
        this.setWinRate = setWinRate;
        this.avgTournamentPlacement = avgTournamentPlacement;
    }


    //getters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(DisciplineType disciplineType) {
        this.disciplineType = disciplineType;
    }

    public int getTotalSetsPlayed() {
        return totalSetsPlayed;
    }


    //Setters

    public void setTotalSetsPlayed(int totalSetsPlayed) {
        this.totalSetsPlayed = totalSetsPlayed;
    }

    public int getTotalSetsWon() {
        return totalSetsWon;
    }

    public void setTotalSetsWon(int totalSetsWon) {
        this.totalSetsWon = totalSetsWon;
    }

    public int getTournamentCount() {
        return tournamentCount;
    }

    public void setTournamentCount(int tournamentCount) {
        this.tournamentCount = tournamentCount;
    }

    public int getTournamentWins() {
        return tournamentWins;
    }

    public void setTournamentWins(int tournamentWins) {
        this.tournamentWins = tournamentWins;
    }

    public double getSetWinRate() {
        return setWinRate;
    }

    public void setSetWinRate(double setWinRate) {
        this.setWinRate = setWinRate;
    }

    public double getAvgTournamentPlacement() {
        return avgTournamentPlacement;
    }

    public void setAvgTournamentPlacement(double avgTournamentPlacement) {
        this.avgTournamentPlacement = avgTournamentPlacement;
    }

    public void addSets(int played, int won) {
        this.totalSetsPlayed += played;
        this.totalSetsWon += won;
    }

    public void addTournamentPlacement(int placement) {
        this.tournamentCount++;
        if (placement == 1) {
            this.tournamentWins++;
        }
    }

    //toString
    @Override
    public String toString() {
        return "CompetitivePlayerStats{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", gender=" + gender +
                ", memberType=" + memberType +
                ", disciplineType=" + disciplineType +
                ", totalSetsPlayed=" + totalSetsPlayed +
                ", totalSetsWon=" + totalSetsWon +
                ", tournamentCount=" + tournamentCount +
                ", tournamentWins=" + tournamentWins +
                ", setWinRate=" + setWinRate +
                ", avgTournamentPlacement=" + avgTournamentPlacement +
                '}';
    }

}
