package controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public abstract class Validator {

    public static boolean givenControlHasValue(TextField textField){
        return textField.getText() != null && textField.getText().length() != 0;
    }

    public static boolean givenControlHasValue(ChoiceBox choiceBox){
        return choiceBox.getValue() != null;
    }

    public static boolean givenControlHasValue(ListView listView){
        return listView.getItems().size() > 0;
    }
}
