package cursojava.util;

public class Genericos {
    public static void main(String[] args) {

        //Crea una referencia Gen para Integers.
        Gen<Integer> iOb;

        //Cree un objeto Gen<Integer> y asigne su referencia a iOb.
        //Observe el uso de autoboxing para encapsular el valor 28 dentro de un objeto Integer.
        iOb=new Gen<Integer>(28);

        //Muestra el tipo de dato utilizado por iOb
        iOb.mostrarTipo();

        //Obtenga el valor en iOb.
        //Fíjese que no se necesita una conversión

        int v=  iOb.getOb();
        System.out.println("Valor: "+v);
        System.out.println();

        //Cree un objeto Gen para Strings.
        Gen<String> strOb=new Gen<String>("Prueba de genéricos");

        //Muestra el tipo de dato utilizado por strOb
        strOb.mostrarTipo();

        //Obtenga el valor de strOb.
        // De nuevo, note que no se necesita de conversión
        String str=strOb.getOb();
        System.out.println("Valor: "+str);
    }
}
