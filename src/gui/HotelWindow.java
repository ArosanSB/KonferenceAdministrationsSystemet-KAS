package gui;

import java.util.ArrayList;
import java.util.Optional;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Hotel;
import model.Tillæg;

public class HotelWindow extends Stage {
	private Hotel hotel;

	public HotelWindow(String title, Hotel hotel) {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);

		this.hotel = hotel;

		setTitle(title);
		GridPane pane = new GridPane();
		initContent(pane);

		Scene scene = new Scene(pane);
		setScene(scene);
	}

	public HotelWindow(String title) {
		this(title, null);
	}

	// -------------------------------------------------------------------------

	private TextField txfNavn, txfEnkeltSeng, txfDobbeltSeng, txfSted;
	private ArrayList<Tillæg> tillæg = new ArrayList<>();
	private ListView<Tillæg> lvwTillæg;
	private Label lblError;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblNavn = new Label("Navn");
		pane.add(lblNavn, 0, 0);

		txfNavn = new TextField();
		pane.add(txfNavn, 0, 1);
		txfNavn.setPrefWidth(400);

		Label lblEnkeltSeng = new Label("Enkelt Seng Pris:");
		pane.add(lblEnkeltSeng, 0, 2);

		txfEnkeltSeng = new TextField();
		pane.add(txfEnkeltSeng, 0, 3);

		Label lblDobbeltSeng = new Label("Dobbelt Seng Pris:");
		pane.add(lblDobbeltSeng, 0, 4);

		txfDobbeltSeng = new TextField();
		pane.add(txfDobbeltSeng, 0, 5);

		Label lblSted = new Label("Sted:");
		pane.add(lblSted, 0, 6);

		txfSted = new TextField();
		pane.add(txfSted, 0, 7);

		Label lblTillæg = new Label("Tillæg:");
		pane.add(lblTillæg, 0, 8);

		lvwTillæg = new ListView<>();
		pane.add(lvwTillæg, 0, 9);
		lvwTillæg.setPrefWidth(100);
		lvwTillæg.setPrefHeight(100);
		ChangeListener<Tillæg> listener = (ov, oldTillæg, newTillæg) -> this.selectedTillægChanged();
		lvwTillæg.getSelectionModel().selectedItemProperty().addListener(listener);

		HBox hbxButtons = new HBox(40);
		pane.add(hbxButtons, 0, 10, 1, 1);
		hbxButtons.setAlignment(Pos.BASELINE_CENTER);

		Button btnCancel = new Button("Cancel");
		hbxButtons.getChildren().add(btnCancel);
		btnCancel.setOnAction(event -> cancelAction());

		Button btnCreate = new Button("Create tillæg");
		hbxButtons.getChildren().add(btnCreate);
		btnCreate.setOnAction(event -> createAction());

		Button btnDelete = new Button("Delete tillæg");
		hbxButtons.getChildren().add(btnDelete);
		btnDelete.setOnAction(event -> deleteAction());

		Button btnOK = new Button("OK");
		hbxButtons.getChildren().add(btnOK);
		btnOK.setOnAction(event -> okAction());

		lblError = new Label();
		pane.add(lblError, 0, 11);
		lblError.setStyle("-fx-text-fill: red");

		initControls();
	}

	private void initControls() {
		if (hotel != null) {
			txfNavn.setText(hotel.getNavn());
			txfEnkeltSeng.setText("" + hotel.getPrisEnkeltSeng());
			txfDobbeltSeng.setText("" + hotel.getPrisDobbeltSeng());
			txfSted.setText(hotel.getSted());
			lvwTillæg.getItems().setAll(hotel.getTillæg());
		} else {
			txfNavn.clear();
			txfEnkeltSeng.clear();
			txfDobbeltSeng.clear();
			txfSted.clear();
			lvwTillæg.getItems().clear();
		}
	}

	// -------------------------------------------------------------------------

	private void selectedTillægChanged() {
		lvwTillæg.getSelectionModel().getSelectedItem();
	}

	private void cancelAction() {
		hide();
	}

	private void createAction() {
		TillægWindow dia = new TillægWindow("Create Tillæg");
		dia.showAndWait();

		// Wait for the modal dialog to close
		if (hotel != null) {
			for (Tillæg tillæg : hotel.getTillæg()) {
				this.tillæg.add(tillæg);
			}
		}

		if (dia.tillæg != null) {
			tillæg.add(dia.tillæg);
		}

		lvwTillæg.getItems().setAll(tillæg);
	}

	private void deleteAction() {
		Tillæg tillæg = lvwTillæg.getSelectionModel().getSelectedItem();
		if (tillæg == null) {
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Tillæg");
		// alert.setContentText("Are you sure?");
		alert.setHeaderText("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();

		// Wait for the modal dialog to close

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			this.tillæg.remove(tillæg);
			lvwTillæg.getItems().setAll(this.tillæg);
		}

	}

	private void okAction() {
		String navn = txfNavn.getText().trim();
		if (navn.length() == 0) {
			lblError.setText("Name is empty");
			return;
		}

		double enkeltSeng = -1;
		try {
			enkeltSeng = Double.parseDouble(txfEnkeltSeng.getText().trim());
		} catch (NumberFormatException ex) {
			// do nothing
		}
		if (enkeltSeng < 0) {
			lblError.setText("Enkelt Seng Pris is not a positive number");
			return;
		}

		double dobbeltSeng = -1;
		try {
			dobbeltSeng = Double.parseDouble(txfDobbeltSeng.getText().trim());
		} catch (NumberFormatException ex) {
			// do nothing
		}
		if (dobbeltSeng < 0) {
			lblError.setText("Dobbelt Seng Pris is not a positive number");
			return;
		}

		String sted = txfSted.getText().trim();
		if (sted.length() == 0) {
			lblError.setText("Sted is empty");
			return;
		}

		// Call controller methods
		if (hotel != null) {
			Controller.updateHotel(hotel, navn, sted, enkeltSeng, dobbeltSeng);
			for (Tillæg tillæg : hotel.getTillæg()) {
				hotel.removeTillæg(tillæg);
			}
			for (Tillæg tillæg : tillæg) {
				Controller.createTillæg(hotel, tillæg.getNavn(), tillæg.getPris());
			}
		} else {
			Hotel hotel2 = Controller.createHotel(navn, sted, enkeltSeng, dobbeltSeng);
			for (Tillæg tillæg : tillæg) {
				Controller.createTillæg(hotel2, tillæg.getNavn(), tillæg.getPris());
			}
		}
		hide();
	}

}
