package element;

public class Element {
    private String locator;
    private LocatorType locatorType;
    private ElementType elementType;
    private String name;
    private boolean generateMethod;

    public Element(LocatorType locatorType,String locator,String name,boolean generateMethod,ElementType elementType){
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

    public ElementType getElementType(){
        return elementType;
    }

    public boolean getGenerateMethod() { return  generateMethod; }
}
