package application.U5D10.controllers;


import application.U5D10.entities.User;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.services.UsersService;
import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return usersService.save(body);
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    }







}
