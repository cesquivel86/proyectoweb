package cursojava.controllers;

import cursojava.entity.Alimento;
import cursojava.entity.Categoria;
import cursojava.services.AlimentoService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;

public class NuevoAlimentoController extends SelectorComposer<Component> {
    @WireVariable
    AlimentoService alimentoService;

    @Wire
    Window nuevoAlimento;

    @Wire
    Combobox categoriaCbModal;

    @Wire
    Textbox nNutrientesTb, nNombreTb,nCantidadTb;

    @Wire
    Button guardarBtn;


    HashMap<Object, Object> hmap = new HashMap<Object, Object>(Executions.getCurrent().getArg());


    Grid grid;
    List<Alimento> todosLosAlimentos;

    Alimento alimentoAModificar=null;

    @Override
    public void doAfterCompose(Component window) throws Exception {
        super.doAfterCompose(window);

        alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");

        List<Categoria> categorias = alimentoService.obtenerCategoriasPorBaseDeDatos();
        ListModelList categoriasModel = new ListModelList(categorias);
        categoriaCbModal.setModel(categoriasModel);

        grid= (Grid) hmap.get("grid");
        todosLosAlimentos= (List<Alimento>) hmap.get("listaDeAlimentos");
        alimentoAModificar = (Alimento) hmap.get("alimento");

        if(alimentoAModificar!=null){
            categoriaCbModal.setValue(alimentoAModificar.getCategoria().getNombre());
            nNombreTb.setValue(alimentoAModificar.getNombre());
            nCantidadTb.setValue(alimentoAModificar.getCantidad());
            nNutrientesTb.setValue(alimentoAModificar.getNutrientes());
            guardarBtn.setLabel("Actualizar");
        }



    }
    @Listen("onClick=#guardarBtn")
    public void guardarAlimento(){
        //crear nuevo objeto
        if(alimentoAModificar==null) {
            alimentoAModificar = new Alimento();
            todosLosAlimentos.add(alimentoAModificar);
        }

        // cargar Objeto
        Categoria c = (Categoria) categoriaCbModal.getModel().getElementAt(categoriaCbModal.getSelectedIndex());
        alimentoAModificar.setCategoria(c);
        alimentoAModificar.setNombre(nNombreTb.getValue());
        alimentoAModificar.setNutrientes(nNutrientesTb.getValue());
        alimentoAModificar.setCantidad(nCantidadTb.getValue());
        alimentoAModificar.setDeleted('0');

        // guardar en base de datos
        alimentoService.guardarAlimento(alimentoAModificar);

        // refrescar el Grid de la vista


        ListModelList modelo = new ListModelList(todosLosAlimentos);
        grid.setModel(modelo);

        // cerrar el modal
        nuevoAlimento.detach();


    }

    @Listen("onClick = #closeBtn")
    public void showModal(Event e) {
        try {
            nuevoAlimento.detach();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
