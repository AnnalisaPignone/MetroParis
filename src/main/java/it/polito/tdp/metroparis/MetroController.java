/**
 * Sample Skeleton for 'Metro.fxml' Controller Class
 */

package it.polito.tdp.metroparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class MetroController {
	private Model model; 

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxArrivo"
    private ComboBox<Fermata> boxArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="boxPartenza"
    private ComboBox<Fermata> boxPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="colonnaFermata"
    private TableColumn<Fermata, String> colonnaFermata; // Value injected by FXMLLoader

    @FXML // fx:id="tablePercorso"
    private TableView<Fermata> tablePercorso; // Value injected by FXMLLoader

    @FXML
    void handleCerca(ActionEvent event) {
    	Fermata partenza=boxPartenza.getValue(); 
    	Fermata arrivo= boxArrivo.getValue(); 
    	
    	if(partenza!=null && arrivo != null && !partenza.equals(arrivo)) {
    		List<Fermata> percorso= model.calcolaPercorso(partenza, arrivo); 
    		
    		tablePercorso.setItems(FXCollections.observableArrayList(percorso)); 
    		txtResult.setText("Percorso trovato con "+percorso.size()+" stazioni\n"); 
    	}
    	else 
    		txtResult.setText("Devi selezionare due stazioni, diverse tra loro\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'Metro.fxml'.";
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'Metro.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Metro.fxml'.";

        colonnaFermata.setCellValueFactory(new PropertyValueFactory<Fermata, String>("nome"));
    }

	public void setModel(Model m) {
	this.model=m; 	
	List<Fermata> fermate = this.model.getFermate(); 
	boxPartenza.getItems().addAll(fermate); 
	boxArrivo.getItems().addAll(fermate);
	
	}

}

