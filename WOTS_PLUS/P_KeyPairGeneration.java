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
    public static String r = "";

    private void calculateLengths(Integer s, Integer w){
        l1 = (int)Math.ceil(new Double(s) / (int)Math.ceil(bl.binlog((double) w)));
        l2 = (int)Math.ceil((new Double((int)Math.ceil(bl.binlog((double) (l1 * (w - 1))))) / new Double((int)Math.ceil(bl.binlog((double) w)))) + 1);
        l = l1 + l2;
    }

    public void calculateSK(Integer s, Integer w) {
        calculateLengths(s, w);
        String Xi = "";
        String ri = "";
        for (int i = 1; i <= l + w - 1; i++) {
            if (i <= l) {
                Xi = prg.Random128();
                X += Xi;
            } else {
                ri = prg.Random128();
                r += ri;
            }
        }
    }

    public void calculatePK(Integer s, Integer w) {
        String Xi = "";
        for (int i = 0; i < l; i++) {
            Xi = X.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            Y += calculateYi(Xi, s, w);
        }
        Y = md5H.md5Custom(Y);
    }

    private String calculateYi(String Xi, Integer s, Integer w) {
        String Yi = "";
        String ri = "";
        Yi = Xi;
        for (int i = 0; i < w - 1; i++) {
            ri = r.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            Yi = md5B.md5Custom(xor(Yi, ri, s));
        }
        return Yi;
    }

    private String xor(String Yi, String ri, Integer s) {
        String newYi = "";
        for (int i = 0; i < s; i++) {
            newYi += Yi.charAt(i) ^ ri.charAt(i);
        }
        return newYi;
    }
}
