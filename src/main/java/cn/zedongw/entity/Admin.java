package cn.zedongw.entity;

import java.util.Date;

/**
 * @Author ZeDongW
 * @ClassName Admin
 * @Description Admin实体类
 * @Version 1.0
 * @date ：Created in 2019/6/17 0017 6:52
 * @modified By：
 */

public class Admin {
    private int id;
    private String userName;
    private String pwd;
    private int age;
    private Date birth;

    public Admin() {
    }

    public Admin(int id, String userName, String pwd, int age, Date birth) {
        this.id = id;
        this.userName = userName;
        this.pwd = pwd;
        this.age = age;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}
