/**
 * Created by Tren on 15.06.2017.
 */
class Gsch {

    static int a = 45;
    static int c = 21;
    static int m = 67;
    static int seed = 2;

     static int get() {
        seed = (a * seed + c) % (m*seed+c);
        return seed;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++)
            System.out.println(get ()+" ");
    }
}





