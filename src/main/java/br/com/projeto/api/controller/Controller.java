package br.com.projeto.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.service.Servicee;
import br.com.projeto.api.model.Client;
import br.com.projeto.api.model.User;
import br.com.projeto.api.repository.Repositoryy;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class Controller {
    @Autowired
    private Repositoryy action;

    @Autowired
    private Servicee service;

    @PostMapping("/api/user")
    public ResponseEntity<?> register(@RequestBody User u){
        return service.register(u);
    }

    @GetMapping("/api/user/all")
    public ResponseEntity<?> showAll() {
        return service.showAll();
    }

    @GetMapping("/api/user/{code}")
    public ResponseEntity<?> showUser(@PathVariable int code) {
        return service.showUser(code);
    }

    @PutMapping("/api/user")
    public ResponseEntity<?> update(@RequestBody User u) {
        return service.update(u);
    }

    @DeleteMapping("/api/user/{code}")
    public ResponseEntity<?> delete(@PathVariable int code){
        return service.delete(code);
    }

    @GetMapping("api/user/count")  
    public long count(){
        return action.count();
    }

    @GetMapping("api/user/order")
    public List<User> order() {
        return action.findByOrderByName();
    }

    @GetMapping("api/user/order/{name}")
    public List<User> order2(@PathVariable String name) {
        return action.findByNameOrderByAgeDesc(name);
    }

    @GetMapping("api/user/contain/{name}")
    public List<User> nameContain(@PathVariable String name) {
        return action.findByNameContaining(name);
    }

    @GetMapping("api/user/start-with/{name}")
    public List<User> starWith(@PathVariable String name) {
        return action.findByNameStartingWith(name);
    }

    @GetMapping("api/user/end-with/{name}")
    public List<User> endWith(@PathVariable String name) {
        return action.findByNameEndingWith(name);
    }

    @GetMapping("/api/user/sum-ages")
    public int sumAges() {
        return action.sumAges();
    }

    @GetMapping("/api/user/older-equal/{age}")
    public List<User> olderEqualUsers(@PathVariable int age) {
        return action.olderAgeEqual(age);
    }

    @PostMapping("/api/client/register")
    public void registerClient(@Valid @RequestBody Client c) {
        // action.save(c);
    }
    
    
}
