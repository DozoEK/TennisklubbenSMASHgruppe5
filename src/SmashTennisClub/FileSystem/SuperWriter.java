package SmashTennisClub.FileSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SuperWriter<Placeholder> {

    //Path skal ændres i hver subclass!
    protected abstract String getCSVFilePathWriter();

    protected abstract String[] objectToParts(Placeholder object);


    public void writeToFile(ArrayList<Placeholder> objects) {
        try (FileWriter writer = new FileWriter(getCSVFilePathWriter())) {
            for (Placeholder object : objects) {
                String[] parts = objectToParts(object);
                String line = String.join(",", parts);
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved to file: " + getCSVFilePathWriter());

        } catch (IOException e) {
            System.out.println("Error while writing to file!" + e.getMessage());
        }
    }
}
