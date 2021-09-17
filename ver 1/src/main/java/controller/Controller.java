package controller;

import element.Element;
import element.ElementType;
import element.LocatorType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Element> elementsList;

    //*WebView
    @FXML
    private WebView webView;

    //*Right Panel
    //**Source Code
    @FXML
    private TextField urlInput;

    @FXML
    private TextField sourceCodeInput;

    //**ElementDescription
    @FXML
    private TextField webElementNameInput;

    @FXML
    private CheckBox generateMethodsCheckbox;

    @FXML
    private Label elementTypeChoiceBoxLabel;
    @FXML
    private ChoiceBox elementTypeChoiceBox;



    //**Element Locator
    @FXML
    private TextField byIdInput;
    @FXML
    private TextField byClassInput;
    @FXML
    private TextField byCssSelectorInput;
    @FXML
    private TextField byXPathInput;

    @FXML
    private ChoiceBox elementLocatorTypeChoiceBox;

    //**List Of elements
    @FXML
    private ListView listOfElements;

    public Controller(){ }

    @FXML
    private void initialize(){
        elementTypeChoiceBox.setItems(FXCollections.observableArrayList(ElementType.values()));
        elementLocatorTypeChoiceBox.setItems(FXCollections.observableArrayList(LocatorType.values()));

        elementTypeChoiceBox.setVisible(false);
        elementTypeChoiceBoxLabel.setVisible(false);

        elementsList = new ArrayList<>();
        loadElementsOnList();
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

    @FXML
    private void showElementTypeChoiceBox(){
        elementTypeChoiceBox.setVisible(generateMethodsCheckbox.isSelected());
        elementTypeChoiceBoxLabel.setVisible(generateMethodsCheckbox.isSelected());
    }

    @FXML
    private void addElementToList(){
        LocatorType locatorType = (LocatorType) elementLocatorTypeChoiceBox.getValue();
        String locator;
        if(locatorType.equals(LocatorType.Id)){
            locator = byIdInput.getText();
        } else if(locatorType.equals(LocatorType.Class)){
            locator = byClassInput.getText();
        } else if(locatorType.equals(LocatorType.Css)){
            locator = byCssSelectorInput.getText();
        } else {
            locator = byXPathInput.getText();
        }

        elementsList.add(new Element(locator,webElementNameInput.getText(),generateMethodsCheckbox.isSelected(), (ElementType) elementTypeChoiceBox.getValue()));
        clearElementForm();
        loadElementsOnList();
    }

    @FXML
    private void loadElementsOnList(){
        ArrayList<String> elementsNameList = new ArrayList<>();
        for (Element element : elementsList){
            elementsNameList.add(element.getName());
        }
        listOfElements.setItems(FXCollections.observableArrayList(elementsNameList));
    }

    @FXML
    private void clearElementForm(){
        webElementNameInput.clear();
        generateMethodsCheckbox.setSelected(false);
        showElementTypeChoiceBox();
    }
}
