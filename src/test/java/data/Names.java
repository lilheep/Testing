package data;

import impl.NamesImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Names implements NamesImpl {
    private BufferedReader bufferedReader;
    private List<String> listNames = new ArrayList<>();
    private List<String> listSurnames = new ArrayList<>();

    @Override
    public List<String> getListFirstName() {
        try {
            bufferedReader = new BufferedReader(new FileReader("firstName.txt"));
            String line = bufferedReader.readLine();

            while (line != null) {
                listNames.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listNames;
    }

    @Override
    public List<String> getListSurname() {
        try {
            bufferedReader = new BufferedReader(new FileReader("surname.txt"));
            String line = bufferedReader.readLine();

            while (line != null) {
                listSurnames.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listSurnames;
    }
}
