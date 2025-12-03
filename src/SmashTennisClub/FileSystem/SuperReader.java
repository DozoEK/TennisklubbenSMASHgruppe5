package SmashTennisClub.FileSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SuperReader<Placeholder> {


    //Path skal ændres i hver subclass!
    protected abstract String getCSVFilePathReader();


    //indexLineLength skal sættes i alle subclasses!
    protected abstract int setMaxIndexLength();


    //Parsing af attributes skal laves i alle subclasses!
    protected abstract Placeholder parseAttributes(String [] parts);

    public ArrayList<Placeholder> readFromFile() {
        ArrayList<Placeholder> objects = new ArrayList<>();
        int indexMaxLength = setMaxIndexLength();

        try (BufferedReader bufferedReader  = new BufferedReader(new FileReader(getCSVFilePathReader()))) {
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",", -1);
                if (parts.length == indexMaxLength) {
                    try {
                        Placeholder parsedPlaceholder = parseAttributes(parts);
                        objects.add(parsedPlaceholder);
                    } catch (Exception e) {
                        System.err.println("Fejl ved parsing af linje " + lineNumber + ": " + line);
                        System.err.println("Fejlbesked: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Advarsel: Linje " + lineNumber + " har forkert antal felter. Forventet: "
                            + indexMaxLength + ", Fundet: " + parts.length);
                    System.err.println("Indhold: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return objects;
    }

}