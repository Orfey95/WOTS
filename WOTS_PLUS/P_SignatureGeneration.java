package WOTS_PLUS;
import Config.*;

public class P_SignatureGeneration {

    Binarylog bl = new Binarylog();
    P_KeyPairGeneration p_kp = new P_KeyPairGeneration();
    MD5Binary md5B = new MD5Binary();
    String CBinary = ""; // CheckSum binary

    public String messageAddZeros(String Message, Integer s, Integer w){
        int temp = s % w; // �������� �� ��������� s � w
        String tempS = ""; //������ ��� ���������� �����
        if(temp > 0){
            for(int i = 0; i < w - temp; i++){
                tempS += "0";
            }
            Message = tempS + Message; // ����������� ����� � ������ ���������
        }
        return Message;
    }

    public Integer [] messageSeparate(String Message, Integer s, Integer w){
        int CDecimal = 0;

        Message = messageAddZeros(Message, s, w);
        Integer [] blocksOfMessage = new Integer[(int)Math.ceil(Message.length() / w)];
        int k = 0;  //������� ��� �������� �������
        for(int i = 0; i < Message.length(); i = i + w) { // ������ �� ������� ����� ������ w ������� ��� ���������� ����� ���������
            String S1 = Message.substring(i, i + w); // ���������� ��������� � ������ � w �������
            blocksOfMessage[k++] = Integer.parseInt(S1, 2); // ������������ ��������� � �������� �������
            CDecimal += ((int) Math.pow(2, w)) - Integer.parseInt(S1, 2); // ������� CheckSum
        }
        CBinary = Integer.toBinaryString(CDecimal); //������� CheckSum � 2-� ��
        //System.out.println("CheckSum = " + CDecimal + "(10)");
        //System.out.println("CheckSum = " + CBinary + "(2)");
        return blocksOfMessage;
    }

    public String checkSumAddZeros(String CBinary, Integer s, Integer w){
        int tCheckSum = P_KeyPairGeneration.l2;
        int temp = (tCheckSum * w) - CBinary.length(); // �������� �� ��������� s � w
        String tempS = ""; //������ ��� ���������� �����
        if(temp > 0){
            for(int i = 0; i < temp; i++){
                tempS += "0";
            }
            CBinary = tempS + CBinary; // ����������� ����� � ������ CheckSum
        }
        return CBinary;
    }

    public Integer [] checkSumSeparate(Integer s, Integer w){
        CBinary = checkSumAddZeros(CBinary, s, w);
        int tCheckSum = P_KeyPairGeneration.l2;
        Integer [] blocksOfCheckSum = new Integer[tCheckSum];
        int k = 0;  //������� ��� �������� �������
        for(int i = 0; i < CBinary.length(); i = i + w) { // ������ �� ������� ����� ������ w ������� ��� ���������� ����� ���������
            String S1 = CBinary.substring(i, i + w); // ���������� ��������� � ������ � w �������
            blocksOfCheckSum[k++] = Integer.parseInt(S1, 2); // ������������ ��������� � �������� �������
        }
        return blocksOfCheckSum;
    }

    public Integer [] messagePlusCheckSum(String Message, Integer s, Integer w){ // ���������� blocksOfMessage � blocksOfCheckSum
        Integer [] messageArray = messageSeparate(Message, s, w);
        Integer [] checkSumArray = checkSumSeparate(s, w);
        Integer [] fullArray = new Integer[P_KeyPairGeneration.l];
        int temp = (int)Math.ceil(messageAddZeros(Message, s, w).length() / w);
        for(int i = 0; i < temp; i++){
            fullArray[i] = messageArray[i];
        }
        int k = 0;  //������� ��� �������� �������
        for (int j = temp; j < P_KeyPairGeneration.l; j++) {
            fullArray[j] = checkSumArray[k];
            k++;
        }
        return fullArray;
    }

    public String SIGNATURE = "";

    public void generateSignature(String Message, Integer s, Integer w) {
        Integer[]b = messagePlusCheckSum(Message, s, w);
        //System.out.println("Full Array = " + Arrays.toString(messagePlusCheckSum(Message, s, w)));
        String Xi = "";
        //System.out.println("blen = " + b.length);
        for (int i = 0; i < P_KeyPairGeneration.l; i++) {
            Xi = P_KeyPairGeneration.X.substring(i * s, i * s + s); // ���������� ��������� � ������ � s ������
            SIGNATURE += calculateSignatureI(Xi, b[i]);
        }
    }

    private String calculateSignatureI(String Xi, Integer bi) {
        String sigi = Xi;
        // System.out.println("bi" + bi);
        for (int i = 1; i <= bi; i++) {
            sigi = md5B.md5Custom(sigi);
        }
        return sigi;
    }
}
