package org.example.javafxtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private ListView<String> dateListView;

    @FXML
    private DatePicker datePickerDate;

    private ViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.viewModel=new ViewModel();
        this.dateListView.setItems(viewModel.getDates());
        viewModel.selectedDateProperty().bind(datePickerDate.valueProperty());

        btnAdd.setOnAction(e -> {
                String formattedDate= viewModel.formattedDate();
                if(formattedDate.isEmpty()) {
                    showAlert(Alert.AlertType.INFORMATION, "Information", "Information", "Kein Datum gewählt!");
                    return;
                }
                if(viewModel.getDates().contains(formattedDate)) {
                    showAlert(Alert.AlertType.INFORMATION, "Information", "Information", "Datum existiert bereits!");
                    return;
                }
                viewModel.getDates().add(formattedDate);
        });

        dateListView.setOnMouseClicked(event -> {
            String date=dateListView.getSelectionModel().getSelectedItem();
            if(date!=null) {
                showAlert(Alert.AlertType.INFORMATION, "Datum", "Gewähltes Datum", date);
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String text) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
