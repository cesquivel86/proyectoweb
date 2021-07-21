package cursojava.util;

public class  Gen<T>{
    //T es el parámetro de tipo genérico.
    T ob; //Declara un objeto de tipo T

    //Pase al constructor una referencia a un objeto de tipo T.
    Gen(T o){
        ob=o;
    }

    T getOb(){
        return ob;
    }

    //Muestra el tipo de T
    void mostrarTipo(){
        System.out.println("El tipo de T es: "+ob.getClass().getName());
    }
}