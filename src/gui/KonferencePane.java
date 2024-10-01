package gui;

import java.util.Optional;
import model.*;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class KonferencePane extends GridPane {
	private TextField txfName, txfAdresse, txfPris, txfStart, txfSlut;
	private TextArea txaDeltager, txaHoteller, txaUdflugter;
	private ListView<Konference> lvwKonferencer;

	public KonferencePane() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblKonf = new Label("Konferencer");
		this.add(lblKonf, 0, 0);

		lvwKonferencer = new ListView<>();
		this.add(lvwKonferencer, 0, 1, 1, 5);
		lvwKonferencer.setPrefWidth(200);
		lvwKonferencer.setPrefHeight(200);
		lvwKonferencer.getItems().setAll(Controller.getKonferencer());

		ChangeListener<Konference> listenerKonference = (ok, oldKonference, newKonference) -> this.selectedCompanyChanged();
		lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listenerKonference);
		
		// Navn
		Label lblName = new Label("Navn:");
		this.add(lblName, 1, 1);

		txfName = new TextField();
		this.add(txfName, 2, 1);
		txfName.setEditable(false);

		// adresse
		Label lblAdresse = new Label("Adresse:");
		this.add(lblAdresse, 1, 2);

		txfAdresse = new TextField();
		this.add(txfAdresse, 2, 2);
		txfAdresse.setEditable(false);

		// pris
		Label lblPris = new Label("Pris:");
		this.add(lblPris, 1, 3);

		txfPris = new TextField();
		this.add(txfPris, 2, 3);
		txfPris.setEditable(false);

		// Start og slut dato
		Label lblStart = new Label("Start dato:");
		this.add(lblStart, 4, 1);

		txfStart = new TextField();
		this.add(txfStart, 5, 1);
		txfStart.setEditable(false);

		Label lblSlut = new Label("Slut dato:");
		this.add(lblSlut, 4, 2);

		txfSlut = new TextField();
		this.add(txfSlut, 5, 2);
		txfSlut.setEditable(false);

		// Deltager

		Label lblDeltager = new Label("Deltager:");
		this.add(lblDeltager, 1, 4);
		GridPane.setValignment(lblDeltager, VPos.BASELINE);
		lblDeltager.setPadding(new Insets(4, 0, 4, 0));

		txaDeltager = new TextArea();
		this.add(txaDeltager, 2, 4);
		txaDeltager.setPrefWidth(200);
		txaDeltager.setPrefHeight(100);
		txaDeltager.setEditable(false);

		// hoteller

		Label lblHoteller = new Label("Hoteller:");
		this.add(lblHoteller, 4, 4);

		GridPane.setValignment(lblHoteller, VPos.BASELINE);
		lblHoteller.setPadding(new Insets(4, 0, 4, 0));

		txaHoteller = new TextArea();
		this.add(txaHoteller, 5, 4);
		txaHoteller.setPrefWidth(200);
		txaHoteller.setPrefHeight(100);
		txaHoteller.setEditable(false);

		// Udflugt
		Label lblUdflugt = new Label("Udflugter:");
		this.add(lblUdflugt, 6, 4);

		GridPane.setValignment(lblUdflugt, VPos.BASELINE);
		lblHoteller.setPadding(new Insets(4, 0, 4, 0));

		txaUdflugter= new TextArea();
		this.add(txaUdflugter, 7, 4);
		txaUdflugter.setPrefWidth(200);
		txaUdflugter.setPrefHeight(100);
		txaUdflugter.setEditable(false);

		// button konference
		HBox hbxButtonsKonference = new HBox(40);
		this.add(hbxButtonsKonference, 0, 6, 1, 1);

		hbxButtonsKonference.setPadding(new Insets(10, 0, 0, 0));
		hbxButtonsKonference.setAlignment(Pos.BASELINE_CENTER);

		Button btnCreateKonference = new Button("Opret Konference");
		hbxButtonsKonference.getChildren().add(btnCreateKonference);
		btnCreateKonference.setOnAction(event -> this.createActionKonference());

		Button btnUpdateKonference = new Button("Opdatere Konference");
		hbxButtonsKonference.getChildren().add(btnUpdateKonference);
		btnUpdateKonference.setOnAction(event -> this.updateActionKonference());

		Button btnDeleteKonference = new Button("Slet Konference");
		hbxButtonsKonference.getChildren().add(btnDeleteKonference);
		btnDeleteKonference.setOnAction(event -> this.deleteActionKonference());

		// button deltager

		HBox hbxButtonsDeltager = new HBox(40);
		this.add(hbxButtonsDeltager, 2, 6, 1, 1);

		Button btnTilmeldDeltager = new Button("Tilmeld");
		hbxButtonsDeltager.getChildren().add(btnTilmeldDeltager);
		btnTilmeldDeltager.setOnAction(event -> this.tilmeldDeltager());

		Button btnDeleteDeltager = new Button("Fjern Deltager");
		hbxButtonsDeltager.getChildren().add(btnDeleteDeltager);

		Button btnUpdateDeltager = new Button("Ændre Deltager");
		hbxButtonsDeltager.getChildren().add(btnUpdateDeltager);

		// button Hotel
		HBox hbxButtonsHotel = new HBox(40);
		this.add(hbxButtonsHotel, 5, 6, 1, 1);

		Button btnTilmeldHotel = new Button("Tilmed Hotel");
		hbxButtonsHotel.getChildren().add(btnTilmeldHotel);

		Button btnDeleteHotel = new Button("Fjern Hotel");
		hbxButtonsHotel.getChildren().add(btnDeleteHotel);

		Button btnUpdateHotel = new Button("Ændre Hotel");
		hbxButtonsHotel.getChildren().add(btnUpdateHotel);

		// Button udflugter
		HBox hbxButtonsUdflugt = new HBox(40);
		this.add(hbxButtonsUdflugt, 7, 6, 1, 1);

		Button btnTilmeldUdflugt = new Button("Opret Udflugt");
		hbxButtonsUdflugt.getChildren().add(btnTilmeldUdflugt);
		btnTilmeldUdflugt.setOnAction(event -> this.createActionUdflugt());

		Button btnDeleteUdflugt = new Button("Fjern Udflugt");
		hbxButtonsUdflugt.getChildren().add(btnDeleteUdflugt);


		if (lvwKonferencer.getItems().size() > 0) {
			lvwKonferencer.getSelectionModel().select(0);
		}
	}

	// -------------------------------------------------------------------------

	// Konference

	private void createActionKonference() {
		KonferenceWindow dia = new KonferenceWindow("Opret Konference");
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwKonferencer.getItems().setAll(Controller.getKonferencer());
		int index = lvwKonferencer.getItems().size() - 1;
		lvwKonferencer.getSelectionModel().select(index);
	}

	private void updateActionKonference() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
		if (konference == null) {
			return;
		}

		KonferenceWindow dia = new KonferenceWindow("Update Konference", konference);
		dia.showAndWait();

		// Wait for the modal dialog to close

		int selectIndex = lvwKonferencer.getSelectionModel().getSelectedIndex();
		lvwKonferencer.getItems().setAll(Controller.getKonferencer());
		lvwKonferencer.getSelectionModel().select(selectIndex);
	}

	private void deleteActionKonference() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
		if (konference == null) {
			return;
		}

		Controller.deleteKonference(konference);
		lvwKonferencer.getItems().setAll(Controller.getKonferencer());
		this.updateControls();

	}

//	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------

	// Udflugt

	private void createActionUdflugt() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
		UdflugtWindowOpret dia = new UdflugtWindowOpret("Opret Udflugt til " + konference, konference);
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwKonferencer.getItems().setAll(Controller.getKonferencer());
		int index = lvwKonferencer.getItems().size() - 1;
		lvwKonferencer.getSelectionModel().select(index);
	}

	private void deleteActionUdflugt() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
		if (konference == null) {
			return;
		}
		
		UdflugtWindowChange dia = new UdflugtWindowChange("Slet Udflugt");
		dia.showAndWait();

		konference.removeUdflugt(null);
		lvwKonferencer.getItems().setAll(Controller.getKonferencer());
		this.updateControls();

	}

	// -------------------------------------------------------------------------

	// Deltager
	
	private void tilmeldDeltager() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
	TilmeldingWindow dia = new TilmeldingWindow("Tilmeld deltager til " + konference, konference);
	dia.showAndWait();
	
	this.updateControls();
	
	}
	
	
//	// -------------------------------------------------------------------------
//
	private void selectedCompanyChanged() {
		this.updateControls();
	}

	public void updateControls() {
		Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
		if (konference != null) {
			txfName.setText(konference.getNavn());
			txfAdresse.setText("" + konference.getSted());
			txfPris.setText(String.valueOf(konference.getPris()));
			txfStart.setText(konference.getStartDato().toString());
			txfSlut.setText(konference.getSlutDato().toString());
			StringBuilder deltagers = new StringBuilder();
			for (Tilmelding deltager : konference.getTilmeldinger()) {
				deltagers.append(deltager + "\n");
			}
			txaDeltager.setText(deltagers.toString());
			StringBuilder hoteller = new StringBuilder();
			for (Hotel hotel : konference.getHoteller()) {
				hoteller.append(hotel + "\n");
			}
			txaHoteller.setText(hoteller.toString());
			StringBuilder Udflugtere = new StringBuilder();
			for (Udflugt Udflugt : konference.getUdflugter()) {
				Udflugtere.append(Udflugt + "\n");
			}
			txaUdflugter.setText(Udflugtere.toString());

		} else {
			txfName.clear();
			txfAdresse.clear();
			txfPris.clear();
			txaDeltager.clear();
			txaHoteller.clear();
			txfStart.clear();
			txfSlut.clear();
		}
	}

}
