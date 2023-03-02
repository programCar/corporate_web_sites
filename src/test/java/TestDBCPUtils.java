import DButils.DBCPUtils;
import org.junit.Test;

public class TestDBCPUtils {

    @Test
    public void testDBCPUtils(){
        System.out.println(DBCPUtils.getConnection());
    }
}
