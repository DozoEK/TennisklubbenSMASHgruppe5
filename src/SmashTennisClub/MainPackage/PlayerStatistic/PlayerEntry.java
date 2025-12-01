package SmashTennisClub.MainPackage.PlayerStatistic;

public class PlayerEntry {

    private int memberId;
    private String memberName;
    private int setsPlayed;
    private int setsWon;
    private boolean matchWinner;

    public PlayerEntry(int memberId, String memberName, int setsPlayed, int setsWon, boolean matchWinner) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.setsPlayed = setsPlayed;
        this.setsWon = setsWon;
        this.matchWinner = matchWinner;
    }

    //Getters
    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getSetsPlayed() {
        return setsPlayed;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public boolean isMatchWinner() {
        return matchWinner;
    }

    //setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setSetsPlayed(int setsPlayed) {
        this.setsPlayed = setsPlayed;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void setMatchWinner(boolean matchWinner) {
        this.matchWinner = matchWinner;
    }


    //toString


    @Override
    public String toString() {
        return "PlayerEntry{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", setsPlayed=" + setsPlayed +
                ", setsWon=" + setsWon +
                ", matchWinner=" + matchWinner +
                '}';
    }

}
