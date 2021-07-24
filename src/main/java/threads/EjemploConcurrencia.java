package threads;

public class EjemploConcurrencia extends Thread{
    public static int cantidad=0;

    public void run(){
        cantidad++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
