package org.example.controllers;


import org.example.entity.Persona;
import org.example.services.PersonaService;
import org.example.services.PersonaServiceImpl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;


public class PersonasController extends SelectorComposer<Component> {

    @Wire
    private Textbox nombreTb;
    @Wire
    private Textbox apellidosTb;
    @Wire
    private Datebox fechaNacDb;


    PersonaService MyPersonaService;

    public void guardarPersona(){
        Persona p = new Persona();
        p.setNombre(nombreTb.getValue());
        p.setApellidos(apellidosTb.getValue());
        p.setFechaNacimiento(fechaNacDb.getValue());
        MyPersonaService.guadarPersona(p);
    }
}
