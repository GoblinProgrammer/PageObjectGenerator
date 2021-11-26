package pageObject;

import element.Element;

import java.util.List;

public class PageObjectClass implements IPageObjectClass{
    String className;
    String pageUrl;
    boolean generateMethods;

    List<PageObjectElement> pageObjectElements;

    public PageObjectClass(String className, String pageUrl,boolean generateMethods){
        this.className = className;
        this.pageUrl = pageUrl;
        this.generateMethods = generateMethods; // TODO: 26.11.2021 decide where generate methods should be - per element or per class 
    }

    @Override
    public String printImports() {
        return null;
    }

    @Override
    public String printConstructor() {
        return null;
    }

    @Override
    public String printGet() {
        return null;
    }

    @Override
    public String printElementsLocatorsAttributes() {
        return null;
    }

    @Override
    public String printElementsFindBys() {
        return null;
    }

    @Override
    public String printElementsMethods(){
        return null;
    }

    @Override
    public String printMethods() {
        return null;
    }

    @Override
    public String printClassBody() {
        return null;
    }

    @Override
    public String printClass() {
        return null;
    }

    @Override
    public void setPageObjectElements(List<Element> pageElements) {

    }
}
