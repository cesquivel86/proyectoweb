package cursojava.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import java.util.HashMap;

public class NuevoAlimentoController extends SelectorComposer<Component> {
    @Wire
    Window nuevoAlimento;


    @Override
    public void doAfterCompose(Component window) throws Exception {
        super.doAfterCompose(window);
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
