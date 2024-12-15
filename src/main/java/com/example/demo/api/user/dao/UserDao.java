package com.example.demo.api.user.dao;

import com.example.demo.api.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//not done yet
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate template;

    public void addUser(User user){
        String sqlCommand = "INSERT INTO user VALUES (:email,:name,:password)";
        template.update(sqlCommand,user.getEmail(),user.getName(), user.getPassword());
    }

    public User getUser(String email){
        RowMapper<User> mapper = (rs,row) -> {
                User user= new User();
                user.setEmail(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));

                return user;
        };
        String sqlCommand = "SELECT * FROM user WHERE email = :email";
        return template.queryForObject(sqlCommand,mapper,email);
    }

    public void fixUser(String email ,User user){
        String sqlCommand = "UPDATE user SET email = :newEmail, name = :newName WHERE email = :oldEmail";
        template.update(sqlCommand, user.getEmail(),user.getName(),email);
    }

    public void deleteUser(String email){
        String sqlCommand = "DELETE FROM user WHERE email = :email";
        template.update(sqlCommand, email);
    }
}
