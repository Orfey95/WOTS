import java.util.Arrays;

public class SignatureGeneration {

    private int C; // CheckSum
    private String CBinary; // CheckSum binary

    public String [] messageSeparate(String Message, Integer s, Integer w){
        int temp = s % w; // �������� �� ��������� s � w
        String tempS = ""; //������ ��� ���������� �����
        if(temp > 0){
            for(int i = 0; i < w - temp; i++){
                tempS += "0";
            }
            Message = tempS + Message; // ����������� ����� � ������ ���������
        }
        String [] blocksOfMessage = new String[Message.length()/w];
        int k = 0;  //������� ��� ��������  �������
        for(int i = 0; i < Message.length(); i = i + w) { // ������ �� ������� ����� ������ w ������� ��� ���������� ����� ���������
            String S1 = Message.substring(i, i + w); // ���������� ��������� � ������ � w �������
            blocksOfMessage[k++] = String.valueOf(Integer.parseInt(S1, 2)); // ������������ ��������� � �������� �������
            C += ((int) Math.pow(2, w)) - Integer.parseInt(S1, 2); // ������� CheckSum
        }
        CBinary = Integer.toBinaryString(C); //������� CheckSum � 2-� ��
        System.out.println("CheckSum = " + C + "(10)");
        System.out.println("CheckSum = " + CBinary + "(2)");
        return blocksOfMessage;
    }
}
