package com.aiti.preauthorizer.repository;


import com.aiti.preauthorizer.domain.admin.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
