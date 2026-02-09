package data;

import impl.NamesImpl;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Names implements NamesImpl {

    @Override
    public List<String> getListFirstName() {
        String fileNames = "firstName.txt";
        return readFile(fileNames);
    }

    @Override
    public List<String> getListSurname() {
        String fileSurnames = "surname.txt";
        return readFile(fileSurnames);
    }

    private List<String> readFile(String file) {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(file);

        if (inputStream == null) {
            throw new RuntimeException("File not found");
        }

        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.toList());
    }
}
