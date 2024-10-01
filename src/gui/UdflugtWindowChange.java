package gui;

import controller.Controller;
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
import model.Konference;
import model.Udflugt;

public class UdflugtWindowChange extends Stage {
    private Udflugt udflugt;
    private Konference konference;

    public UdflugtWindowChange(String title, Udflugt udflugt, Konference konference) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        this.udflugt= udflugt;
        this.konference = konference;

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public UdflugtWindowChange(String title) {
        this(title, null, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfWage, txfEmployment;
    private ComboBox<Udflugt> cbxUdflugter;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 0, 0);

        txfName = new TextField();
        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblHours = new Label("Hourly Wage");
        pane.add(lblHours, 0, 2);

        txfWage = new TextField();
        pane.add(txfWage, 0, 3);
        
        Label lblEmployment = new Label("Employment Year:");
        pane.add(lblEmployment, 0, 5);
        txfEmployment = new TextField();
        pane.add(txfEmployment, 0, 6);

            

        cbxUdflugter = new ComboBox<>();
        pane.add(cbxUdflugter, 0, 5);
        cbxUdflugter.getItems().addAll(konference.getUdflugter());
        cbxUdflugter.setDisable(true);

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 7);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 7);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
       // btnOK.setOnAction(event -> okAction());

        lblError = new Label();
        pane.add(lblError, 0, 8);
        lblError.setStyle("-fx-text-fill: red");

        initControls();
    }

    private void initControls() {
        if (udflugt != null) {
            txfName.setText(udflugt.getNavn());
            txfWage.setText("" + udflugt.getBeskrivelse());
            txfEmployment.setText(String.valueOf(udflugt.getPris()));
            if (konference.getUdflugter() != null) {
                cbxUdflugter.getSelectionModel().select(0);
                txfEmployment.setText("test");
            }
            else {
            	cbxUdflugter.getSelectionModel().select(0);
            }
        }
        else {
            txfName.clear();
            txfWage.clear();
            cbxUdflugter.getSelectionModel().select(0);
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        hide();
    }

//    private void okAction() {
//        String name = txfName.getText().trim();
//        if (name.length() == 0) {
//            lblError.setText("Name is empty");
//            return;
//        }
//
//        int wage = -1;
//        try {
//            wage = Integer.parseInt(txfWage.getText().trim());
//        }
//        catch (NumberFormatException ex) {
//            // do nothing
//        }
//        if (wage < 0) {
//            lblError.setText("Wage is not a positive number");
//            return;
//        }
//        int year = -1;
//        try {
//            year = Integer.parseInt(txfEmployment.getText().trim());
//        }
//        catch (NumberFormatException ex) {
//            // do nothing
//        }
//
//        boolean companyIsSelected = chbCompany.isSelected();
//        Company newCompany = cbbCompany.getSelectionModel().getSelectedItem();
//
//        // Call controller methods
//        if (employee != null) {
//            Controller.updateEmployee(employee, name, wage);
//            if (companyIsSelected) {
//                Controller.addEmployeeToCompany(employee, newCompany);
//            }
//            else {
//                Controller.removeEmployeeFromCompany(employee, employee.getCompany());
//            }
//        }
//        else {
//            if (companyIsSelected) {
//                Controller.createEmployee(name, wage, year, newCompany);
//            }
//            else {
//                Controller.createEmployee(name, wage, year);
//            }
//        }
//        hide();
//    }

    // -------------------------------------------------------------------------

    private void selectedCompanyChanged(boolean checked) {
    	cbxUdflugter.setDisable(!checked);
    }

}
