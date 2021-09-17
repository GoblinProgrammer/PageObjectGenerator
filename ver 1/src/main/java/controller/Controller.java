package controller;

import element.ElementType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {
    //*WebView
    @FXML
    private WebView webView;

    //*Right Panel
    //**Source Code
    @FXML
    private TextField urlInput;

    @FXML
    private TextField sourceCodeInput;

    @FXML
    private Button loadButton;

    //**ElementDescription
    @FXML
    private TextField webElementNameInput;

    @FXML
    private CheckBox generateMethodsCheckbox;

    @FXML
    private ChoiceBox elementTypeChoiceBox;

    //**Element Locator
    @FXML
    private TextField byIdInput;
    @FXML
    private Button useByIdButton;

    @FXML
    private TextField byClassInput;
    @FXML
    private Button useByClassButton;

    @FXML
    private TextField byCssSelectorInput;
    @FXML
    private Button useByCssButton;

    @FXML
    private TextField byXPathInput;
    @FXML
    private Button useByXPathButton;

    //**List Of elements
    @FXML
    private ListView listOfElements;

    public Controller(){ }

    @FXML
    private void initialize(){
        elementTypeChoiceBox.setItems(FXCollections.observableArrayList(ElementType.values()));
    }

    @FXML
    private void loadUrlIntoWebView(){
        WebEngine webEngine = webView.getEngine();
        if(!urlInput.getText().isBlank()){
            webEngine.load(urlInput.getText());
        } else if(!sourceCodeInput.getText().isBlank()){
            webEngine.load(sourceCodeInput.getText());
        }

    }
}
