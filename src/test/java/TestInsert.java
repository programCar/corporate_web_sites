import Dao.Insert;
import org.junit.Test;

public class TestInsert {

    @Test
    public void testInsert(){
        System.out.println(Insert.insert("李四", "123456"));
    }
}
