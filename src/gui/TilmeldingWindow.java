package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import controller.Controller;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.Deltager;
import model.Hotel;
import model.Konference;
import model.Ledsager;
import model.Tilmelding;
import model.Udflugt;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilmeldingWindow extends Stage {
	private Konference konference;

	public TilmeldingWindow(String title, Konference konference) {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);

		this.konference = konference;

		setTitle(title);
		GridPane pane = new GridPane();
		initContent(pane);

		Scene scene = new Scene(pane);
		setScene(scene);
	}

	public TilmeldingWindow(String title) {
		this(title, null);
	}

	private TextField txfUdflugtDato, txfUdflugtPris, txfName, txfAdresse, txfBy, txfTelefon, txfAnkomst, txfAfrejse,
			txfFirmaNavn, txfFirmaTlf, txfLedsager, txfHotelPris;
	private ListView<Udflugt> lvwUdflugter;
	private CheckBox chbAdmin, chbForedragsholder, chbLedsager, chbHotel, chbFirma;
	private ComboBox<Object> cbbHoteller;

	private Label lblError;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(20));
		pane.setHgap(20);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblComp = new Label("Deltagerinformation");
		pane.add(lblComp, 0, 0);
		Label lblLedsager = new Label("Program for Ledsagere");
		pane.add(lblLedsager, 0, 6);
		Label lblHotel = new Label("Valg af hotel");
		pane.add(lblHotel, 0, 12);

		// Deltager

		Label lblName = new Label("Navn*:");
		pane.add(lblName, 0, 1);
		txfName = new TextField();
		pane.add(txfName, 1, 1);
		txfName.setPrefWidth(200);
		txfName.setEditable(true);

		Label lblAdresse = new Label("Adresse*:");
		pane.add(lblAdresse, 0, 2);
		txfAdresse = new TextField();
		pane.add(txfAdresse, 1, 2);
		txfAdresse.setPrefWidth(200);
		txfAdresse.setEditable(true);

		Label lblBy = new Label("By/Land*:");
		pane.add(lblBy, 0, 3);
		txfBy = new TextField();
		pane.add(txfBy, 1, 3);
		txfBy.setPrefWidth(200);
		txfBy.setEditable(true);

		Label lblTelefon = new Label("Tlf.nr*:");
		pane.add(lblTelefon, 2, 1);
		txfTelefon = new TextField();
		pane.add(txfTelefon, 3, 1);
		txfTelefon.setPrefWidth(200);
		txfTelefon.setEditable(true);

		Label lblAnkomst = new Label("Ankomstdato*: \nyyyy/mm/dd");
		pane.add(lblAnkomst, 2, 2);
		txfAnkomst = new TextField();
		pane.add(txfAnkomst, 3, 2);
		txfAnkomst.setPrefWidth(200);
		txfAnkomst.setEditable(true);

		Label lblAfrejse = new Label("Afrejsedato*: \nyyyy/mm/dd");
		pane.add(lblAfrejse, 2, 3);
		txfAfrejse = new TextField();
		pane.add(txfAfrejse, 3, 3);
		txfAfrejse.setPrefWidth(200);
		txfAfrejse.setEditable(true);

		chbFirma = new CheckBox("Firma");
		pane.add(chbFirma, 1, 4);
		ChangeListener<Boolean> listener2 = (ov, oldValue, newValue) -> selectedFirmaChanged(newValue);
		chbFirma.selectedProperty().addListener(listener2);

		Label lblFirmaNavn = new Label("Firmanavn:");
		pane.add(lblFirmaNavn, 0, 5);
		txfFirmaNavn = new TextField();
		pane.add(txfFirmaNavn, 1, 5);
		txfFirmaNavn.setPrefWidth(200);
		txfFirmaNavn.setEditable(true);
		txfFirmaNavn.setDisable(true);

		Label lblFirmaTlf = new Label("Firma tlf.nr:");
		pane.add(lblFirmaTlf, 2, 5);
		txfFirmaTlf = new TextField();
		pane.add(txfFirmaTlf, 3, 5);
		txfFirmaTlf.setPrefWidth(200);
		txfFirmaTlf.setEditable(true);
		txfFirmaTlf.setDisable(true);

		chbForedragsholder = new CheckBox("Foredragsholder");
		pane.add(chbForedragsholder, 0, 4);

		chbAdmin = new CheckBox("Admin");
		pane.add(chbAdmin, 2, 4);

		// Ledsager
		chbLedsager = new CheckBox("Ledsager");
		pane.add(chbLedsager, 1, 6);
		ChangeListener<Boolean> listener3 = (ov, oldValue, newValue) -> selectedLedsagerChanged(newValue);
		chbLedsager.selectedProperty().addListener(listener3);

		Label lblLedsager2 = new Label("Ledsagers Navn:");
		pane.add(lblLedsager2, 2, 7);
		txfLedsager = new TextField();
		pane.add(txfLedsager, 3, 7);
		txfLedsager.setPrefWidth(200);
		txfLedsager.setEditable(true);
		txfLedsager.setDisable(true);

		lvwUdflugter = new ListView<>();
		pane.add(lvwUdflugter, 0, 7, 1, 5);
		lvwUdflugter.setPrefWidth(200);
		lvwUdflugter.setPrefHeight(200);
		lvwUdflugter.getItems().setAll(konference.getUdflugter());
		ChangeListener<Udflugt> listener = (ov, oldEmployee, newEmployee) -> this.updateControls();
		lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);
		lvwUdflugter.setDisable(true);

		Label lblUdflugtDato = new Label("Dato på udflugt:");
		pane.add(lblUdflugtDato, 2, 8);
		txfUdflugtDato = new TextField();
		pane.add(txfUdflugtDato, 3, 8);
		txfUdflugtDato.setPrefWidth(200);
		txfUdflugtDato.setEditable(false);
		txfUdflugtDato.setDisable(true);

		Label lblUdflugtPris = new Label("Pris på udflugt:");
		pane.add(lblUdflugtPris, 2, 9);
		txfUdflugtPris = new TextField();
		pane.add(txfUdflugtPris, 3, 9);
		txfUdflugtPris.setPrefWidth(200);
		txfUdflugtPris.setEditable(false);
		txfUdflugtPris.setDisable(true);

		// hotel
		chbHotel = new CheckBox("Hotel");
		pane.add(chbHotel, 1, 12);
		ChangeListener<Boolean> listener4 = (ov, oldValue, newValue) -> selectedHotelChanged(newValue);
		chbHotel.selectedProperty().addListener(listener4);

		Label lblHotelPris = new Label("Hotel priser: dobbelt/enkelt");
		pane.add(lblHotelPris, 2, 13);
		txfHotelPris = new TextField();
		pane.add(txfHotelPris, 3, 13);
		txfHotelPris.setPrefWidth(200);
		txfHotelPris.setEditable(false);
		txfHotelPris.setDisable(true);

		cbbHoteller = new ComboBox<>();
		pane.add(cbbHoteller, 0, 13);
		cbbHoteller.getItems().addAll(Controller.getHoteller());
		cbbHoteller.setDisable(true);
		ChangeListener<? super Object> listener5 = (ov, oldValue, newValue) -> updateControls();
		cbbHoteller.getSelectionModel().selectedItemProperty().addListener(listener5);

		HBox hbxButtons = new HBox(40);
		pane.add(hbxButtons, 0, 14, 3, 1);
		hbxButtons.setPadding(new Insets(10, 0, 0, 0));
		hbxButtons.setAlignment(Pos.BASELINE_CENTER);

		Button btnOpret = new Button("Opret");
		hbxButtons.getChildren().add(btnOpret);
		btnOpret.setOnAction(event -> this.createAction());

		Button btnCancel = new Button("Cancel");
		hbxButtons.getChildren().add(btnCancel);
		btnCancel.setOnAction(event -> this.cancelAction());

		lblError = new Label();
		pane.add(lblError, 1, 15);
		lblError.setStyle("-fx-text-fill: red");

	}

	// -------------------------------------------------------------------------

	private void createAction() {
		Deltager deltager = null;
		Tilmelding tilmeld = null;
		String name = txfName.getText().trim();
		String adresse = txfAdresse.getText().trim();
		String by = txfBy.getText().trim();
		String tlf = txfTelefon.getText().trim();
		String start = txfAnkomst.getText().trim();
		String slut = txfAfrejse.getText().trim();
		Udflugt udflugter = lvwUdflugter.getSelectionModel().getSelectedItem();
		boolean foredrag = chbForedragsholder.isSelected();
		boolean admin = chbAdmin.isSelected();
		if (name.length() == 0 || adresse.length() == 0 || by.length() == 0 || tlf.length() == 0 || slut.length() == 0
				|| start.length() == 0) {
			lblError.setText("Du skal udfylde det med stjerne(\"*\") på");
		} else {

			LocalDate ankomst = LocalDate.parse(start);
			LocalDate afrejse = LocalDate.parse(slut);
			deltager = Controller.createDeltager(name, tlf, adresse, by, admin);
			System.out.println("Deltager er success");
			tilmeld = Controller.createTilmelding(konference, deltager, foredrag, ankomst, afrejse);
			System.out.println("Tilmeldling er success");
		}
		if (chbLedsager.isSelected() && deltager != null) {
			String ledsager = txfLedsager.getText().trim();
			Ledsager ledsag = deltager.createLedsager(ledsager);
			System.out.println("Ledsager er success");
			ledsag.addUdflugt(udflugter);
			System.out.println("udflugter er success");

		}
		if (chbFirma.isSelected() && deltager != null) {
			String firmaNavn = txfFirmaNavn.getText().trim();
			String firmaTlf = txfFirmaTlf.getText().trim();
			deltager.setFirma(firmaNavn);
			System.out.println("Firma er success");
		}
		if (chbHotel.isSelected() && tilmeld != null) {
			Hotel hotel = (Hotel) cbbHoteller.getSelectionModel().getSelectedItem();
			tilmeld.setHotel(hotel);
			System.out.println("Hotel er success");
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Kvittering");
		alert.setContentText(String.valueOf(tilmeld.samletPris()));
		alert.setHeaderText("Samlet Pris");
		Optional<ButtonType> result = alert.showAndWait(); 
		hide();
		updateControls();

	}

	private void cancelAction() {
		hide();
	}

	// -------------------------------------------------------------------------

	public void updateControls() {
		boolean ledsager = chbLedsager.isSelected();
		boolean hotel = chbHotel.isSelected();
		if (hotel) {
			Hotel hoteller = (Hotel) cbbHoteller.getSelectionModel().getSelectedItem();
			if (hoteller != null) {
				txfHotelPris.setText(String.valueOf(hoteller.getPrisDobbeltSeng()) + "/"
						+ String.valueOf(hoteller.getPrisEnkeltSeng()));
			}
		}
		if (ledsager) {
			Udflugt udflugter = lvwUdflugter.getSelectionModel().getSelectedItem();
			if (udflugter != null) {
				txfUdflugtDato.setText(udflugter.getStartDato().toString());
				txfUdflugtPris.setText(String.valueOf(udflugter.getPris()));
			}
		}

	}

	private void selectedFirmaChanged(boolean checked) {
		txfFirmaNavn.setDisable(!checked);
		txfFirmaTlf.setDisable(!checked);
		updateControls();

	}

	private void selectedLedsagerChanged(boolean checked) {
		txfLedsager.setDisable(!checked);
		txfUdflugtDato.setDisable(!checked);
		txfUdflugtPris.setDisable(!checked);
		lvwUdflugter.setDisable(!checked);
		updateControls();

	}

	private void selectedHotelChanged(boolean checked) {
		txfHotelPris.setDisable(!checked);
		cbbHoteller.setDisable(!checked);
		updateControls();
	}

}
