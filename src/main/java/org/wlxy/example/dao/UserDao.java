package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.User;

import java.util.List;

//@Mapper
public interface UserDao {


//    @Select("select * from user")
    List<User> getAllUser(User u);

//    @Delete("delete from user where id = #{id}")
    int removeUserById(int id);

//    @Insert("insert into user (userName,password,roleId)values(#{userName},#{password},#{roleId})")
    int addUser(User user);

//    @Update("update user set userName=#{userName},password=#{password} where id=#{id}")
    int updateUser(User user);

    @Select("select * from user where id =#{id}")
    User getUserById(int id);
}
