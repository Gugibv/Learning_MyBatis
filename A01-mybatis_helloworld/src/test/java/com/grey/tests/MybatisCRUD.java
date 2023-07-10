package com.grey.tests;

import com.grey.mapper.StudentMapper;
import com.grey.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisCRUD {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        // 从 XML 中构建 SqlSessionFactory
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 查询
     */
    @Test
    public void select(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu = mapper.selectStu("101");
        System.out.println(stu.getName());

    }

    /**
     * 添加
     * 注意：要提交事务
     *
     */
    @Test
    public void insert(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu =new Student();
        stu.setId("4");
        stu.setName("Epiphany");
        try {
            Integer result = mapper.insertStu(stu);
            sqlSession.commit();
            System.out.println(result);
        }
        catch (Exception ex){
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }

    /**
     * 更新
     * 注意：要提交事务
     *
     */
    @Test
    public void update(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu =new Student();
        stu.setId("4");
        stu.setName("张三");
        try {
            Integer result = mapper.updateStu(stu);
            sqlSession.commit();
            System.out.println(result);
        }
        catch (Exception ex){
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }


    /**
     * 删除
     * 注意：要提交事务
     *
     */
    @Test
    public void delete(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        try {
            Integer result = mapper.deleteStu("4");
            sqlSession.commit();
            System.out.println(result);
        }
        catch (Exception ex){
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }

}
