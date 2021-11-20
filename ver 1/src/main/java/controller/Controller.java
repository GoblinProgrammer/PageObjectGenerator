package controller;

import element.Element;
import element.ElementType;
import element.LocatorType;
import file.FileHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import languange.Languange;
import pageObject.JavaPageObjectClass;
import pageObject.PageObjectClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    //**Source Code
    @FXML
    private TextField urlInput;

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
    private TextField byCssSelectorInput;
    @FXML
    private TextField byXPathInput;

    @FXML
    private ChoiceBox elementLocatorTypeChoiceBox;

    //**List Of elements
    @FXML
    private ListView listOfElements;
    @FXML
    private ChoiceBox languangeChoiceBox;
    @FXML
    private TextField className;

    PageObjectClass pageObjectClass;

    private List<Element> elementsList;

    public Controller(){ }

    @FXML
    private void initialize(){
        elementTypeChoiceBox.setItems(FXCollections.observableArrayList(ElementType.values()));
        elementLocatorTypeChoiceBox.setItems(FXCollections.observableArrayList(LocatorType.values()));
        languangeChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Languange.JAVA,Languange.CSHARP,Languange.KOTLIN)));

        elementTypeChoiceBox.setVisible(false);
        elementTypeChoiceBoxLabel.setVisible(false);

        elementsList = new ArrayList<>();
        loadElementsOnList();
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
        } else {
            locator = byXPathInput.getText();
        }

        elementsList.add(new Element(locatorType,locator,webElementNameInput.getText(),generateMethodsCheckbox.isSelected(), (ElementType) elementTypeChoiceBox.getValue()));
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

    @FXML
    public void savePageObject(){
        if(languangeChoiceBox.equals(Languange.JAVA)){
            pageObjectClass = new JavaPageObjectClass(className.getText(),urlInput.getText());
            pageObjectClass.setPageObjectElements(elementsList);
        }

        if(className.getText() != ""){
            FileHandler.saveToFile(className.getText(),pageObjectClass.printClass());
        } else {
            // TODO: 19.11.2021
        }
    }
}
