package InspectTool;

import Dao.Select;
import Perple.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户输入信息的检查类
 */
public class InspectRegisterInfo {

    //用户名为空错误提示信息
    private static String USERNAME_NULL = "对不起，注册的用户名不能为空，请输入";

    //用户名格式不合法错误提示信息
    private static String USERNAME_ILLEGAL = "用户名格式不合法，用户名必须在1~20个字符";

    //用户名已存在错误提示信息
    private static String USERNAME_EXIST = "对不起，此用户名已存在，请重新输入";

    //密码为空错误提示信息
    private static String PASSWORD_NULL = "对不起，注册的密码不能为空，请输入";

    //密码格式不合法错误提示信息
    private static String PASSWORD_ILLEGAL = "密码格式不合法，密码必须在1~20个字符,且必须由大写字母，小写字母，特殊符号组合";

    //身份证为空错误提示信息
    private static String IDCARD_NULL = "对不起，注册的用户身份证不能为空，请输入";

    //身份证已存在错误提示信息
    private static String IDCARD_EXIST = "对不起，此身份证已被注册，请重新输入";

    //身份证格式不合法错误提示信息
    private static String IDCARD_ILLEGAL = "身份证格式不合法";

    //真实姓名已存在错误提示信息
    private static String YOURNAME_NULL = "对不起，注册的用户真实姓名不能为空，请输入";


    /**
     * 检查用户注册信息
     * @param username
     * @param password
     * @param idCard
     * @param YourName
     * @return Map<String,String>
     */
    public static Map<String,String> inspectRegisterInfo(String username, String password, String idCard, String YourName){

        //用户注册信息错误提示收集池子，存放用户提示的错误信息
        HashMap<String,String> error_Pool = new HashMap<>();

        //检查注册用户名是否正确
        if (username == null){
            error_Pool.put("username_null",USERNAME_NULL);
        }else if (username.length() == 0 || username.length() > 20){
            error_Pool.put("username_illegal", USERNAME_ILLEGAL);
        }else {
            //检查用户名是否存在
            if (Select.selectPerson(username,Select.SELECT_Person_SQL) != null) {
                error_Pool.put("username_exist",USERNAME_EXIST);
            }

        }

        //检查注册密码是否正确
        if (password == null){
            error_Pool.put("password_null",PASSWORD_NULL);
        }else if (password.length() == 0 || password.length() > 20){
            error_Pool.put("password_illegal", PASSWORD_ILLEGAL);
        }else {
            //检查密码格式是否正确
            if (!passwordInfoInspect(password)){
                error_Pool.put("password_illegal",PASSWORD_ILLEGAL);
            }
        }

        //检查注册身份证是否正确
        if (idCard == null){
            error_Pool.put("idCard_null",IDCARD_NULL);
        }else if (idCard.length() != 18){
            error_Pool.put("idCard_illegal",IDCARD_ILLEGAL);
        }else {
            //检查身份证是否被注册过
            for (String the_idCard : Select.select_idCard()) {
                if (the_idCard.equals(idCard)){
                    error_Pool.put("idCard_exist",IDCARD_EXIST);
                    break;
                }
            }
        }

        //检查用户注册真实姓名是否存在
        if (YourName == null){
            error_Pool.put("yourName_null",YOURNAME_NULL);
        }

        return error_Pool;

    }

    /**
     * 注册密码格式检查
     * @param password
     * @return boolean
     */
    private static boolean passwordInfoInspect(String password){

        boolean big_char = false;

        boolean small_char = false;

        boolean special_char = false;

        /**
         * 判断用户密码是否由大写字母，小写字母，特殊符号组成
         */
        for (int i = 0; i < password.length(); i++){

            if (!big_char){
                if (password.charAt(i) >= 65 && password.charAt(i) <= 90){
                    big_char = true;
                }
            }

            if (!small_char){
                if (password.charAt(i) >= 97 && password.charAt(i) <= 122){
                    small_char = true;
                }
            }

            if (!special_char){
                if (password.charAt(i) > 122 || password.charAt(i) < 65 || (password.charAt(i) > 90 && password.charAt(i) < 97)){
                    special_char = true;
                }
            }

            if (special_char){
                if (big_char){
                    if (small_char){
                        return true;
                    }
                }
            }

        }

        return false;
    }

}
