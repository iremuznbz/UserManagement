package com.assignment.usermanagement.dao;

import com.assignment.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createUser(User user) {
        if (!mongoTemplate.collectionExists(User.class))
            mongoTemplate.createCollection(User.class);
        mongoTemplate.insert(user);
    }

    public List<User> listUsers() {
        return mongoTemplate.findAll(User.class, "users");
    }

    public User findUserById(String id) {
        Query query = new Query();
        query.addCriteria(where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    public void updateUser(User user) {
        User found = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(user.getId())), User.class);
        found.setFirstName(user.getFirstName());
        found.setLastName(user.getLastName());
        found.setPhone(user.getPhone());

        mongoTemplate.save(found);
    }

    public void deleteUser(String id) {
        User found = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id)), User.class);
        mongoTemplate.remove(found);
    }
}
