package gui;

import java.util.ArrayList;
import java.util.Optional;

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
import model.Hotel;
import model.Tillæg;
import model.Tilmelding;

public class HotelPane extends GridPane {
	private TextField txfNavn, txfEnkeltSeng, txfDobbeltSeng, txfSted;
	private TextArea txaTillæg, txaTilmeldte;
	private ListView<Hotel> lvwHoteller;

	public HotelPane() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblHoteller = new Label("Hoteller");
		this.add(lblHoteller, 0, 0);

		lvwHoteller = new ListView<>();
		this.add(lvwHoteller, 0, 1, 1, 5);
		lvwHoteller.setPrefWidth(200);
		lvwHoteller.setPrefHeight(200);
		lvwHoteller.getItems().setAll(this.initAllHotelList());
		ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
		lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblName = new Label("Navn:");
		this.add(lblName, 1, 1);

		txfNavn = new TextField();
		this.add(txfNavn, 2, 1);
		txfNavn.setPrefWidth(200);
		txfNavn.setEditable(false);

		Label lblEnkeltSeng = new Label("Enkelt Seng Pris:");
		this.add(lblEnkeltSeng, 1, 2);

		txfEnkeltSeng = new TextField();
		this.add(txfEnkeltSeng, 2, 2);
		txfEnkeltSeng.setEditable(false);

		Label lblDobbeltSeng = new Label("Dobbelt Seng Pris:");
		this.add(lblDobbeltSeng, 1, 3);

		txfDobbeltSeng = new TextField();
		this.add(txfDobbeltSeng, 2, 3);
		txfDobbeltSeng.setEditable(false);

		Label lblSted = new Label("Sted:");
		this.add(lblSted, 1, 4);

		txfSted = new TextField();
		this.add(txfSted, 2, 4);
		txfSted.setEditable(false);

		Label lblTillæg = new Label("Tillæg:");
		this.add(lblTillæg, 1, 5);
		GridPane.setValignment(lblTillæg, VPos.BASELINE);
		lblTillæg.setPadding(new Insets(4, 0, 4, 0));

		txaTillæg = new TextArea();
		this.add(txaTillæg, 2, 5);
		txaTillæg.setPrefWidth(200);
		txaTillæg.setPrefHeight(100);
		txaTillæg.setEditable(false);

		Label lblTilmeldte = new Label("Tilmeldte:");
		this.add(lblTilmeldte, 1, 6);
		GridPane.setValignment(lblTilmeldte, VPos.BASELINE);
		lblTilmeldte.setPadding(new Insets(4, 0, 4, 0));

		txaTilmeldte = new TextArea();
		this.add(txaTilmeldte, 2, 6);
		txaTilmeldte.setPrefWidth(200);
		txaTilmeldte.setPrefHeight(100);
		txaTilmeldte.setEditable(false);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 6, 1, 1);
		hbxButtons.setAlignment(Pos.BASELINE_LEFT);

		Button btnCreate = new Button("Create");
		hbxButtons.getChildren().add(btnCreate);
		btnCreate.setOnAction(event -> this.createAction());

		Button btnUpdate = new Button("Update");
		hbxButtons.getChildren().add(btnUpdate);
		btnUpdate.setOnAction(event -> this.updateAction());

		Button btnDelete = new Button("Delete");
		hbxButtons.getChildren().add(btnDelete);
		btnDelete.setOnAction(event -> this.deleteAction());

		if (lvwHoteller.getItems().size() > 0) {
			lvwHoteller.getSelectionModel().select(0);
		}
	}

	private ArrayList<Hotel> initAllHotelList() {
		ArrayList<Hotel> list = new ArrayList<>();
		for (Hotel hotel : Controller.getHoteller()) {
			list.add(hotel);
		}
		return list;
	}

	// -------------------------------------------------------------------------

	private void createAction() {
		HotelWindow dia = new HotelWindow("Create Hotel");
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwHoteller.getItems().setAll(this.initAllHotelList());
		this.updateControls();
	}

	private void updateAction() {
		Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
		if (hotel == null) {
			return;
		}

		HotelWindow dia = new HotelWindow("Update Hotel", hotel);
		dia.showAndWait();

		// Wait for the modal dialog to close

		int selectIndex = lvwHoteller.getSelectionModel().getSelectedIndex();
		lvwHoteller.getItems().setAll(this.initAllHotelList());
		lvwHoteller.getSelectionModel().select(selectIndex);
	}

	private void deleteAction() {
		Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
		if (hotel == null) {
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Hotel");
		// alert.setContentText("Are you sure?");
		alert.setHeaderText("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();

		// Wait for the modal dialog to close

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			Controller.deleteHotel(hotel);
			lvwHoteller.getItems().setAll(this.initAllHotelList());
			this.updateControls();
		}

	}

	// -------------------------------------------------------------------------

	private void selectedHotelChanged() {
		this.updateControls();
	}

	public void updateControls() {
		Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
		if (hotel != null) {
			txfNavn.setText(hotel.getNavn());
			txfEnkeltSeng.setText("" + hotel.getPrisEnkeltSeng());
			txfDobbeltSeng.setText("" + hotel.getPrisDobbeltSeng());
			txfSted.setText(hotel.getSted());
			StringBuilder sb = new StringBuilder();
			for (Tillæg tillæg : hotel.getTillæg()) {
				sb.append(tillæg + "\n");
			}
			txaTillæg.setText(sb.toString());
			StringBuilder sb2 = new StringBuilder();
			for (Tilmelding tilmelding : hotel.getTilmeldinger()) {
				if (tilmelding.getDeltager().getLedsager() == null) {
					sb2.append(tilmelding.getDeltager().getNavn() + "\n");
				} else {
					sb2.append(tilmelding.getDeltager().getNavn() + " ("
							+ tilmelding.getDeltager().getLedsager().getNavn() + ")" + "\n");
				}
			}
			txaTilmeldte.setText(sb2.toString());
		} else {
			txfNavn.clear();
			txfEnkeltSeng.clear();
			txfDobbeltSeng.clear();
			txfSted.clear();
			txaTillæg.clear();
			txaTilmeldte.clear();
		}
	}

}
