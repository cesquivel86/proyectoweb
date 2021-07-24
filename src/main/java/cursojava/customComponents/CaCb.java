package cursojava.customComponents;

import cursojava.entity.Categoria;
import cursojava.services.AlimentoService;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.ListModelList;

import java.util.List;

public class CaCb extends Combobox implements AfterCompose {

    @WireVariable
    AlimentoService alimentoService;

    @Override
    public void afterCompose() {
        alimentoService= (AlimentoService) SpringUtil.getBean("alimentoService");
        try {

            List<Categoria> categoriaList = alimentoService.obtenerCategoriasPorBaseDeDatos();
            ListModelList<Categoria> model = new ListModelList<Categoria>(categoriaList);
            this.setModel(model);

            this.setItemRenderer(new ComboitemRenderer<Categoria>() {
                @Override
                public void render(Comboitem comboitem, Categoria o, int i) throws Exception {
                    comboitem.setLabel(o.getNombre());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
