import java.util.Arrays;
public class SignatureVerification {
    MD5 md5 = new MD5();
    SignatureGeneration sigGen = new SignatureGeneration();
    String sig = "";

    public boolean verifySignature(String SIGNATURE, String Message, Integer s, Integer w) {
        Integer[]b = sigGen.messagePlusCheckSum(Message, s, w);
        System.out.println("Full Array = " + Arrays.toString(sigGen.messagePlusCheckSum(Message, s, w)));
        String SIGNATUREi = "";
        for (int i = 0; i < KeyPairGeneration.t; i++) {
            SIGNATUREi = SIGNATURE.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
            sig += calculateSignatureI(SIGNATUREi,b[i],w);
        }
        if(KeyPairGeneration.Y.compareTo(sig) == 0)
            return true;
        else
            return false;
    }
    private String calculateSignatureI(String sigi, Integer bi, Integer w) {
        for (int i = 1; i <= Math.pow(2, w) - 1 - bi; i++) {
            sigi = md5.md5Custom(sigi);
        }
        return sigi;
    }


}
