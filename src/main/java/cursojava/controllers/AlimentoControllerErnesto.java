package cursojava.controllers;



import cursojava.entity.Alimento;
import cursojava.entity.Categoria;
import cursojava.services.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AlimentoControllerErnesto extends SelectorComposer<Component>
{
    @Autowired private AlimentoService alimentoService;

    @Wire private Combobox cboCategorias;
    @Wire private Textbox filterCategory;
    @Wire private Textbox filterNombre;
    @Wire private Textbox filterNutrientes;
    @Wire private Button filterBtnCategory;
    @Wire private Button filterBtnNombre;
    @Wire private Button filterBtnNutrientes;
    @Wire private Grid listAlimentos;
    @Wire private Footer nRegsAlimentos;
    @Wire private Footer nSegundos;
    @Wire private Timer segundero;
    @Wire private Div componentePadre;
    @Wire private ArrayList<Button> botones = new ArrayList<Button>();

    private static final String lblNumRegs = "Se encontraron NN registros.";
    private List<Alimento> dataModelAlimento = new ArrayList<Alimento>();
    private List<Alimento> dataModelAlimentoAux = new ArrayList<Alimento>();

    private List<Categoria> dataModelCategoria = new ArrayList<Categoria>();

    private int segundos = 0;

    // ECC2021.07.14 Método que se ejecuta tras la generación y despliegue de la vista zk [front end]
    // ECC2021.07.16 Funciona como método inicial
    public void doAfterCompose(Component comp) throws Exception
    {
        super.doAfterCompose(comp);
        alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");

        // System.out.println("bean cargado");
        //getAlimentos();
    }

    @Listen("onCreate = #filterBtnNutrientes")
    public void getAlimentos()
    {
        limpiarFiltros();
        getAllRegistros();
        cargaCategorias();

        /*Rows listRows = listAlimentos.getRows();
        for(int i = 1; i < listAlimentos.getChildren().size(); i++)
        {
            if(listRows.getChildren().get(i) instanceof Row)
            {
                Row r = (Row) listAlimentos.getChildren().get(i);
                for (int j = 1; j < r.getChildren().size(); j++)
                {
                    if (r.getChildren().get(j) instanceof Button)
                    {
                        Button boton = (Button) r.getChildren().get(j);
                        boton.addEventListener("onClick", new EventListener<Event>() {
                            @Override
                            public void onEvent(Event arg0) throws Exception {
                                AlimentoRelacionado a = (AlimentoRelacionado) listAlimentos.getModel().getElementAt(r.getIndex());
                                System.out.println("Botón encontrado..." + a.getId());
                                // servicio para borrar.
                            }

                        });
                        botones.add(boton);
                    }
                }
            }
        }*/
/*
        for (Component row : listAlimentos.getRows().getChildren()) {
            row.addEventListener(Events.ON_CLICK, new EventListener<Event>()
            {
                @Override
                public void onEvent(Event arg0) throws Exception
                {
                    Row row = (Row) arg0.getTarget();
                    Boolean rowSelected = (Boolean) row.getAttribute("Selected");
                    if (rowSelected != null && rowSelected) {
                        row.setAttribute("Selected", false);
                        // row.setStyle("");
                        row.setSclass("");
                    } else {
                        row.setAttribute("Selected", true);
                        // row.setStyle("background-color: #CCCCCC !important");   // inline style
                        row.setSclass("z-row-background-color-on-select");         // sclass
                    }
                }
            });
        }*/
    }

    /*@Command("clearCache")
    @NotifyChange("model")
    public void doClearCache()
    {
        System.out.println("se hizo click");
    }*/

    @Command
    public void borrarAlimento(@BindingParam("alimento") Alimento alimento)
    {
        System.out.println("se hizo click en " + alimento.getNombre());

    }
    @Listen("onClick = #listAlimentos > rows > row")
    public void clickRow(MouseEvent event)
    {
        System.out.println("se hizo click...");
    }

    @Listen("onClick = #nuevoAlimentoBtn")
    public void showModal(Event e) {
        HashMap parametros = new HashMap<String, Object>();
        parametros.put("parametro", "este es mi parámetro");
        //crea una nueva ventana a partir de un archivo .zul
        Window window = (Window) Executions.createComponents("/vistas/nuevoAlimento.zul", null, null);
        window.doModal();
    }

    // @Listen("onChanging=#categoriaTb, #nombreTb, #nutrientesTb")
    // @Listen("onClick = button#filterBtnCategory; onOK = textbox#filterCategory;")
    @Listen("onChanging = textbox#filterCategory; onClick = button#filterBtnCategory; onOK = textbox#filterCategory;")
    public void filtrarPorCategoria(InputEvent event)
    {
        filtrarTxt(filterCategory, event.getValue());
    }

    @Listen("onChanging = textbox#filterNombre; onClick = button#filterBtnNombre; onOK = textbox#filterNombre;")
    public void filtrarPorNombre(InputEvent event)
    {
        filtrarTxt(filterNombre, event.getValue());
    }

    @Listen("onChanging = textbox#filterNutrientes; onClick = button#filterBtnNutrientes; onOK = textbox#filterNutrientes;")
    public void filtrarPorNutrientes(InputEvent event)
    {
        filtrarTxt(filterNutrientes, event.getValue());
    }

    private void cargaCategorias()
    {
        /*String ultimaCategoria = "TODAS"; // "Selecconar..."
        cboCategorias.appendItem(ultimaCategoria);
        for (int i = 0; i < dataModelAlimento.size(); i++)
        {
            if(dataModelAlimento.get(i).getCategoria() == ultimaCategoria)
                continue; // Si repite la categoría salta a la siguiente iteración.

            cboCategorias.appendItem(dataModelAlimento.get(i).getCategoria());
            ultimaCategoria = dataModelAlimento.get(i).getCategoria();
        }*/

        dataModelCategoria = alimentoService.obtenerCategoriasPorBaseDeDatos();
        for (int i = 0; i < dataModelCategoria.size(); i++)
        {
            cboCategorias.appendItem(dataModelCategoria.get(i).getNombre());
        }
        cboCategorias.setSelectedIndex(0);
    }

    private void getAllRegistros()
    {
        //List<Alimentos> dataModelAlimento = alimentosService.getAlimentosAll();
        dataModelAlimento = alimentoService.obtenerTodos();

        showRegistros(dataModelAlimento, listAlimentos);
    }

    private void filtrarTxt(Textbox filtro, String cadena)
    {
        filtro.setValue(cadena);
        boolean bolRegsFound = false;
        dataModelAlimentoAux.clear();
        getAllRegistros();

        if(filtro.getValue().length() > 0)
        {
            for (int i = 0; i < dataModelAlimento.size(); i++)
            {
                switch (filtro.getId())
                {
                    case "filterCategory":
                        if (dataModelAlimento.get(i).getCategoria().getNombre().toLowerCase().contains(filtro.getValue().toLowerCase())) {
                            dataModelAlimentoAux.add(dataModelAlimento.get(i));
                            bolRegsFound = true;
                        }
                        break;
                    case "filterNombre":
                        if (dataModelAlimento.get(i).getNombre().toLowerCase().contains(filtro.getValue().toLowerCase())) {
                            dataModelAlimentoAux.add(dataModelAlimento.get(i));
                            bolRegsFound = true;
                        }
                        break;
                    case "filterNutrientes":
                        if (dataModelAlimento.get(i).getNutrientes().toLowerCase().contains(filtro.getValue().toLowerCase())) {
                            dataModelAlimentoAux.add(dataModelAlimento.get(i));
                            bolRegsFound = true;
                        }
                        break;
                }
            }
        }
        else
        {
            dataModelAlimentoAux = alimentoService.obtenerTodos();
            bolRegsFound = true;
        }

        if(bolRegsFound)
            showRegistros(dataModelAlimentoAux, listAlimentos);
        else
            nRegsAlimentos.setLabel(lblNumRegs.replace("NN","0"));
    }

    @Listen("onSelect = combobox#cboCategorias")
    public void elegirCategoria()
    {
        if(cboCategorias.getSelectedItem().getLabel() != "TODAS")
        {
            filtrarTxt(filterCategory, cboCategorias.getSelectedItem().getLabel());
        }
        else
        {
            limpiarFiltros();
            getAllRegistros();
        }
    }

    private void limpiarFiltros()
    {
        filterCategory.setValue("");
        filterNombre.setValue("");
        filterNutrientes.setValue("");
    }

    @Listen("onClick = button#filtersBtnLimpiar;")
    public void limpiarFilters()
    {
        limpiarFiltros();
        cboCategorias.setSelectedIndex(0);
        getAllRegistros();
    }

    private void showRegistros(List<Alimento> dataModel, Grid grid)
    {
        listAlimentos.setModel(new ListModelList<Alimento>(dataModel));
        nRegsAlimentos.setLabel(lblNumRegs.replace("NN", String.valueOf(grid.getListModel().getSize()))); // dataModelAlimento.size()
    }

    @Listen("onTimer = timer#segundero;")
    public void agregarSegundo()
    {
        segundos++;
        nSegundos.setLabel("Esta sesión lleva abierta " + segundos + " segundos.");
        // Textbox tb = new Textbox();
        // tb.setValue("texto adicional");
        // tb.setParent(componentePadre);
    }
}