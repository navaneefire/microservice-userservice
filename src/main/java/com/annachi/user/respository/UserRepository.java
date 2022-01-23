package com.annachi.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annachi.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{

}
