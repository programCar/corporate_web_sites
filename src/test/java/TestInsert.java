import Dao.Insert;
import org.junit.Test;

public class TestInsert {

    @Test
    public void testInsert() {
        System.out.println(Insert.insert("李四", "123456", "44142420000320516", "张三"));
//
//        String idCard = "441424200003210516";
//
//        System.out.println(idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14));
    }
}
