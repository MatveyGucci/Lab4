import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MainPassGenerator {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        PassGenerator passGenerator = new PassGenerator();
        passGenerator.textSetter();
        passGenerator.generate(false); // true - md5, false - sha-1
        passGenerator.csvFileAdder();

    }


}
