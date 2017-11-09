import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {

    static String Message = "11010101100011100011010110000010101011111010100110010000010000001110001001111011100100101011000100111100100011110010001010000000";
    static int s = Message.length();
    static int w = 3;
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGeneration gen = new KeyPairGeneration();
        SignatureGeneration sg = new SignatureGeneration();
        Binarylog bl = new Binarylog();
        MD5 md5 = new MD5();
        gen.generatePairKey(s, w);
        //System.out.println("t: " + gen.t);
        System.out.println("X: " + gen.X + "\nY: " + gen.Y);
        System.out.println("Full Array = " + Arrays.toString(sg.messagePlusCheckSum(Message, s, w)));
    }

}
