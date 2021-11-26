package pageObject;

import element.Element;

import java.util.List;

public interface IPageObjectClass {
    String printImports();
    String printConstructor();
    String printGet();
    String printElementsLocatorsAttributes();
    String printElementsFindBys();
    String printElementsMethods();
    String printMethods();
    String printClassBody();
    String printClass();
    void setPageObjectElements(List<Element> pageElements);
}
