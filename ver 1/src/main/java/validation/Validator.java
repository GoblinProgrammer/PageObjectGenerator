package validation;

import gui.PopUpWindow;
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


    public static boolean validateForm(Control... controls){
        int validElementsCount = 0;
        for(Control control : controls){
            if(control instanceof ChoiceBox){
                validElementsCount += (givenControlHasValue((ChoiceBox) control)) ? 1 : 0;
            } else if(control instanceof TextField){
                validElementsCount += (givenControlHasValue((TextField) control)) ? 1 : 0;
            } else if(control instanceof ListView){
                validElementsCount += (givenControlHasValue((ListView) control)) ? 1 : 0;
            }
        }
        if(validElementsCount == controls.length){
            return true;
        } else {
            PopUpWindow.display("INFO: Some fields has no data");
            return false;
        }
    }

}
