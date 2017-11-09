public class SignatureGeneration {

    Binarylog bl = new Binarylog();
    private int CDecimal; // CheckSum
    public String CBinary; // CheckSum binary

    public String messageAddZeros(String Message, Integer s, Integer w){
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

    public Integer [] messageSeparate(String Message, Integer s, Integer w){
        Message = messageAddZeros(Message, s, w);
        Integer [] blocksOfMessage = new Integer[Message.length()/w];
        int k = 0;  //счетчик для индексов  массива
        for(int i = 0; i < Message.length(); i = i + w) { // проход по массиву через каждые w символа для нахождения новой подстроки
            String S1 = Message.substring(i, i + w); // нахождение подстроки с длиной в w символа
            blocksOfMessage[k++] = Integer.parseInt(S1, 2); // присваивание подстроки к элементу массива
            CDecimal += ((int) Math.pow(2, w)) - Integer.parseInt(S1, 2); // Подсчет CheckSum
        }
        CBinary = Integer.toBinaryString(CDecimal); //Перевод CheckSum в 2-ю сс
        System.out.println("CheckSum = " + CDecimal + "(10)");
        System.out.println("CheckSum = " + CBinary + "(2)");
        return blocksOfMessage;
    }

    public String checkSumAddZeros(String CBinary, Integer s, Integer w){
        int tCheckSum = (bl.binlog(s/w) + 1 + w)/w;
        int temp = CBinary.length() % tCheckSum; // Проверка на кратность s к w
        String tempS = ""; //Строка для добавочных нулей
        if(temp > 0){
            for(int i = 0; i < tCheckSum - temp; i++){
                tempS += "0";
            }
            CBinary = tempS + CBinary; // Подстановка нулей в начало CheckSum
        }
        return CBinary;
    }

    public String [] checkSumSeparate(Integer s, Integer w){
        checkSumAddZeros(CBinary, s, w);
        int tCheckSum = (bl.binlog(s/w) + 1 + w)/w;
        String [] blocksOfCheckSum = new String[tCheckSum];

        return blocksOfCheckSum;
    }
}
