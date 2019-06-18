package cn.zedongw.myDao;

import cn.zedongw.entity.Students;

import java.util.List;

/**
 * @Author ZeDongW
 * @ClassName StudentsDao
 * @Description 学生类操作实体类
 * @Version 1.0
 * @date ：Created in 2019/6/18 0018 14:59
 * @modified By：
 */

public class StudentsDao extends MyBaseDao{

    /**
     * @Author: ZeDongW
     * @Description: 根据id删除学生
     * @Date: 2019/6/18 0018 15:42
     * @Param: [id]
     * @return: void
     */
    public void delete(int id){
        String sql = "delete from students where id = ?";
        super.update(sql, new Object[]{id});
    }

    /**
     * @Author: ZeDongW
     * @Description: 添加学生
     * @Date: 2019/6/18 0018 15:47
     * @Param: [stu]
     * @return: void
     */
    public void save(Students stu){
        String sql = "insert into students (name, age) values (?, ?)";
        super.update(sql, new Object[]{stu.getName(), stu.getAge()});
    }

    /**
     * @Author: ZeDongW
     * @Description: 查询说有学生
     * @Date: 2019/6/18 0018 15:53
     * @Param: []
     * @return: java.util.List<cn.zedongw.entity.Students>
     */
    public List<Students> getAll(){
        String sql = "select * from students";
        List<Students> list = super.query(sql, null, Students.class);
        return list;
    }

    /**
     * @Author: ZeDongW
     * @Description: 根据id查找学生
     * @Date: 2019/6/18 0018 15:54
     * @Param: [id]
     * @return: cn.zedongw.entity.Students
     */
    public Students findById(int id){
        String sql = "select * from students where id = ?";
        List<Students> list = super.query(sql, new Object[]{id}, Students.class);
        return (list != null && list.size() >0) ? list.get(0) : null;
    }
}
