import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MD5 md5 = new MD5();
        PRG random = new PRG();
        KeyPairGeneration kpg = new KeyPairGeneration();
        String projectName = "WOTS";
        //System.out.println(md5.md5Custom(projectName));
        System.out.println(random.Random128());
    }
}
