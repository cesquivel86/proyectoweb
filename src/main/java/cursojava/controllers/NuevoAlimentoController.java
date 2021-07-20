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


    HashMap<Object, Object> hmap = new HashMap<Object, Object>(Executions.getCurrent().getArg());

    Grid grid;
    List<Alimento> todosLosAlimentos;

    @Override
    public void doAfterCompose(Component window) throws Exception {
        super.doAfterCompose(window);
        super.doAfterCompose(window);
        alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");

        List<Categoria> categorias = alimentoService.obtenerCategoriasPorBaseDeDatos();
        ListModelList categoriasModel = new ListModelList(categorias);
        categoriaCbModal.setModel(categoriasModel);

        System.out.println(hmap.get("parametro").toString());
        grid= (Grid) hmap.get("grid");
        todosLosAlimentos= (List<Alimento>) hmap.get("listaDeAlimentos");
    }
    @Listen("onClick=#guardarBtn")
    public void guardarAlimento(){
        Alimento alimento = new Alimento();
        Categoria c = (Categoria) categoriaCbModal.getModel().getElementAt(categoriaCbModal.getSelectedIndex());
        alimento.setCategoria(c);
        alimento.setNombre(nNombreTb.getValue());
        alimento.setNutrientes(nNutrientesTb.getValue());
        alimento.setCantidad(nCantidadTb.getValue());
        alimento.setDeleted('0');

        alimentoService.guardarAlimento(alimento);
        todosLosAlimentos.add(alimento);
        ListModelList modelo = new ListModelList(todosLosAlimentos);
        grid.setModel(modelo);

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
