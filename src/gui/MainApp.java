package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void init() {
		Controller.init();
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("KAS - Systemet");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}

	// -------------------------------------------------------------------------

	private void initContent(BorderPane pane) {
		TabPane tabPane = new TabPane();
		this.initTabPane(tabPane);
		pane.setCenter(tabPane);
	}

	private void initTabPane(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tabKonference = new Tab("Konference");
		tabPane.getTabs().add(tabKonference);

		KonferencePane KonferencePane = new KonferencePane();
		tabKonference.setContent(KonferencePane);
		tabKonference.setOnSelectionChanged(event -> KonferencePane.updateControls());

		Tab tabHotel = new Tab("Hoteller");
		tabPane.getTabs().add(tabHotel);

		HotelPane hotelPane = new HotelPane();
		tabHotel.setContent(hotelPane);
		tabHotel.setOnSelectionChanged(event -> hotelPane.updateControls());
		
		Tab test = new Tab("Kudni");
		tabPane.getTabs().add(test);


	}

}
