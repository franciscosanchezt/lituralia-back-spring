package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


}
