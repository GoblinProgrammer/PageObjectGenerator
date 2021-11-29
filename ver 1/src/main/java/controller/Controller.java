package controller;

import element.Element;
import element.ElementType;
import element.LocatorType;
import file.FileHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import languange.Languange;
import pageObject.*;

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
    private CheckBox generateMethodCheckbox;

    @FXML
    private Label elementTypeChoiceBoxLabel;
    @FXML
    private ChoiceBox elementTypeChoiceBox;

    //**Element Locator
    @FXML
    private TextField locatorInput;

    @FXML
    private ChoiceBox elementLocatorTypeChoiceBox;

    //**List Of elements
    @FXML
    private ListView listOfElements;
    @FXML
    private ChoiceBox languangeChoiceBox;
    @FXML
    private TextField classNameInput;

    private List<Element> elementsList;

    private IPageObjectClass pageObjectClass;
    private String fileExtention;

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
        elementTypeChoiceBox.setVisible(generateMethodCheckbox.isSelected());
        elementTypeChoiceBoxLabel.setVisible(generateMethodCheckbox.isSelected());
    }

    @FXML
    private void addElementToList(){
        if(validatePageObjectElementForm()){
            LocatorType locatorType = (LocatorType) elementLocatorTypeChoiceBox.getValue();
            elementsList.add(new Element(locatorType,locatorInput.getText(),webElementNameInput.getText(), generateMethodCheckbox.isSelected(), (ElementType) elementTypeChoiceBox.getValue()));
            clearElementForm();
            loadElementsOnList();
        }
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
        locatorInput.clear();
        generateMethodCheckbox.setSelected(false);
        elementTypeChoiceBox.setValue(null);
        elementLocatorTypeChoiceBox.setValue(null);
        showElementTypeChoiceBox();
    }

    @FXML
    private void clearForm(){
        clearElementForm();
        classNameInput.clear();
        urlInput.clear();
        elementsList = new ArrayList<>();
        loadElementsOnList();
    }

    @FXML
    public void savePageObject(){
        if(validatePageObjectForm()){
            if(languangeChoiceBox.getValue().equals(Languange.JAVA)) {
                pageObjectClass = new JavaPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
                fileExtention = "java";
            } else if(languangeChoiceBox.getValue().equals(Languange.CSHARP)){
                pageObjectClass = new CSharpPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
                fileExtention = "cs";
            } else if(languangeChoiceBox.getValue().equals(Languange.KOTLIN)){
                pageObjectClass = new KotlinPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
                fileExtention = "kt";
            }

            System.out.println(pageObjectClass.printClass());
            pageObjectClass.setPageObjectElements(elementsList);
            FileHandler.saveToFile(classNameInput.getText(), pageObjectClass.printClass(), fileExtention);
            clearForm();
        }
    }

    public boolean validatePageObjectElementForm(){
        if(generateMethodCheckbox.isSelected()){
            if(Validator.givenControlHasValue(webElementNameInput) && Validator.givenControlHasValue(elementTypeChoiceBox) && Validator.givenControlHasValue(locatorInput) && Validator.givenControlHasValue(elementLocatorTypeChoiceBox)){
                return true;
            } else {
                // TODO: 29.11.2021 show pop up here
                System.out.println("INFO: Some fields has no data");
                return false;
            }
        } else {
            if(Validator.givenControlHasValue(webElementNameInput) && Validator.givenControlHasValue(locatorInput) && Validator.givenControlHasValue(elementLocatorTypeChoiceBox)){
                return true;
            } else {
                // TODO: 29.11.2021 show pop up here
                System.out.println("INFO: Some fields has no data");
                return false;
            }
        }
    }

    public boolean validatePageObjectForm(){
        if(Validator.givenControlHasValue(classNameInput) && Validator.givenControlHasValue(urlInput) && Validator.givenControlHasValue(listOfElements) && Validator.givenControlHasValue(languangeChoiceBox)){
            return true;
        }
        // TODO: 29.11.2021 show pop up here
        System.out.println("INFO: Some fields has no data");
        return false;
    }
}
