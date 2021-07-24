package cursojava.adapters;

import cursojava.entity.Alimento;

import java.util.ArrayList;
import java.util.List;

public class AlimentoAdapter {

    private Alimento alimento;

    public AlimentoAdapter(Alimento alimento){
        this.alimento = alimento;
    }

    public String getMacronutrientes(){
        String mayoritario="No Existe";
        if (this.alimento.getMacroNutrienteMayoritario()!=null) mayoritario= this.alimento.getMacroNutrienteMayoritario().name();
        String minoritario = "No Existe";
        if(this.alimento.getMacroNutrienteMinoritario()!=null)minoritario=this.alimento.getMacroNutrienteMinoritario().name();
        return mayoritario+" - "+minoritario;
    }

    public static List<AlimentoAdapter> obtenerPorLista(List<Alimento> alimentos){
        List<AlimentoAdapter> adaptadores = new ArrayList<AlimentoAdapter>();
        for(Alimento a:alimentos){
            AlimentoAdapter adaptador = new AlimentoAdapter(a);
            adaptadores.add(adaptador);
        }
        return adaptadores;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }
}
