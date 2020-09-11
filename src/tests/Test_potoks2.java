package tests;

import java.util.concurrent.locks.ReentrantLock;

//import static tests.Test_potoks2.getosr;

public class Test_potoks2 {
    public static Osr[] osrs = new Osr[]{new Osr("n1"),new Osr("n2"),new Osr("n3"),new Osr("n4")};

   public String getosr(String potok){
       String result = null;
        while (true){
        for(Osr osr:osrs){
            result = osr.getName(potok);

            if(result!=null)return result;
            //else System.out.println("result");
        }
            /*try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<6; i++)new Potoks("potok "+i);
    }
}


class Osr {
    String name;
    ReentrantLock lock = new ReentrantLock();
    Osr(String name1){
      name =name1;
    }

    boolean islock(){
        return lock.isLocked();
    }

    String getName(String potok){
        //if()
        if(!lock.tryLock())return null;
        try {
            Thread.sleep((long) ((Math.random() * (100 - 50)) + 50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            return name+" "+potok;
        } finally {
            lock.unlock();
        }
    }
}

class Potoks implements Runnable{
    String name;
    Test_potoks2 test_potoks2 = new Test_potoks2();
    Potoks(String name1){
        name =name1;
        new Thread(this).start();
    }
    @Override
    public void run() {

        for(int i=0; i<100; i++){
            try {
                Thread.sleep((long) ((Math.random() * (30 - 10)) + 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"    "+ test_potoks2.getosr(name));
        }
    }
}