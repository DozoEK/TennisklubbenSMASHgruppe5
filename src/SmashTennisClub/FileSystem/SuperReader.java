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
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == indexMaxLength) {
                    Placeholder parsedPlaceholder = parseAttributes(parts);
                    objects.add(parsedPlaceholder);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return objects;
    }

    public String getCompleteFileName() {
        return getCSVFilePathReader();
    }

}

