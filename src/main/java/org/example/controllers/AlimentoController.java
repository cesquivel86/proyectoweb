package org.example.controllers;

import org.example.entity.Alimento;
import org.example.services.AlimentoService;import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class AlimentoController extends SelectorComposer<Component> {
    @WireVariable
    AlimentoService alimentoService;

    @Wire
    Grid alimentosGrid;

    @Wire
    Textbox categoriaTb, nombreTb, nutrientesTb;

    @Wire
    Combobox categoriaCb;

    @Wire
    Footer footer;

    @Wire
    Div componentePadre;

    private int segundos=0;

    ArrayList<Alimento> todosLosAlimentos;

    public void doAfterCompose(Component comp) throws Exception {
        try{
            super.doAfterCompose(comp);
            alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");
            todosLosAlimentos = new ArrayList<Alimento>(alimentoService.obtenerTodos());
            ListModelList<Alimento> modelo = new ListModelList<Alimento>(todosLosAlimentos);
            alimentosGrid.setModel(modelo);
            System.out.println("bean cargado");

            List<String> categorias = alimentoService.obtenerCategorias(todosLosAlimentos);
            categoriaCb.appendItem("Todas");
            for(String categoria:categorias){
                categoriaCb.appendItem(categoria);
            }
        }catch(Exception e){

            e.printStackTrace();
        }
    }

    @Listen("onTimer=#segundero")
    public void agregarSegundo(){
        segundos++;
        footer.setLabel("esta vista lleva abierta "+segundos+" segundos");
        Textbox tb = new Textbox();
        tb.setValue("objeto generado desde el controlador");
        tb.setParent(componentePadre);

        Grid g = new Grid();

    }

    @Listen("onChanging=#categoriaTb, #nombreTb, #nutrientesTb")
    public void filtrar(InputEvent event){
        String categoria = categoriaTb.getValue();
        String nombre = nombreTb.getValue();
        String nutrientes=nutrientesTb.getValue();

        try {
            if (event.getTarget() == categoriaTb) categoria = event.getValue();
            if (event.getTarget() == nombreTb) nombre = event.getValue();
            if (event.getTarget() == nutrientesTb) nutrientes = event.getValue();
        }catch (NullPointerException npe){
            System.out.println("El método no se está llamando desde un evento");
        }

        List<Alimento> listaAlimentos =alimentoService.obtenerAlimentosFiltrados(categoria,nombre, nutrientes,todosLosAlimentos);
        ListModelList<Alimento> modelo = new ListModelList<Alimento>(listaAlimentos);
        alimentosGrid.setModel(modelo);
    }

    @Listen("onSelect=#categoriaCb")
    public void seleccionarCategoria(){
        try {
            String categoria = categoriaCb.getSelectedItem().getLabel();
            if (categoria.equals("Todos")) {
                todosLosAlimentos = new ArrayList<Alimento>(alimentoService.obtenerTodos());
                ListModelList<Alimento> modelo = new ListModelList<Alimento>(todosLosAlimentos);
                alimentosGrid.setModel(modelo);
            } else {
                todosLosAlimentos = new ArrayList<Alimento>(alimentoService.obtenerPorCategoria(categoria));
                this.filtrar(null);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
