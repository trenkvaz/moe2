package Mypack2;

/**
 * Created by Tren on 13.06.2017.
 */
class Balan {
    String name;
    double bal;
    public Balan(String n, double b) {
        name = n;
        bal = b;
    }
    public void show() {
        if (bal<0)
            System.out.print("-->");
        System.out.println(name+ ": $" + bal);

    }
}

class Acc {
    public static void main(String[] args) {
        Balan cur[] = new Balan[3];
        cur[0] = new Balan("Fild", 123.23);
        cur[1] = new Balan("Tell", 157.02);
        cur[2] = new Balan("Tom", -12.33);
        for (int i = 0; i < 3; i++) cur[i].show();
    }
}