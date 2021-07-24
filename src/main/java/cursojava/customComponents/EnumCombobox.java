package cursojava.customComponents;

import cursojava.util.EquipoDeFutbol;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

public class EnumCombobox extends Combobox implements AfterCompose {

    private Class<Enum> miEnumerable;

    @Override
    public void afterCompose() {
        ListModelList modelo = new ListModelList(miEnumerable.getEnumConstants());
        this.setModel(modelo);

    }

    public Class<Enum> getMiEnumerable() {
        return miEnumerable;
    }

    public void setMiEnumerable(String enumerable) {
        try {
            this.miEnumerable = (Class<Enum>) Class.forName(enumerable);
        }catch(Exception e){

        }
    }
}
