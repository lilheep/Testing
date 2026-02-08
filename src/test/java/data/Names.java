package data;

import impl.NamesImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Names implements NamesImpl {
    @Override
    public List<String> getListFirstName() {
        return readResource("firstName.txt");
    }

    @Override
    public List<String> getListSurname() {
        return readResource("surname.txt");
    }

    private List<String> readResource(String fileName) {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (is == null) {
            throw new RuntimeException(fileName + " not found in classpath");
        }

        return new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.toList());
    }
}
