package tests;

import java.util.concurrent.*;

class Test_threads {

    int a;


   /* Test_threads (int n){
        a = n;
    }*/

  Callable sum(int n){
        a = n+1;
     return task;
    }

    Callable task = () -> a;
}

class Potok implements Runnable{
  static Manager manager;
  String name;

  Potok(String name1){
      manager = new Manager();
      name =name1;
      new Thread(this).start();
  }
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<10; i++){
            System.out.println(name+"    "+manager.get(i));
        }
    }
}

class Manager {
  static ExecutorService executor = Executors.newFixedThreadPool(4);
  static Test_threads test_threads = new Test_threads();

    String get(int a){
        String threadName = Thread.currentThread().getName();
        Future<Integer> future = executor.submit(test_threads.sum(a));
        Integer b = 0;
        try {
            b = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return threadName+" "+b;
    }

    public static void main(String[] args) {
        for(int i=0; i<6; i++)new Potok("potok "+i);
    }
}
