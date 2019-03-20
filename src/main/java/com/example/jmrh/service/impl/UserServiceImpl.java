package com.example.jmrh.service.impl;

import com.example.jmrh.dao.UserReposity;
import com.example.jmrh.entity.User;
import com.example.jmrh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:58
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReposity userReposity;


    @Override
    public User queryUserByUserName(String userName) throws Exception {

        return userReposity.queryUserByUserName(userName);
    }

    @Override
    public User save(User user) throws Exception {
        return userReposity.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserById(Long id) throws Exception {
        userReposity.deleteById(id);
    }

    @Override
    public Page<User> queryUsers(User user, Pageable pageable) throws Exception {

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<>();

                if (!ObjectUtils.isEmpty(user.getUserName())){
                    list.add(criteriaBuilder.like(root.get("userName"),"%"+user.getUserName()+"%"));
                }

                if (!ObjectUtils.isEmpty(user.getCity())){
                    list.add(criteriaBuilder.like(root.get("city"),"%"+user.getCity()+"%"));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };

        return userReposity.findAll(specification,pageable);
    }

    @Override
    public User queryUserById(Long id) throws Exception {
        return userReposity.queryUserById(id);
    }

}
