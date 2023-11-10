package application.U5D10.services;

import application.U5D10.entities.Device;
import application.U5D10.entities.User;
import application.U5D10.enums.DeviceStatus;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.exceptions.NotDeviceFoundException;
import application.U5D10.exceptions.NotUserFoundException;
import application.U5D10.payloads.NewDeviceDTO;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.repositories.DevicesRepository;
import application.U5D10.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DevicesService {
    @Autowired
    private DevicesRepository devicesRepo;
    @Autowired
    private UsersRepository usersRepo;

    public Device findById(int id) throws NotDeviceFoundException {
        return devicesRepo.findById(id).orElseThrow(() -> new NotDeviceFoundException(id));
    }

    public Page<Device> getAllUser(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return devicesRepo.findAll(pageable);
    }





    public Device save(NewDeviceDTO body) throws IOException {

        Device newDevice = new Device();

        newDevice.setType(body.type());
        newDevice.setStatus(DeviceStatus.valueOf(body.stato()));
        newDevice.setDisponibile(true);

        return devicesRepo.save(newDevice);

    }





}
