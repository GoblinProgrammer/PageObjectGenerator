package element;

public class Element {
    private String locator;
    private String name;

    public Element(String locator,String name,boolean generateMethods,ElementType elementType){
        this.locator = locator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLocator() {
        return locator;
    }
}
