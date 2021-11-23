package pageObject;

import element.Element;

import java.util.List;

public class PageObjectClass{
    String className;
    String pageUrl;

    List<PageObjectElement> pageObjectElements;

    public PageObjectClass(String className, String pageUrl){
        this.className = className;
        this.pageUrl = pageUrl;
    }
}
