package cursojava.customComponents;

import cursojava.util.MacroNutriente;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

public class MNCb extends Combobox  implements AfterCompose{

    @Override
    public void afterCompose() {
        ListModelList model = new ListModelList(MacroNutriente.values());
        setModel(model);
    }
}
