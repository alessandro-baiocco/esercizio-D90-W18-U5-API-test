package application.U5D10.services;

import application.U5D10.entities.User;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.payloads.NewDeviceDTO;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.repositories.DevicesRepository;
import application.U5D10.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private DevicesRepository devicesRepo;



    public User save(NewUserDTO body) throws IOException {
        usersRepo.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        User newUser = new User();

        newUser.setCognome(body.cognome());
        newUser.setNome(body.nome());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setUserPicture("https://ui-avatars.com/api/?name=" + body.nome().replace(" " , "") + "+" + body.cognome().replace(" " , ""));

        return usersRepo.save(newUser);

    }


}