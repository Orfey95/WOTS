import java.util.Arrays;

public class SignatureGeneration {

    private int C; // CheckSum
    private String CBinary; // CheckSum binary

    public String addZeros(String Message, Integer s, Integer w){
        int temp = s % w; // Проверка на кратность s к w
        String tempS = ""; //Строка для добавочных нулей
        if(temp > 0){
            for(int i = 0; i < w - temp; i++){
                tempS += "0";
            }
            Message = tempS + Message; // Подстановка нулей в начало сообщения
        }
        return Message;
    }

    public String [] messageSeparate(String Message, Integer s, Integer w){
        Message = addZeros(Message,  s,  w);
        String [] blocksOfMessage = new String[Message.length()/w];
        int k = 0;  //счетчик для индексов  массива
        for(int i = 0; i < Message.length(); i = i + w) { // проход по массиву через каждые w символа для нахождения новой подстроки
            String S1 = Message.substring(i, i + w); // нахождение подстроки с длиной в w символа
            blocksOfMessage[k++] = String.valueOf(Integer.parseInt(S1, 2)); // присваивание подстроки к элементу массива
            C += ((int) Math.pow(2, w)) - Integer.parseInt(S1, 2); // Подсчет CheckSum
        }
        CBinary = Integer.toBinaryString(C); //Перевод CheckSum в 2-ю сс
        System.out.println("CheckSum = " + C + "(10)");
        System.out.println("CheckSum = " + CBinary + "(2)");
        return blocksOfMessage;
    }
}
