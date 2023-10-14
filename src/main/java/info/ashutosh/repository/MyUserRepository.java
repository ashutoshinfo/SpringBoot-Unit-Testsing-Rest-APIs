package info.ashutosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.ashutosh.domain.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

}
