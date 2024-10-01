package gui;

import java.time.LocalDate;

import controller.Controller;
import model.Konference;
import model.Udflugt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UdflugtWindowOpret extends Stage {
	private Konference konference;
	private Udflugt udflugt;

	public UdflugtWindowOpret(String title, Konference konference) {
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.konference = konference;
		

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}

	public UdflugtWindowOpret(String title) {
		this(title, null);
	}

	// -------------------------------------------------------------------------

	private TextField txfName, txfPris, txfAdresse, txfStart, txfSlut;
	private Label lblError;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblName = new Label("Navn");
		pane.add(lblName, 0, 0);

		txfName = new TextField();
		pane.add(txfName, 0, 1);
		txfName.setPrefWidth(200);

		Label lblAdresse = new Label("Adresse");
		pane.add(lblAdresse, 0, 2);
		txfAdresse = new TextField();
		pane.add(txfAdresse, 0, 3);
		txfAdresse.setPrefWidth(200);

		Label lblPris = new Label("Pris");
		pane.add(lblPris, 0, 4);

		txfPris = new TextField();
		pane.add(txfPris, 0, 5);

		Label lblStart = new Label("Start Dato");
		pane.add(lblStart, 0, 6);

		txfStart = new TextField();
		pane.add(txfStart, 0, 7);
		
		Label lblSlut = new Label("Start Dato");
		pane.add(lblSlut, 0, 8);

		txfSlut = new TextField();
		pane.add(txfSlut, 0,9);



		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 0, 10);
		GridPane.setHalignment(btnCancel, HPos.LEFT);
		btnCancel.setOnAction(event -> this.cancelAction());

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 10);
		GridPane.setHalignment(btnOK, HPos.RIGHT);
       btnOK.setOnAction(event -> this.okAction());

		lblError = new Label();
		pane.add(lblError, 0, 11);
		lblError.setStyle("-fx-text-fill: red");

	}
 

	// -------------------------------------------------------------------------

	private void cancelAction() {
		this.hide();
	}

	private void okAction() {
		String name = txfName.getText().trim();
		String adresse = txfAdresse.getText().trim();
		String start = txfStart.getText().trim();
		String slut = txfSlut.getText().trim();
		LocalDate Start = LocalDate.parse(start);
		LocalDate Slut = LocalDate.parse(slut);
		double pris = Double.parseDouble(txfPris.getText().trim());
		// Call controller methods
		Controller.createUdflugt(konference, name, adresse, pris, Start, Slut);
		
		this.hide();
		
	
	}
}
