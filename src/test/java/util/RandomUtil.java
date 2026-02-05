package util;

import data.Names;
import impl.NamesImpl;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    private final Random random = new Random();
    private final String allowedSymbols =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~";
    private static final NamesImpl names = new Names();
    private static final List<String> firstNames = names.getListFirstName();
    private static final List<String> surnames = names.getListSurname();

    public String generateEmail(String domain) {
        StringBuilder localPart = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            localPart.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length() + 1)));
        }
        return localPart + "@" + domain;
    }

    public String generateFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public String generateSurname() {
        return surnames.get(random.nextInt(surnames.size()));
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            password.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length() + 1)));
        }
        return password.toString();
    }

    public int generateId() {
        return random.nextInt(13, 1000);
    }

    public int generateValidId() { return random.nextInt(1, 13); }
}
