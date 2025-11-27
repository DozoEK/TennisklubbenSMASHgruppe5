package SmashTennisClub.MainPackage.PlayerStatistic;

import java.util.ArrayList;

public class TournamentList {
    private ArrayList<Tournament> tournamentEntries = new ArrayList<>();

// Tilføjer et Tournament objekt ad gangen

    public void addToStatistic(Tournament entry) {
        if (tournamentEntries.size() >= 4) {
            System.out.println("Der kan maks registreres 4 spillere");
            return;
        }

        tournamentEntries.add(entry);
        System.out.println("Tilføjet spiller: " + entry.getMemberName());
    }

    public boolean hasMinimumPlayers() {
        return tournamentEntries.size() >= 2;
    }

//Returnerer alle registrerede spilleres turneringsdata

    public ArrayList<Tournament> getTournamentEntries() {
        return tournamentEntries;
    }

//Tømmer listen hvis man starter en ny turneringsregistrering

    public void clear() {
        tournamentEntries.clear();
    }
}
