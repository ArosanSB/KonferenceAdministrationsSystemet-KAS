package gui;

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
import model.Tillæg;

public class TillægWindow extends Stage {
	public Tillæg tillæg;

	public TillægWindow (String title, Tillæg tillæg) {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);

		this.tillæg = tillæg;

		setTitle(title);
		GridPane pane = new GridPane();
		initContent(pane);

		Scene scene = new Scene(pane);
		setScene(scene);
	}

	public TillægWindow(String title) {
		this(title, null);
	}

	private TextField txfNavn, txfPris;
	private Label lblError;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblNavn = new Label("Navn:");
		pane.add(lblNavn, 0, 0);

		txfNavn = new TextField();
		pane.add(txfNavn, 0, 1);
		txfNavn.setPrefWidth(400);

		Label lblPris = new Label("Pris:");
		pane.add(lblPris, 0, 2);

		txfPris = new TextField();
		pane.add(txfPris, 0, 3);

		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 0, 4);
		GridPane.setHalignment(btnCancel, HPos.LEFT);
		btnCancel.setOnAction(event -> cancelAction());

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 4);
		GridPane.setHalignment(btnOK, HPos.RIGHT);
		btnOK.setOnAction(event -> okAction());

		lblError = new Label();
		pane.add(lblError, 0, 5);
		lblError.setStyle("-fx-text-fill: red");

	}

	private void cancelAction() {
		hide();
	}

	private void okAction() {
		String navn = txfNavn.getText().trim();
		if (navn.length() == 0) {
			lblError.setText("Name is empty");
			return;
		}

		double pris = -1;
		try {
			pris = Double.parseDouble(txfPris.getText().trim());
		} catch (NumberFormatException ex) {
			// do nothing
		}
		if (pris < 0) {
			lblError.setText("Pris is not a positive number");
			return;
		}

		tillæg = new Tillæg(navn, pris);
		hide();
	}
}
