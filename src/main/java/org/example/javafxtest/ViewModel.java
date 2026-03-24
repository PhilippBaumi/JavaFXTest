package org.example.javafxtest;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewModel {

    private final ObservableList<String> observableList=FXCollections.observableArrayList();
    private final ObjectProperty<LocalDate> dateProperty=new SimpleObjectProperty<>();
    private final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final ReadOnlyStringWrapper formattedDate=new ReadOnlyStringWrapper();

    public ViewModel() {
        formattedDate.bind(
                Bindings.createStringBinding(() ->
                {
                    LocalDate date=dateProperty.get();
                    if(date==null){
                        return "";
                    }
                    return date.format(formatter);
                }, dateProperty)
        );
    }

    public ObservableList<String> getDates() {
        return observableList;
    }

    public ObjectProperty<LocalDate> selectedDateProperty() {
        return this.dateProperty;
    }

    public String formattedDate() {
        return this.formattedDate.get();
    }
}
