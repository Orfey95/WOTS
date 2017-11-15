import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import Config.*;
import WOTS_CR.*;
import WOTS_PLUS.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        /*MD5Binary md5b = new MD5Binary();

        Scanner inMessage = new Scanner(System.in);
        System.out.printf("Input your message:\n");
        String MessageOrigin = inMessage.nextLine();

        Scanner inW = new Scanner(System.in);
        System.out.printf("Input parametr w:\n");
        int w = Integer.parseInt(inW.nextLine());

        String Message = "";
        int s = 0;
        Message = md5b.md5Custom(MessageOrigin);
        s = Message.length();
        KeyPairGeneration kpg = new KeyPairGeneration();
        SignatureGeneration sg = new SignatureGeneration();
        Binarylog bl = new Binarylog();
        SignatureVerification sv = new SignatureVerification();
        kpg.generatePairKey(s, w);
        System.out.println("X - Private key; Y - Public key; Y` - Validation string.\n");
        System.out.println("X : " + kpg.X + "\nY : " + kpg.Y);
        sg.generateSignature(Message, s, w);
        System.out.println("Signature: " + sg.SIGNATURE);

        boolean equalSignature = false;
        if(sv.verifySignature(sg.SIGNATURE, Message, s,w)) {
            equalSignature = true;
        }

        System.out.println("Y`: " + sv.sig);

        if(equalSignature == true){
            System.out.println("\nThe signature is valid!");
        }
        else {
            System.out.println("The signature is NOT valid!");
        }*/
        int s = 128;
        int w = 3;
        MD5Binary md5b = new MD5Binary();
        String Message = md5b.md5Custom("aaa");
        P_KeyPairGeneration p_kpg = new P_KeyPairGeneration();
        P_SignatureGeneration p_sg = new P_SignatureGeneration();
        P_SignatureVerification p_sv = new P_SignatureVerification();
        p_kpg.calculateSK(s,w);
        p_kpg.calculatePK(s,w);
        p_sg.generateSignature(Message, s, w);
        p_sv.verifySignature(p_sg.SIGNATURE, Message, s, w);
        System.out.println("l: " + String.valueOf(P_KeyPairGeneration.l));
        System.out.println("X: " + P_KeyPairGeneration.X);
        System.out.println("Y: " + P_KeyPairGeneration.Y);
        System.out.println("r: " + P_KeyPairGeneration.r);
        System.out.println("SIGNATURE: " + p_sg.SIGNATURE);
        System.out.println("sig: " + p_sv.sig);
    }
}
