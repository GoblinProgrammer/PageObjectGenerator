package pageObject;

public class KotlinPageObjectClass extends PageObjectClass implements IPageObjectClass{
    public KotlinPageObjectClass(String className, String pageUrl,boolean generateMethods) {
        super(className, pageUrl,generateMethods);
    }
}
