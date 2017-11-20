import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import Config.*;
import WOTS_CR.*;
import WOTS_PLUS.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MD5Binary md5b = new MD5Binary();

        Scanner inMessage = new Scanner(System.in);
        System.out.printf("Input your message:\n");
        String MessageOrigin = inMessage.nextLine();

        Scanner inW = new Scanner(System.in);
        System.out.printf("Input parametr w:\n");
        int w = Integer.parseInt(inW.nextLine());

        Scanner inChoose = new Scanner(System.in);
        System.out.printf("Choose algoritm (1 - WOTS, 2 - WOTS+):\n");
        int chooseAlgoritm = Integer.parseInt(inW.nextLine());

        String Message = "";
        int s = 0;
        Message = md5b.md5Custom(MessageOrigin);
        s = Message.length();
        Binarylog bl = new Binarylog();
        if(chooseAlgoritm > 2){
            System.out.println("Wrong input!");
        }
        if(chooseAlgoritm == 1) {
            KeyPairGeneration kpg = new KeyPairGeneration();
            SignatureGeneration sg = new SignatureGeneration();

            SignatureVerification sv = new SignatureVerification();
            long start = System.currentTimeMillis(); // STAR TIMER
            kpg.generatePairKey(s, w);
            System.out.println("X - Private key; Y - Public key; Y` - Validation string.\n");
            System.out.println("X : " + kpg.X + "\nY : " + kpg.Y);
            sg.generateSignature(Message, s, w);
            System.out.println("Signature: " + sg.SIGNATURE);

            boolean equalSignature = false;
            if (sv.verifySignature(sg.SIGNATURE, Message, s, w)) {
                equalSignature = true;
            }
            long finish = System.currentTimeMillis(); // FINISH TIMER
            System.out.println("Y`: " + sv.sig);
            long timeConsumedMillis = finish - start;
            //Statistic
            System.out.println("\n" + "Statictic:");
            System.out.println("Length of Private key: " + KeyPairGeneration.X.length() + " bits");
            System.out.println("Length of Signature: " + sg.SIGNATURE.length() + " bits");
            System.out.println("Time: " + timeConsumedMillis + "ms");
            //Statistic

            if (equalSignature == true) {
                System.out.println("\nThe signature is valid!");
            } else {
                System.out.println("The signature is NOT valid!");
            }
        }
        if(chooseAlgoritm == 2) {
        /*String Message = "00110000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001010";
        int s = Message.length();
        int w = 3;*/

            P_KeyPairGeneration p_kpg = new P_KeyPairGeneration();
            P_SignatureGeneration p_sg = new P_SignatureGeneration();

        /*p_kpg.calculateSK(s,w);
        p_kpg.calculatePK(s, w);
        System.out.println("l: " + String.valueOf(P_KeyPairGeneration.l));
        System.out.println("X: " + P_KeyPairGeneration.X);
        System.out.println("Y: " + P_KeyPairGeneration.Y);
        System.out.println("r: " + P_KeyPairGeneration.r);*/
            p_kpg.calculateLengths(s, w);
            //System.out.println(p_sg.messageAddZeros(Message, s, w));
            //System.out.println(Arrays.asList(p_sg.messageSeparate(Message, s, w)));
            //System.out.println(p_sg.checkSumAddZeros(p_sg.CBinary, s, w));
            //System.out.println(Arrays.asList(p_sg.checkSumSeparate(s, w)));
            System.out.println(Arrays.asList(p_sg.messagePlusCheckSum(Message, s, w)));
        }
        Scanner inExit = new Scanner(System.in);
        System.out.printf("Exit or not? (0 - Exit, 1 - Start):\n");
        int exitOrNot = Integer.parseInt(inExit.nextLine());
        if(exitOrNot == 1){
            Reboot();
            main(args);
        }
    }

    public static void Reboot(){ // Reboot global parametrs
        SignatureGeneration sg = new SignatureGeneration();
        SignatureVerification sv = new SignatureVerification();
        KeyPairGeneration.t = 0;
        KeyPairGeneration.t1 = 0;
        KeyPairGeneration.t2 = 0;
        KeyPairGeneration.X = "";
        KeyPairGeneration.Y = "";
        sg.CBinary = "";
        sg.SIGNATURE = "";
        sv.sig = "";
        P_SignatureGeneration p_sg = new P_SignatureGeneration();
        P_SignatureVerification p_sv = new P_SignatureVerification();
        P_KeyPairGeneration.l = 0;
        P_KeyPairGeneration.l1 = 0;
        P_KeyPairGeneration.l2 = 0;
        P_KeyPairGeneration.X = "";
        P_KeyPairGeneration.Y = "";
        p_sg.CBinary = "";
        p_sg.SIGNATURE = "";
        p_sv.sig = "";
    }
}
