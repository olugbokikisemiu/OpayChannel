package util;

import io.github.cdimascio.dotenv.Dotenv;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Common {
    public String CalculateHash(String hashValue, String hashAlgo) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(hashAlgo);
        byte[] digestByte = digest.digest(hashValue.getBytes());
        BigInteger bigInt = new BigInteger(1, digestByte);
        return bigInt.toString(16);
    }

    public String ReadEnv(String envName){
        Dotenv dotEnv = Dotenv.load();
        return dotEnv.get(envName);
    }
}
