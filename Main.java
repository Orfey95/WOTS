import java.security.NoSuchAlgorithmException;

public class Main {

    static String Message = "11010101100011100011010110000010101011111010100110010000010000001110001001111011100100101011000100111100100011110010001010000000";
    static int s = Message.length();
    static int w = 3;
    public static void main(String[] args) throws NoSuchAlgorithmException {
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
