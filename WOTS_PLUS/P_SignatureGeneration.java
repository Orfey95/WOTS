package WOTS_PLUS;
import Config.*;

public class P_SignatureGeneration {

    Binarylog bl = new Binarylog();
    P_KeyPairGeneration p_kp = new P_KeyPairGeneration();
    MD5Binary md5B = new MD5Binary();
    public String CBinary = ""; // CheckSum binary

    public String messageAddZeros(String Message, Integer s, Integer w){
        int temp = p_kp.l1 * (int)Math.ceil(bl.binlog((double) w)); // Проверка на кратность s к w
        temp = temp - s;
        String tempS = ""; //Строка для добавочных нулей
        if(temp > 0){
            for(int i = 0; i < temp; i++){
                tempS += "0";
            }
            Message = tempS + Message; // Подстановка нулей в начало сообщения
        }
        return Message;
    }

    public Integer [] messageSeparate(String Message, Integer s, Integer w){
        int CDecimal = 0;

        Message = messageAddZeros(Message, s, w);
        Integer [] blocksOfMessage = new Integer[p_kp.l1];
        int k = 0;  //счетчик для индексов массива
        for(int i = 0; i < Message.length(); i = i + (int)Math.ceil(bl.binlog((double) w))) { // проход по массиву через каждые w символа для нахождения новой подстроки
            String S1 = Message.substring(i, i + (int)Math.ceil(bl.binlog((double) w))); // нахождение подстроки с длиной в w символа
            blocksOfMessage[k++] = Integer.parseInt(S1, 2); // присваивание подстроки к элементу массива
            CDecimal += (w - 1 - (Integer.parseInt(S1, 2) % w)); // Подсчет CheckSum
        }
        CBinary = Integer.toBinaryString(CDecimal); //Перевод CheckSum в 2-ю сс
        //System.out.println("CheckSum = " + CDecimal + "(10)");
        //System.out.println("CheckSum = " + CBinary + "(2)");
        return blocksOfMessage;
    }

    public String checkSumAddZeros(String CBinary, Integer s, Integer w){
        int tCheckSum = P_KeyPairGeneration.l2;
        int temp = (tCheckSum * (int)Math.ceil(bl.binlog((double) w))) - CBinary.length(); // Проверка на кратность s к w
        String tempS = ""; //Строка для добавочных нулей
        if(temp > 0){
            for(int i = 0; i < temp; i++){
                tempS += "0";
            }
            CBinary = tempS + CBinary; // Подстановка нулей в начало CheckSum
        }
        return CBinary;
    }

    public Integer [] checkSumSeparate(Integer s, Integer w){
        CBinary = checkSumAddZeros(CBinary, s, w);
        int tCheckSum = P_KeyPairGeneration.l2;
        Integer [] blocksOfCheckSum = new Integer[tCheckSum];
        int k = 0;  //счетчик для индексов массива
        for(int i = 0; i < CBinary.length(); i = i + (int)Math.ceil(bl.binlog((double) w))) { // проход по массиву через каждые w символа для нахождения новой подстроки
            String S1 = CBinary.substring(i, i + (int)Math.ceil(bl.binlog((double) w))); // нахождение подстроки с длиной в w символа
            blocksOfCheckSum[k++] = Integer.parseInt(S1, 2); // присваивание подстроки к элементу массива
        }
        return blocksOfCheckSum;
    }

    public Integer [] messagePlusCheckSum(String Message, Integer s, Integer w){ // Соединение blocksOfMessage и blocksOfCheckSum
        Integer [] messageArray = messageSeparate(Message, s, w);
        Integer [] checkSumArray = checkSumSeparate(s, w);
        Integer [] fullArray = new Integer[P_KeyPairGeneration.l];
        for(int i = 0; i < p_kp.l1; i++){
            fullArray[i] = messageArray[i];
        }
        int k = 0;  //счетчик для индексов массива
        for (int j = p_kp.l1; j < p_kp.l; j++) {
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
            Xi = P_KeyPairGeneration.X.substring(i * s, i * s + s); // нахождение подстроки с длиной в s символ
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
