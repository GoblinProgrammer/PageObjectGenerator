package pageObject;

import element.Element;

import java.util.List;

public class CSharpPageObjectClass extends PageObjectClass implements IPageObjectClass{

    public CSharpPageObjectClass(String className, String pageUrl,boolean generateMethods) {
        super(className, pageUrl,generateMethods);
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
