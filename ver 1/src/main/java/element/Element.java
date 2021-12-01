package element;

public class Element {
    private final String locator;
    private final LocatorType locatorType;
    private final String elementType;
    private final String name;
    private final boolean generateMethod;

    public Element(LocatorType locatorType,String locator,String name,boolean generateMethod,String elementType){
        this.locatorType = locatorType;
        this.locator = locator;
        this.name = name;
        this.elementType = elementType;
        this.generateMethod = generateMethod;
    }

    public String getName() {
        return name;
    }

    public String getLocator() {
        return locator;
    }

    public LocatorType getLocatorType(){
        return locatorType;
    }

    public String getElementType(){
        return elementType;
    }

    public boolean getGenerateMethod() { return  generateMethod; }
}
