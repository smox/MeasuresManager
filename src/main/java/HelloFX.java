import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import persistence.model.Entry;
import persistence.model.TypeOfWine;
import persistence.model.Wine;
import persistence.service.EntryService;

import java.sql.Date;

public class HelloFX {

    @FXML
    private Label lblHello;


    public void sayHello(ActionEvent actionEvent) {

        /*
        System.out.println("Initializing dababase connection...");
        EntryService entryService = new EntryService();

        Entry entry = new Entry();
        entry.setYear("2020");
        entry.setAmount(5000);
        entry.setContainer("1");
        entry.setRealizedAt(new Date(System.currentTimeMillis()));

        Wine wine = new Wine();
        wine.setName("Der Junge");
        wine.setVintage("2020");

        TypeOfWine gv = new TypeOfWine();
        wine.setTypeOfWine(gv);

        entry.setWine(wine);

        entryService.persist(entry);*/

        if(lblHello.getText().equals("Hello JavaFX!")) {
            lblHello.setText("Hello World!!");
        } else {
            lblHello.setText("Hello JavaFX!");
        }

    }
}
