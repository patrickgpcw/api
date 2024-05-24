package br.com.projeto.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Message;
import br.com.projeto.api.model.User;
import br.com.projeto.api.repository.Repositoryy;

@Service
public class Servicee {

    @Autowired
    private Message message;
    
    @Autowired
    private Repositoryy action;    

    public ResponseEntity<?> register(User u){
        if(u.getName().equals("")){
            message.setMessage("the name field needs to be filled in");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(u.getAge() < 0 ){
            message.setMessage("the age field needs to be > 0");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(action.save(u), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> showAll(){
        return new ResponseEntity<>(action.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> showUser(int code){
        if(action.countByCode(code) == 0 ){
            message.setMessage("id user not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.findByCode(code), HttpStatus.OK);
        }

    }

    public ResponseEntity<?> update(User u){
        if(u.getName().equals("")){
            message.setMessage("the name field needs to be filled in");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(u.getAge() < 0 ){
            message.setMessage("the age field needs to be > 0");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(action.countByCode(u.getCode()) == 0){
            message.setMessage("id user not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(action.save(u), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> delete(int code){
        if(action.countByCode(code) == 0){
            message.setMessage("id user not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            User u = action.findByCode(code);
            action.delete(u);
            message.setMessage("User deleted");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
