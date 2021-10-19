package com.rmit.sept.bk_loginservices.repositories;

import com.rmit.sept.bk_loginservices.model.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE USERNAME=:username", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USER WHERE id=:id", nativeQuery = true)
    User getById(@Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM USER WHERE USERNAME=:username", nativeQuery = true)
    void deleteUserByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "UPDATE USER SET USERNAME=:newUsername, FULL_NAME=:fullName, PASSWORD=:password WHERE USERNAME=:oldUsername", nativeQuery = true)
    void editUserDetails(@Param("newUsername") String newUsername, @Param("fullName")String fullName, @Param("password") String password, @Param("oldUsername") String oldUsername);
}
