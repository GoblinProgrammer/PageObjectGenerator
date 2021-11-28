package pageObject;

import element.Element;

import java.util.List;

public abstract class PageObjectClass implements IPageObjectClass{
    String className;
    String pageUrl;

    List<IPageObjectElement> pageObjectElements;

    public PageObjectClass(String className, String pageUrl,List<Element> pageElements){
        this.className = className;
        this.pageUrl = pageUrl;
        setPageObjectElements(pageElements);
    }

    public void setPageObjectElements(List<Element> pageElements) {
    }

    @Override
    public String printElementsLocatorsAttributes(){
        String elementsLocators = "";
        for(IPageObjectElement element : pageObjectElements){
            elementsLocators += element.printElementLocator();
        }
        return elementsLocators + "\n";
    }

    @Override
    public String printElementsFindBys(){
        String elementsFindBys = "";
        for(IPageObjectElement element : pageObjectElements){
            elementsFindBys += element.printElementFindBy();
        }

        return elementsFindBys + "\n";
    }

    @Override
    public String printElementsMethods(){
        String elementsMethods = "";
        for(IPageObjectElement element : pageObjectElements){
            if(element.isMethodNeeded()){
                elementsMethods += element.printElementHandleMethod();
            }
        }
        return elementsMethods;
    }

    @Override
    public String printClassBody(){
        return printElementsLocatorsAttributes() + printElementsFindBys() + printConstructor() + printGet() + printElementsMethods() + "\n";
    }
}
