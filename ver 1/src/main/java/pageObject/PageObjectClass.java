package pageObject;

import element.Element;

import java.util.List;

public class PageObjectClass implements IPageObjectClass{
    String className;

    List<String> imports;
    String pageUrl;

    List<PageObjectElement> pageObjectElements;

    public PageObjectClass(String className, String pageUrl){
        this.className = className;
        this.pageUrl = pageUrl;
    }

    public void setPageObjectElements(List<Element> pageObjectElements){
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
    public String printClass(){ return null; }
}
