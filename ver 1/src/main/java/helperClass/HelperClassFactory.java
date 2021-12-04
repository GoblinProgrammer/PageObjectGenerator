package helperClass;

import java.util.List;

public interface HelperClassFactory {
    String printClass();
    String printClassBody();

    String printHandleInput();
    String printHandleSelect();
    String printHandleAllOthers(List<String> others);
    String printHandleOther(String other);
}
