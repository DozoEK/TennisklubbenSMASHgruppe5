package SmashTennisClub.MainPackage.PlayerStatistic;

public class PlayerEntry {

    private int playerEntryId;
    private int memberId;
    private String memberName;
    private int setsPlayed;
    private int setsWon;
    private boolean matchWinner;

    public PlayerEntry(int playerEntryId, int memberId, String memberName, int setsPlayed, int setsWon, boolean matchWinner) {
        this.playerEntryId = playerEntryId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.setsPlayed = setsPlayed;
        this.setsWon = setsWon;
        this.matchWinner = matchWinner;
    }

    //Getters
    public int getPlayerEntryId() {
        return playerEntryId;
    }

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
    public void setPlayerEntryId(int playerEntryId) {
        this.playerEntryId = playerEntryId;
    }

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
                "playerEntryId=" + playerEntryId +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", setsPlayed=" + setsPlayed +
                ", setsWon=" + setsWon +
                ", matchWinner=" + matchWinner +
                '}';
    }

}
