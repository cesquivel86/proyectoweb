package threads;

public class EjemploThreadConExtends extends Thread{

    public void run () {
        for (int i=0;i<10 ;i++ ) {
            System.out.println("Holaaaaa desde el Thread)");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
