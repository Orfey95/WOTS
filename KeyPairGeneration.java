public class KeyPairGeneration {
    Binarylog bl = new Binarylog();
    PRG prg = new PRG();
    MD5 md5 = new MD5();
    static int t;
    static int t1;
    static int t2;
    String X = "";
    String Y = "";

    public void calculateLengths(Integer s, Integer w){
        t1 = (int)Math.ceil(new Double(s) / new Double(w));
        t2 = (int)Math.ceil((double)((int)Math.ceil(bl.binlog((double) t1)) + 1 + w)/(double)w);
        t = t1 + t2;
    }


    public void generatePairKey(Integer s, Integer w) {
        calculateLengths(s, w);
        String Xi = "";
        String Yi = "";

        for(int i = 1; i <= t; i++){
            Xi = prg.Random128();
            X += Xi;
            Yi = calculateYi(Xi, Yi, w);
            Y += Yi;
        }
    }

    private String calculateYi(String Xi, String Yi, Integer w){
        Yi = Xi;
        for(int i = 1; i <= Math.pow(2, w) - 1; i++){
            Yi = md5.md5Custom(Yi);
        }
        return Yi;
    }
}
