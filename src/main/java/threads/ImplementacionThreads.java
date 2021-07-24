package threads;

public class ImplementacionThreads {
    public static void main(String args[]){


        EjemploConcurrencia concurrencia = new EjemploConcurrencia();
        concurrencia.start();

        while(concurrencia.isAlive()){
            System.out.println("esperando");
        }
        System.out.println(EjemploConcurrencia.cantidad);

        EjemploConcurrencia.cantidad++;
        System.out.println(EjemploConcurrencia.cantidad);


        //hilo2.run();
    }
}
