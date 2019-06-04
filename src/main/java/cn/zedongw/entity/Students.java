package cn.zedongw.entity;

/**
 * @Author ZeDongW
 * @ClassName Students
 * @Description 学生实体类
 * @Version 1.0
 * @date ：Created in 2019/6/5 0005 6:49
 * @modified By：
 */

public class Students {
    private int id;
    private String name;
    private int age;

    public Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
