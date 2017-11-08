import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class KeyPairGeneration {
    Binarylog bl = new Binarylog();
    PRG random = new PRG();

    int s = 128; //hash-size
    int w = 2;
    int t;

    public void calculateLengths(){
        int t1 = (int) Math.ceil(s + w);
        int t2 = (bl.binlog(t1) + 1 + w)/w;
        t = t1 + t2;
    }


    public String generatePrivateKey() {
        PRG prg = new PRG();
        calculateLengths();
        String X = "";
        for(int i=0;i<t;i++){
            X += prg.Random128();
        }
        return X;
    }

}
