package cursojava.controllers;

import cursojava.entity.Categoria;
import cursojava.entity.Alimento;
import cursojava.services.AlimentoService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
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

    List<Alimento> todosLosAlimentos;

    public void doAfterCompose(Component comp) throws Exception {
        try{
            super.doAfterCompose(comp);
            alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");
            System.out.println("bean cargado");

            todosLosAlimentos = new ArrayList<Alimento>(alimentoService.obtenerTodos());
            ListModelList<Alimento> modelo = new ListModelList<Alimento>(todosLosAlimentos);
            alimentosGrid.setModel(modelo);



            alimentosGrid.setRowRenderer(new RowRenderer<Object>() {
                @Override
                public void render(Row row, Object objetoDeModelo, int i) throws Exception {
                    final Alimento a = (Alimento) objetoDeModelo;

                    Label etiquetaCategoria = new Label();
                    etiquetaCategoria.setValue(a.getCategoria().getNombre());
                    etiquetaCategoria.setParent(row);

                    Label etiquetaNombre = new Label();
                    etiquetaNombre.setValue(a.getNombre());
                    etiquetaNombre.setParent(row);

                    new Label(a.getNutrientes()).setParent(row);
                    new Label(a.getPorcentajeDiario().toString()).setParent(row);
                    new Label(a.getCalorias().toString()).setParent(row);
                    new Label(a.getCantidad()).setParent(row);

                    Button eliminarBtn =new Button("Eliminar");

                    eliminarBtn.addEventListener("onClick",new EventListener() {
                        @Override
                        public void onEvent(Event event) throws Exception {

                            Messagebox.show("¿está seguro que desea eliminar el Alimento?",
                                    "Question", Messagebox.OK | Messagebox.CANCEL,
                                    Messagebox.QUESTION,
                                    new org.zkoss.zk.ui.event.EventListener(){
                                        public void onEvent(Event e){
                                            if(Messagebox.ON_OK.equals(e.getName())){
                                                alimentoService.eliminarAlimentoDesdeTabla(a);
                                                todosLosAlimentos.remove(a);
                                                ListModelList<Alimento> modelo = new ListModelList<Alimento>(todosLosAlimentos);
                                                alimentosGrid.setModel(modelo);
                                            }else if(Messagebox.ON_CANCEL.equals(e.getName())){
                                                System.out.println("evento cancelado");
                                            }
                                        }
                                    }
                            );


                        }
                    });


                    eliminarBtn.setParent(row);
                }
            });

            List<Categoria> categorias = alimentoService.obtenerCategoriasPorBaseDeDatos();
            ListModelList categoriasModel = new ListModelList();
            Categoria c = new Categoria();
            c.setNombre("Todos");
            categoriasModel.add(c);
            categoriasModel.addAll(categorias);
            categoriaCb.setModel(categoriasModel);



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

        List<Alimento>alimentosFiltrados =alimentoService.obtenerAlimentosFiltrados(categoria,nombre, nutrientes,todosLosAlimentos);
        ListModelList<Alimento> modelo = new ListModelList<Alimento>(alimentosFiltrados);
        alimentosGrid.setModel(modelo);
    }

    @Listen("onSelect=#categoriaCb")
    public void seleccionarCategoria(){
        try {
            if(categoriaCb.getSelectedIndex()==0) {
                todosLosAlimentos = new ArrayList<Alimento>(alimentoService.obtenerTodos());
            }else {
                Categoria categoria = (Categoria) categoriaCb.getModel().getElementAt(categoriaCb.getSelectedIndex());
                todosLosAlimentos = alimentoService.obtenerPorCategoria(categoria);
            }
            this.filtrar(null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    @Listen("onClick = #nuevoAlimentoBtn")
    public void showModal(Event e) {
        HashMap parametros = new HashMap<String, Object>();
        parametros.put("parametro", "este es mi parámetro");
        parametros.put("listaDeAlimentos", todosLosAlimentos);
        parametros.put("grid",alimentosGrid);

        //crea una nueva ventana a partir de un archivo .zul
        Window window = (Window) Executions.createComponents(
                "/vistas/alimentos/nuevoAlimento.zul", null, parametros);
        window.doModal();
    }

    public void buscarBoton(List<Component> components){
        for(Component c: components){
            if(c instanceof Button){
                System.out.println();
                System.out.println("botón encontrado");
                System.out.println();
            }else{
                System.out.println();
                System.out.println("Componente encontrado");
                System.out.println(c.getClass());
                System.out.println();
                if(c.getChildren()!=null){
                    buscarBoton(c.getChildren());
                }
            }
        }

    }
}
