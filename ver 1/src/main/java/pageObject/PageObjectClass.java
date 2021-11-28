package pageObject;

import element.Element;

import java.util.List;

public abstract class PageObjectClass{
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
}
