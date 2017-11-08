import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
       KeyPairGeneration gen = new KeyPairGeneration();
        gen.generatePairKey();
        System.out.println("X: " + gen.X + "\n Y: " + gen.Y);
    }
}
