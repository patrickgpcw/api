package br.com.projeto.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.User;

@Repository
public interface Repositoryy extends CrudRepository<User,Integer> {
    
    List<User> findAll();

    User findByCode(int code);

    User findByName(String name);

    List<User> findByOrderByName();

    List<User> findByNameOrderByAgeDesc(String name);

    List<User> findByNameContaining(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    @Query(value = "SELECT SUM(age) FROM users", nativeQuery = true)
    int sumAges();

    @Query(value = "SELECT * FROM users WHERE age >= :age", nativeQuery = true)
    List<User> olderAgeEqual(int age);

    int countByCode(int code);
}
