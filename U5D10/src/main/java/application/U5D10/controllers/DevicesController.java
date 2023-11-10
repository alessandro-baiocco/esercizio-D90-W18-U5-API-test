package application.U5D10.controllers;

import application.U5D10.entities.Device;
import application.U5D10.entities.User;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.payloads.NewDeviceDTO;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.services.DevicesService;
import application.U5D10.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    private DevicesService devicesService;

    @GetMapping("/{id}")
    public Device findById(@PathVariable int id){
        return devicesService.findById(id);
    }

    @GetMapping("")
    public Page<Device> getAlldevices(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
        return devicesService.getAllUser(page , size , order);
    }



    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated NewDeviceDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return devicesService.save(body);
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable int id){
       devicesService.findByIdAndDelete(id);
    }


}
