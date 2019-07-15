package org.wlxy.example.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.wlxy.example.common.HttpCode;
import org.wlxy.example.common.MyException;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.UserDao;
import org.wlxy.example.model.User;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public Object getAllUser(PageParam<User> pageParam) {

        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<User> userList=userDao.getAllUser(pageParam.getModel());
        PageInfo<User> userPageInfo = new PageInfo<User>(userList);

        return userPageInfo;
    }

    @CacheEvict(value = "users",key = "#p0")
    @Override
    public boolean removeUserById(int id) {

        return userDao.removeUserById(id)==1;
    }


    @CachePut(value = "users",key = "#p0.id")
    @Override
    public Object addUser(User user) {
//        if(userDao.addUser(user)==1){
//
//        }
        user.setRoleId("general");
        userDao.addUser(user);


        return userDao.getUserById(user.getId());
    }

    @Override
    public boolean updateUser(User user) {

        if(StringUtils.isEmpty(user.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改用户时，id不能为空");
        }

        return userDao.updateUser(user)==1;
    }

    @Cacheable(key = "#p0",value="users")
    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        log.info("走的是数据库查询");
        return userDao.getUserById(id);
    }
}
