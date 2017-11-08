import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class KeyPairGeneration {
    Binarylog bl = new Binarylog();
    PRG prg = new PRG();
    MD5 md5 = new MD5();
    int s = 128; //hash-size
    int w = 2;
    int t;
    String X = "";
    String Y = "";

    public void calculateLengths(){
        int t1 = (int) Math.ceil(s + w);
        int t2 = (bl.binlog(t1) + 1 + w)/w;
        t = t1 + t2;
    }


    public void generatePairKey() {
        calculateLengths();
        String Xi = "";
        String Yi = "";

        for(int i=0;i<t;i++){
            Xi = prg.Random128();
            X += Xi;
            Yi = CalculateYi(Xi,Yi);
            Y += Yi;
        }
    }

    private String CalculateYi(String Xi, String Yi){
        Yi = Xi;
        for(int i=1; i<Math.pow(2,w)-1;i++){
            Yi = md5.md5Custom(Yi);
        }
        return Yi;
    }

}
