package Perple;

/**
 * 用户类，存放用户的个人基础信息
 */
public class User {

    private String username;

    private String password;

    private String idCard;

    private String yourName;

    private String birthday;

    public User(String username,String idCard, String yourName, String birthday) {
        this.username = username;
        this.idCard = idCard;
        this.yourName = yourName;
        this.birthday = birthday;
    }

    public User(String username, String password, String idCard, String yourName, String birthday) {
        this.username = username;
        this.password = password;
        this.idCard = idCard;
        this.yourName = yourName;
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
