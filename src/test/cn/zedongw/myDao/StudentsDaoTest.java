package cn.zedongw.myDao;

import cn.zedongw.entity.Students;
import org.junit.Test;

import java.util.List;

public class StudentsDaoTest {

    StudentsDao sd = new StudentsDao();

    @Test
    public void delete() {
        sd.delete(14);
    }

    @Test
    public void save() {
        Students stu = new Students("张成",20);
        sd.save(stu);
    }

    @Test
    public void getAll() {
        List<Students> list = sd.getAll();
        System.out.println(list);
    }

    @Test
    public void findById() {
        System.out.println(sd.findById(15));
    }
}