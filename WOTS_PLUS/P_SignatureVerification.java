package WOTS_PLUS;
import Config.*;

public class P_SignatureVerification {
    MD5Binary md5B = new MD5Binary();
    MD5HEX md5H = new MD5HEX();
    P_SignatureGeneration sigGen = new P_SignatureGeneration();
    public String sig = "";

    public boolean verifySignature(String SIGNATURE, String Message, Integer s, Integer w) {
        Integer[]b = sigGen.messagePlusCheckSum(Message, s, w);
        //System.out.println("Full Array = " + Arrays.toString(sigGen.messagePlusCheckSum(Message, s, w)));
        String SIGNATUREi = "";
        for (int i = 0; i < P_KeyPairGeneration.l; i++) {
            SIGNATUREi = SIGNATURE.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            sig += calculateSignatureI(SIGNATUREi,b[i],w);
        }
        sig = md5H.md5Custom(sig);
        if(P_KeyPairGeneration.Y.compareTo(sig) == 0)
            return true;
        else
            return false;
    }
    private String calculateSignatureI(String sigi, Integer bi, Integer w) {
        for (int i = 1; i <= Math.pow(2, w) - 1 - bi; i++) {
            sigi = md5B.md5Custom(sigi);
        }
        return sigi;
    }
}