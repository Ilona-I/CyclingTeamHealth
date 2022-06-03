package ua.nure.illiashenko.ilona.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public String decodeString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    public String encodeString(String string){
        return Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
    }
}
