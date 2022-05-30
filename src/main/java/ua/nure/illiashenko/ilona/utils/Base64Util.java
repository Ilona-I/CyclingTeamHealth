package ua.nure.illiashenko.ilona.utils;

import java.util.Base64;

public class Base64Util {

    public String decodeString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
