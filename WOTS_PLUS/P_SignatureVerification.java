package WOTS_PLUS;
import Config.*;

public class P_SignatureVerification {
    MD5Binary md5B = new MD5Binary();
    MD5HEX md5H = new MD5HEX();
    P_SignatureGeneration sigGen = new P_SignatureGeneration();
    public String sig = "";

    public boolean verifySignature(String SIGNATURE, String Message, Integer s, Integer w) {
        Integer[]b = sigGen.messagePlusCheckSum(Message, s, w);
        //System.out.println(Arrays.asList(b));
        String SIGNATUREi = "";

        for (int i = 0; i < P_KeyPairGeneration.l; i++) {
            // I = (b[i]+1) % (w - 1);
            SIGNATUREi = SIGNATURE.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            sig += calculateSignatureI(SIGNATUREi,b[i],s,w);
        }
        sig = md5H.md5Custom(sig);
        if(P_KeyPairGeneration.Y.compareTo(sig) == 0)
            return true;
        else
            return false;
    }
    private String calculateSignatureI(String sigi, Integer bi, Integer s, Integer w) {
        String ri = "";
        int I = bi;
        for (int i = 0; i < w - 1 - bi; i++) {
            ri = P_KeyPairGeneration.r.substring(I * s, I * s + s); // нахождение подстроки с длиной в s символ
            sigi = md5B.md5Custom(xor(sigi, ri, s));
            I++;
        }
        return sigi;
    }

    private String xor(String sigi, String ri, Integer s) {
        String newsigi = "";
        for (int i = 0; i < s; i++) {
            newsigi += sigi.charAt(i) ^ ri.charAt(i);
        }
        return newsigi;
    }

}
