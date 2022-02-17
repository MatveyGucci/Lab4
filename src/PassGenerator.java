import org.w3c.dom.Text;
222
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PassGenerator {
    MessageDigest md = MessageDigest.getInstance("MD5");
    MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
    byte[] bytes = new byte[0];
    String[] shortText;
    String[] text;
    String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    ArrayList<String> hashes = new ArrayList<>();


    public PassGenerator() throws NoSuchAlgorithmException {
    }

    public void generator(){

    }
    public void textSetter() throws IOException {
        String text = Files.readString(Path.of("C:\\Users\\Matve\\Desktop\\Secutiry\\Lab4.0\\src\\top-1000000.txt"));
        String shortText = Files.readString(Path.of("C:\\Users\\Matve\\Desktop\\Secutiry\\Lab4.0\\src\\top100.txt"));
        this.text = text.split("\n");
        this.shortText = shortText.split("\n");
    }

    public void generate(boolean flag){
        String password = null;
        Random random = new Random();
        for (int i =0; i<100000; i++) {
            int rand = random.nextInt(100);
            if (rand <= 10)
            {
                password = topHundred();
            }
            if (rand <= 90 && rand >=10)
            {
                password = topMillion();
            }
            if (rand >= 90)
            {
                password = reallyRandom();
            }
            if (flag = true) {
                md.update(password.getBytes());
                bytes = md.digest();
                String hash = new BigInteger(1, bytes).toString(16);
                while (hash.length() < 32) {
                    hash = "0" + hash;
                }
                password = hash;
                hashes.add(password);
                System.out.println(password);
            }
            else {
                sha1.update(password.getBytes());
                bytes = sha1.digest();
                String hash = new BigInteger(1, bytes).toString(16);
                while (hash.length() < 40) {
                    hash = "0" + hash;
                }
                password = hash;
                hashes.add(password);
                System.out.println(password);
            }
        }
    }

    public String topHundred(){
        Random random = new Random();
        String word = shortText[random.nextInt(shortText.length)];
        return  word;
    }
    public String topMillion(){
        Random random = new Random();
        String word = text[random.nextInt(text.length)];
        return word;
    }
    public String reallyRandom(){
        Random random = new Random();
        String word = "";
        for (int i =0; i< random.nextInt(12); i++) {
            int rand = random.nextInt(10);
            if (rand <= 7) {
                word += alphabet.charAt(random.nextInt(alphabet.length()));
            }
            else {
                word += random.nextInt(9);
            }
        }
        word += random.nextInt(1000);
        return word;

    }

    public void csvFileAdder () throws IOException {
        FileWriter writer= new FileWriter("C:\\Users\\Matve\\Desktop\\Secutiry\\Lab4.0\\src\\passwords.csv");
        for (int i = 0; i< hashes.size(); i++)
        {
            writer.write(hashes.get(i)+"\n");
        }

    }
    public void output(){
        for(int i =0; i< text.length; i++) {
            System.out.println(text[i]);
        }
    }

}
