package controller;

import element.Element;
import element.ElementType;
import element.LocatorType;
import file.FileHandler;
import gui.FileDirectory;
import gui.PopUpWindow;
import helperClass.HelperClassCSharp;
import helperClass.HelperClassFactory;
import helperClass.HelperClassJava;
import helperClass.HelperClassKotlin;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import languange.Languange;
import pageObject.*;
import validation.Validator;

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

    @FXML
    private Label webElementTypeInputLabel;
    @FXML
    private TextField webElementTypeInput;

    //**Element Locator
    @FXML
    private TextField locatorInput;

    @FXML
    private ChoiceBox elementLocatorTypeChoiceBox;

    @FXML
    private CheckBox generateHelperClassCheckbox;

    //**List Of elements
    @FXML
    private ListView listOfElements;
    @FXML
    private ChoiceBox languangeChoiceBox;
    @FXML
    private TextField classNameInput;

    @FXML
    private TextArea previewTextArea;

    private List<Element> elementsList;
    private List<String> otherElementTypes;

    private IPageObjectClass pageObjectClass;
    private HelperClassFactory helperClass;
    private String fileExtention;

    public Controller(){ }

    @FXML
    private void initialize(){
        elementTypeChoiceBox.setItems(FXCollections.observableArrayList(ElementType.values()));
        elementLocatorTypeChoiceBox.setItems(FXCollections.observableArrayList(LocatorType.values()));
        languangeChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Languange.JAVA,Languange.CSHARP,Languange.KOTLIN)));

        //languangeChoiceBox.setValue(Languange.JAVA);

        elementTypeChoiceBox.setManaged(false);
        elementTypeChoiceBoxLabel.setManaged(false);

        webElementTypeInput.setManaged(false);
        webElementTypeInputLabel.setManaged(false);

        elementsList = new ArrayList<>();
        otherElementTypes = new ArrayList<>();
        loadElementsOnList();
    }

    @FXML
    private void showElementTypeChoiceBox(){
        elementTypeChoiceBox.setManaged(generateMethodCheckbox.isSelected());
        elementTypeChoiceBox.setVisible(generateMethodCheckbox.isSelected());
        elementTypeChoiceBoxLabel.setManaged(generateMethodCheckbox.isSelected());
        elementTypeChoiceBoxLabel.setVisible(generateMethodCheckbox.isSelected());
        if(!generateMethodCheckbox.isSelected()){
            webElementTypeInput.setManaged(false);
            webElementTypeInput.setVisible(false);
            webElementTypeInputLabel.setManaged(false);
            webElementTypeInputLabel.setVisible(false);
        }
    }

    @FXML
    private void addElementToList(){
        if(validatePageObjectElementForm()){
            LocatorType locatorType = (LocatorType) elementLocatorTypeChoiceBox.getValue();
            elementsList.add(new Element(locatorType,locatorInput.getText(),webElementNameInput.getText(), generateMethodCheckbox.isSelected(), getElementTypeFromForm()));
            if(elementTypeChoiceBox.getValue().equals(ElementType.Other)){
                otherElementTypes.add(webElementTypeInput.getText());
            }
            clearElementForm();
            loadElementsOnList();
            refreshPageObjectPreview();
        }
    }
    private String getElementTypeFromForm(){
        if(!isElementTypeEqualsOther()){
            return elementTypeChoiceBox.getValue().toString();
        } else {
            return webElementTypeInput.getText();
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
        webElementTypeInput.clear();
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
        generateHelperClassCheckbox.setSelected(false);
        loadElementsOnList();
    }

    @FXML
    public void savePageObject(){
        String directory;
        if(validatePageObjectForm()){
            updatePageObjectClass();
            directory = FileDirectory.getDirectory();
            FileHandler.saveToFile( directory + "\\" + classNameInput.getText(), pageObjectClass.printClass(), fileExtention);
            if(generateHelperClassCheckbox.isSelected()){
                FileHandler.saveToFile(directory + "\\" + "SeleniumHelper", helperClass.printClass(),fileExtention);
            }
            clearForm();

            PopUpWindow.display("INFO: PageObject Generated successfully");
        }
    }

    private boolean validatePageObjectElementForm(){
        if(generateMethodCheckbox.isSelected()){
            return Validator.validateForm(webElementNameInput,elementTypeChoiceBox,locatorInput,elementLocatorTypeChoiceBox);
        } else {
            return Validator.validateForm(webElementNameInput,locatorInput,elementLocatorTypeChoiceBox);
        }
    }

    private boolean validatePageObjectForm(){
        return Validator.validateForm(classNameInput,urlInput,listOfElements);
    }

    public void updatePageObjectClass(){
        if(languangeChoiceBox.getValue().equals(Languange.JAVA)) {
            pageObjectClass = new JavaPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
            helperClass = new HelperClassJava(otherElementTypes);
            fileExtention = "java";
        } else if(languangeChoiceBox.getValue().equals(Languange.CSHARP)){
            pageObjectClass = new CSharpPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
            helperClass = new HelperClassCSharp(otherElementTypes);
            fileExtention = "cs";
        } else if(languangeChoiceBox.getValue().equals(Languange.KOTLIN)){
            pageObjectClass = new KotlinPageObjectClass(classNameInput.getText(),urlInput.getText(),elementsList);
            helperClass = new HelperClassKotlin(otherElementTypes);
            fileExtention = "kt";
        }

        pageObjectClass.setPageObjectElements(elementsList);
    }

    @FXML
    public void refreshPageObjectPreview(){
        updatePageObjectClass();
        previewTextArea.setText(pageObjectClass.printClass());
    }

    @FXML
    public void showOtherInput(){
        if(elementTypeChoiceBox.getValue() != null){
            webElementTypeInputLabel.setManaged(isElementTypeEqualsOther());
            webElementTypeInputLabel.setVisible(isElementTypeEqualsOther());
            webElementTypeInput.setManaged(isElementTypeEqualsOther());
            webElementTypeInput.setVisible(isElementTypeEqualsOther());
        }
    }
    private boolean isElementTypeEqualsOther(){
        return elementTypeChoiceBox.getValue().equals(ElementType.Other);
    }

    private void generateHelperClass(boolean generate){
        if(generate){

        }
    }
}
