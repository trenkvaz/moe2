/**
 * Created by Tren on 13.06.2017.
 */
interface Call{
    void call(int param);        // объявление интерфейса Call с методом call со своей переменной
}

class InterFeis implements Call {   // класс Интерфейс применяет интерфейс Call
    public void call(int p) {                 // метод интерфейса, но вставлена своя другая переменная, нужно всегда указывать доступ паблик !
        System.out.println("метод call, вызываемый со значением "+p);    //
    }
    void svoymetod(int k){                                // в классе кроме метода интерфейса можно объявлять и свои методы
        System.out.println("в классах, реализующих интерфейсы могут определяться и другие методы  " +k);
    }
}
class Interfeis2 implements Call{       // класс Интерфейс2 применяет интерфейс Call
    public void call(int p) {            // тот же интерфейс с другим методом
        System.out.println("Еще один вариант метода call()");
        System.out.println("p вквадрает равно " +(p*p));
    }
}
class Testintface {
    public static void main(String[] args) {
        Call c = new InterFeis();               // объектом класс Интерфейс выступает ссылка С на интерфейс
        InterFeis b = new InterFeis();  // объект Б класс Интерфейс
        Interfeis2 n = new Interfeis2();    // объект Н класса Интерфейс2
        b.svoymetod(20);                     // объект Б реализует свой метод в своем классе
        c.call(42);                     // ссылка С включает метод интерфейса call
        c = n;                             //ссылка интерфейса С начинает ссылать на объект Н
        c.call(2);                  // объект Н через ссылку С включает интерфейс call
} }