package cursojava.customComponents;

import cursojava.util.MacroNutriente;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

public class MacronutrientesCombobox extends Combobox implements AfterCompose {
    @Override
    public void afterCompose() {
        ListModelList<MacroNutriente> modelo = new ListModelList<MacroNutriente>(MacroNutriente.values());
        this.setModel(modelo);
    }
}
