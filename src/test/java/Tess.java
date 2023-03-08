import InspectTool.InspectRegisterInfo;
import org.junit.Test;

import java.util.Map;

public class Tess {
    @Test
    public void test(){

        Map<String, String> stringStringMap = InspectRegisterInfo.inspectRegisterInfo("李四", null, null, null);

        System.out.println(stringStringMap);
    }

}
