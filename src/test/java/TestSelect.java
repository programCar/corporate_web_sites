import Dao.Select;
import Perple.User;
import org.junit.Test;

import java.util.List;

public class TestSelect {
    @Test
    public void selectAll(){

        List<User> users = Select.selectAll();


        for (User user : users) {
            System.out.println("username : " + user.getUsername() + " " + "password : " + user.getPassword());
        }

    }

    @Test
    public void selectPerson(){
        User user = Select.selectPerson("张三");

        System.out.println("username : " + user.getUsername() + " " + "password : " + user.getPassword());
    }
}
