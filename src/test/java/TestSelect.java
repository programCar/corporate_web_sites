import Dao.Select;
import Perple.User;
import org.junit.Test;

import java.util.List;

public class TestSelect {
    @Test
    public void selectAll(){

        List<User> users = Select.selectAll(Select.SELECT_ALL_USER_SQL);


        for (User user : users) {
            System.out.println("username : " + user.getUsername() + " " + "password : " + user.getPassword());
        }

    }

    @Test
    public void selectPerson(){
//        User user = Select.selectPerson("张三",Select.SELECT_Person_SQL);
//
//        System.out.println("username : " + user.getUsername() + " " + "password : " + user.getPassword());
//
//        System.out.println(Select.select_idCard());

        User user = Select.selectPerson("李四", Select.SELECT_Person_SQL);

        System.out.println(user.getUsername());
    }
}
