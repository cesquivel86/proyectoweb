package org.example.controllers;

import org.example.entity.Log;
import org.example.services.MyService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyController extends SelectorComposer<Component> {

    @WireVariable
    private MyService myService;

    @Wire
    private Textbox keywordBox;

    @Wire
    private Listbox logListbox;

    private String message;

    @Listen("onCreate = #addButton")
    public void search(){
        List<Log> logList = myService.getLogs();
        ListModelList<Log> logListModel = new ListModelList<Log>(logList);
    }

    @Listen("onClick = #addButton")
    public void addLog() {
        System.out.println("botón clickado");
        message = keywordBox.getValue();
        Log log = new Log(message);
        log = myService.addLog(log);
        ListModelList<Log> listModelList = new ListModelList<Log>((Collection<? extends Log>) logListbox.getModel());
        listModelList.add(log);
        logListbox.setModel(listModelList);
    }

}
