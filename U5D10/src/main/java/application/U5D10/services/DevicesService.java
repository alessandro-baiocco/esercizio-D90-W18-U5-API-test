package application.U5D10.services;

import application.U5D10.controllers.DevicePut;
import application.U5D10.entities.Device;
import application.U5D10.entities.User;
import application.U5D10.enums.DeviceStatus;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.exceptions.DeviceNotAvalableException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        newDevice.setStatus(DeviceStatus.valueOf(body.status()));
        newDevice.setDisponibile(true);

        return devicesRepo.save(newDevice);

    }

    public void findByIdAndDelete(int id) throws NotDeviceFoundException{
        Device found = findById(id);
        devicesRepo.delete(found);
    }

    public Device findByIdAndUpdate(int id , Device body) throws NotDeviceFoundException {
        Device found = findById(id);

        found.setStatus(body.getStatus() != null ? body.getStatus() : found.getStatus());
        found.setType(body.getType()  != null ? body.getType() : found.getType());
        found.setDisponibile(body.getUser() == null);



        return devicesRepo.save(found);
    }

    public Device findByIdAndUpdate(int id , DevicePut body ) throws NotDeviceFoundException{
        Device found = findById(id);
        User userFound = usersRepo.findById(body.getUser()).orElseThrow(() -> new NotUserFoundException(id));
    if(found.getStatus().compareTo(DeviceStatus.disponibile) == 0){
     found.setStatus(DeviceStatus.assegnato);
     found.setUser(userFound);
     found.setDisponibile(true);
     return devicesRepo.save(found);
    }else if(found.getStatus().compareTo(DeviceStatus.manutenzione) == 0) {
        throw new DeviceNotAvalableException("il dispositivo è in manutenzione");
    }else if(found.getStatus().compareTo(DeviceStatus.dismesso) == 0) {
        throw new DeviceNotAvalableException("il dispositivo non è disponibile");
    }else  {
        throw new DeviceNotAvalableException("il dispositivo è già stato assegnato");
    }

    }








}
