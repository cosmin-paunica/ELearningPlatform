package services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashService {
    private static PasswordHashService instance = null;

    private PasswordHashService() {}

    public static PasswordHashService getInstance() {
        if (PasswordHashService.instance == null) {
            PasswordHashService.instance = new PasswordHashService();
        }
        return PasswordHashService.instance;
    }

    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] byteHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, byteHash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
            hexString.insert(0, '0');
        return hexString.toString();
    }

    public boolean verify(String password, String hashedPassword) {
        try {
            String hash = this.hash(password);
            if (hash.equals(hashedPassword)) {
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
