import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MD5Binary md5b = new MD5Binary();

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
        }
    }
}
