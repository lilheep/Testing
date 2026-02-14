package util;

import constants.ApiConstants;
import data.Names;
import impl.NamesImpl;
import models.response.appuser.RootGetListUsersResponse;
import models.response.collection.RootGetListRecordsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    private final Random random = new Random();
    private final String allowedSymbols =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~";
    private final NamesImpl names = new Names();
    private final List<String> firstNames = names.getListFirstName();
    private final List<String> surnames = names.getListSurname();

    public String generateEmail(String domain) {
        StringBuilder localPart = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            localPart.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length())));
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
            password.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length())));
        }
        return password.toString();
    }

    public String generateUserIdOnProject(RootGetListUsersResponse response) {
        List<String> listUsersId = new ArrayList<>();
        for (var user : response.getData()) {
            if (user.getId().equals(ApiConstants.getMyId())) {
                continue;
            }
            listUsersId.add(user.getId());
        }

        return listUsersId.get(random.nextInt(listUsersId.size()));
    }

    public String generateStatusUserOnProject() {
        List<String> statuses = new ArrayList<>(List.of("active", "pending"));
        return statuses.get(random.nextInt(statuses.size()));
    }

    public int generateValidId() { return random.nextInt(1, 13); }

    public String generateRecordId(RootGetListRecordsResponse responseBody) {
        List<String> listRecordId = new ArrayList<>();
        for (var record : responseBody.getData()) {
            listRecordId.add(record.getId());
        }
        return listRecordId.get(random.nextInt(listRecordId.size()));
    }
}
