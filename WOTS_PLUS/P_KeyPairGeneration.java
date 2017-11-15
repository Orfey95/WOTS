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
        l1 = (int)Math.ceil(new Double(s) / new Double(w));
        l2 = (int)Math.ceil((double)((int)Math.ceil(bl.binlog((double) l1)) + 1 + w)/(double)w);
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
        String Yi = "";
        String ri = "";
        int I = 0;
        for (int i = 0; i < l; i++) {
            I = i % (w - 1);
            Xi = X.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            ri = r.substring(I * s, I * s + s); // нахождение подстроки с длиной в s символ
            Y += xor(calculateYi(Xi, I, Yi, w), ri, s);
        }
    }

    private String calculateYi(String Xi, Integer I, String Yi, Integer w) {
        Yi = Xi;
        for (int i = 1; i <= I - 1; i++) {
            Yi = md5B.md5Custom(Yi);
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
