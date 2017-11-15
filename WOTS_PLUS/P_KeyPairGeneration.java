package WOTS_PLUS;
import Config.*;

public class P_KeyPairGeneration {
    Binarylog bl = new Binarylog();
    PRG prg = new PRG();
    MD5Binary md5B = new MD5Binary();
    MD5HEX md5H = new MD5HEX();
    public static int l;
    public static int l1;
    public static int l2;
    public static String X = "";
    public static String Y = "";

    public void calculateLengths(Integer s, Integer w){
        l1 = (int)Math.ceil(new Double(s) / (int)Math.ceil(bl.binlog((double) w)));
        l2 = (int)Math.ceil((new Double((int)Math.ceil(bl.binlog((double) (l1 * (w - 1))))) / new Double((int)Math.ceil(bl.binlog((double) w)))) + 1);
        l = l1 + l2;
    }

    public void generatePairKey(Integer s, Integer w) {
        calculateLengths(s, w);
        String Xi = "";
        String Yi = "";

        for(int i = 1; i <= l; i++){
            Xi = prg.Random128();
            X += Xi;
            Yi = calculateYi(Xi, Yi, w);
            Y += Yi;
        }
        Y = md5H.md5Custom(Y);
    }

    private String calculateYi(String Xi, String Yi, Integer w){
        Yi = Xi;
        for(int i = 1; i <= Math.pow(2, w) - 1; i++){
            Yi = md5B.md5Custom(Yi);
        }
        return Yi;
    }
}