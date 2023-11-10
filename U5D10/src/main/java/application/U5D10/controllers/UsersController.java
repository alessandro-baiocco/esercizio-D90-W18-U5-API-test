package application.U5D10.controllers;


import application.U5D10.entities.Device;
import application.U5D10.entities.User;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.exceptions.NotUserFoundException;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.services.UsersService;
import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return usersService.findById(id);
    }

    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
            return usersService.getAllUser(page , size , order);
    }

    @GetMapping("/{id}/devices")
    public List<Device> getUserDevices(@PathVariable int id){
        return usersService.findUserDevices(id);
    }



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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id){
        usersService.findByIdAndDelete(id);
    }



    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody User body){
        return usersService.findByIdAndUpdate(id, body);
    }


    @PostMapping("/{id}/upload")
    public User changeUserImg(@PathVariable int id , @RequestParam("avatar") MultipartFile body) throws IOException , NotUserFoundException {
        return usersService.uploadPicture(id, body);

    }

}
