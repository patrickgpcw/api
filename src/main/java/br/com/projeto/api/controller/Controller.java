package br.com.projeto.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.Service.Servicee;
import br.com.projeto.api.model.Client;
import br.com.projeto.api.model.Message;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Repositoryy action;

    @Autowired
    private Servicee service;

    @PostMapping("/user")
    @Operation(summary = "Route to register news users")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "201",
        description = "User registed with sucessfully",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
                )
            }
        ),
        @ApiResponse(
        responseCode = "400",
        description = "Invalid information",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Message.class)
                )    
            }
        )
    })
    public ResponseEntity<?> register(@RequestBody User u){
        return service.register(u);
    }

    @GetMapping("/user/all")
    @Operation(summary = "Route to show all users")
    @ApiResponse(
        responseCode = "200",
        description = "Show all users",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
                )
            }
        )
    public ResponseEntity<?> showAll() {
        return service.showAll();
    }

    @GetMapping("/user/{code}")
    @Operation(summary = "Route to show a user")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200",
        description = "User found with sucessfully",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
                )
            }
        ),
        @ApiResponse(
        responseCode = "400",
        description = "User not found",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Message.class)
                )    
            }
        )
    })
    public ResponseEntity<?> showUser(@PathVariable int code) {
        return service.showUser(code);
    }

    @PutMapping("/user")
    @Operation(summary = "Route to update user")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200",
        description = "User updated with sucessfully",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
                )
            }
        ),
        @ApiResponse(
        responseCode = "400",
        description = "User not found",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Message.class)
                )    
            }
        )
    })
    public ResponseEntity<?> update(@RequestBody User u) {
        return service.update(u);
    }

    @DeleteMapping("/user/{code}")
    @Operation(summary = "Route to delete a user")
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200",
        description = "User deleted with sucessfully",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Message.class)
                )
            }
        ),
        @ApiResponse(
        responseCode = "400",
        description = "User not found",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Message.class)
                )    
            }
        )
    })
    public ResponseEntity<?> delete(@PathVariable int code){
        return service.delete(code);
    }

    @GetMapping("/user/count")  
    @Operation(summary = "Route to count users")
    @ApiResponse(
    responseCode = "200",
    description = "Quantity of users",
    content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class)
            )
        }
    )
    public long count(){
        return action.count();
    }

    @GetMapping("/user/order")
    @Operation(summary = "Route to sort by ascending name of users")
    @ApiResponse(
    responseCode = "200",
    description = "Show all user in sort by ascending name",
    content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class)
            )
        }
    )
    public List<User> order() {
        return action.findByOrderByName();
    }

    @GetMapping("/user/order/{name}")
    @Operation(summary = "Route to sort by descending age of users")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Show all user in descending order ",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                    )
                }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "User not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
                    )
                }
        )
    })
    public List<User> order2(@PathVariable String name) {
        return action.findByNameOrderByAgeDesc(name);
    }

    @GetMapping("/user/contain/{name}")
    @Operation(summary = "Route to find user by contain part of name")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Show all user found by contain part of name ",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                    )
                }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "User not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
                    )
                }
        )
    })
    public List<User> nameContain(@PathVariable String name) {
        return action.findByNameContaining(name);
    }

    @GetMapping("/user/start-with/{name}")
    @Operation(summary = "Route to find user by start with name ")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Show all user found by start with name",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                    )
                }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "User not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
                    )
                }
        )
    })
    public List<User> starWith(@PathVariable String name) {
        return action.findByNameStartingWith(name);
    }

    @GetMapping("/user/end-with/{name}")
    @Operation(summary = "Route to find user by end with name ")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Show all user found by end with name",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                    )
                }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "User not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
                    )
                }
        )
    })
    public List<User> endWith(@PathVariable String name) {
        return action.findByNameEndingWith(name);
    }

    @GetMapping("/user/sum-ages")
    @Operation(summary = "Route to sum ages of users")
    @ApiResponse(
    responseCode = "200",
    description = "Show sum ages of users",
    content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class)
            )
        }
    )
    public int sumAges() {
        return action.sumAges();
    }

    @GetMapping("/user/older-equal/{age}")
    @Operation(summary = "Route to find users who are older or same by age")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Show all user found by age",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                    )
                }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "User not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
                    )
                }
        )
    })
    public List<User> olderEqualUsers(@PathVariable int age) {
        return action.olderAgeEqual(age);
    }

    @PostMapping("/client/register")
    @Operation(summary = "Route test")
    public void registerClient(@Valid @RequestBody Client c) {
        // action.save(c);
    }
    
    
}
