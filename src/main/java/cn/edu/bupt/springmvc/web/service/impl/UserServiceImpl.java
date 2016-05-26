package cn.edu.bupt.springmvc.web.service.impl;

import cn.edu.bupt.springmvc.core.feature.orm.mybatis.Page;
import cn.edu.bupt.springmvc.core.generic.GenericDao;
import cn.edu.bupt.springmvc.core.generic.GenericServiceImpl;
import cn.edu.bupt.springmvc.web.dao.UserMapper;
import cn.edu.bupt.springmvc.web.model.User;
import cn.edu.bupt.springmvc.web.model.UserExample;
import cn.edu.bupt.springmvc.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 * <p>
 * Created by FirenzesEagle on 2016/4/18 0018.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

    @Override
    public int update(User model) {
        return userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, Long> getDao() {
        return userMapper;
    }

    @Override
    public User selectByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<User> list = userMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public List<User> selectByPage(int pageNo, int pageSize) {
        UserExample example = new UserExample();
        example.createCriteria();
        example.setOrderByClause("id DESC"); // id是表中的用户ID列，这里按照时间降序排列
        Page<User> page = new Page<User>(pageNo, pageSize);
        final List<User> list = userMapper.selectByExampleAndPage(page, example);
        return list;
    }
}
