package data;

import impl.NamesImpl;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Names implements NamesImpl {
    private final String fileNames = "firstName.txt";
    private final String fileSurnames = "surname.txt";

    @Override
    public List<String> getListFirstName() {
        return readFile(fileNames);
    }

    @Override
    public List<String> getListSurname() {
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
