package cursojava.customComponents;

import cursojava.entity.Categoria;
import cursojava.services.AlimentoService;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

import java.util.List;

public class CategoriaCombobox extends Combobox implements AfterCompose {

    private List<Categoria> categoriaList;

    private AlimentoService alimentoService;

    @Override
    public void afterCompose() {
        alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");
        categoriaList=alimentoService.obtenerCategoriasPorBaseDeDatos();
        ListModelList<Categoria> modelo = new ListModelList<>(categoriaList);
        this.setModel(modelo);

    }
    @Override
    public String toString(){
        return "probando";
    }
}
