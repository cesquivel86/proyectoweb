package threads;

import cursojava.entity.Alimento;

public class EjemploThreadConImplements extends Alimento implements Runnable{
    @Override
    public void run () {
        for (int i=0;i<10 ;i++ ) {
            System.out.println("hola soy" + this.getNombre());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
