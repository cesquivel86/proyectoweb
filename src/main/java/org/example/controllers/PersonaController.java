package org.example.controllers;


import org.example.entity.Persona;
import org.example.services.PersonaService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PersonaController extends SelectorComposer<Component> {

    @WireVariable
    PersonaService personaService;

    @Wire
    private Textbox nombreTb;
    @Wire
    private Textbox apellidosTb;
    @Wire
    private Datebox fechaNacDb;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        personaService = (PersonaService) SpringUtil.getBean("personaService");
        System.out.println("bean cargado");

    }


    @Listen("onClick = #guadarBtn")
    public void guardarPersona(){
        Persona p = new Persona();
        p.setNombre(nombreTb.getValue());
        p.setApellidos(apellidosTb.getValue());
        p.setFechaNacimiento(fechaNacDb.getValue());
        personaService.guadarPersona(p);
    }
}
