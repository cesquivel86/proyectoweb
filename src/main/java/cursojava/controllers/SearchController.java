package cursojava.controllers;

import java.util.List;
import java.util.Set;


import cursojava.services.CarService;
import cursojava.services.impl.CarServiceImpl;
import cursojava.entity.Car;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;


public class SearchController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;
//Wire es una anotación de ZK para enlazar objetos de la vista en el controlador
    @Wire
    private Textbox keywordBox;
    @Wire
    private Listbox carListbox;
    @Wire
    private Label modelLabel;
    @Wire
    private Label makeLabel;
    @Wire
    private Label priceLabel;
    @Wire
    private Label descriptionLabel;
    @Wire
    private Image previewImage;
    @Wire
    private Component detailBox;


    private CarService carService = new CarServiceImpl();

    @Listen("onClick = #searchButton")
    public void search(){
        String keyword = keywordBox.getValue();
        List<Car> result = carService.search(keyword);
        carListbox.setModel(new ListModelList<Car>(result));
    }

    @Listen("onSelect = #carListbox")
    public void showDetail(){
        detailBox.setVisible(true);

        Set<Car> selection = ((Selectable<Car>)carListbox.getModel()).getSelection();
        if (selection!=null && !selection.isEmpty()){
            Car selected = selection.iterator().next();
            previewImage.setSrc(selected.getPreview());
            modelLabel.setValue(selected.getModel());
            makeLabel.setValue(selected.getMake());
            priceLabel.setValue(selected.getPrice().toString());
            descriptionLabel.setValue(selected.getDescription());
        }
    }
}