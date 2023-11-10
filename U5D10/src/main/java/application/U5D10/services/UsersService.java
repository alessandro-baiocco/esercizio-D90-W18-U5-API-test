package application.U5D10.services;

import application.U5D10.entities.Device;
import application.U5D10.entities.User;
import application.U5D10.exceptions.BadRequestException;
import application.U5D10.exceptions.NotUserFoundException;
import application.U5D10.payloads.NewDeviceDTO;
import application.U5D10.payloads.NewUserDTO;
import application.U5D10.repositories.DevicesRepository;
import application.U5D10.repositories.UsersRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private DevicesRepository devicesRepo;
    @Autowired
    private Cloudinary cloudinary;

    public User findById(int id) throws NotUserFoundException{
        return usersRepo.findById(id).orElseThrow(() -> new NotUserFoundException(id));
    }

    public Page<User> getAllUser(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return usersRepo.findAll(pageable);
    }


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

    public List<Device> findUserDevices(int id) throws NotUserFoundException{
        User found = usersRepo.findById(id).orElseThrow(() -> new NotUserFoundException(id));
        return found.getDevices();
    }



    public void findByIdAndDelete(int id) throws NotUserFoundException{
        User found = findById(id);
        usersRepo.delete(found);
    }


    public User findByIdAndUpdate(int id , User body) throws NotUserFoundException{
        User found = findById(id);
        found.setNome(body.getNome() != null ? body.getNome() : found.getNome());
        found.setCognome(body.getCognome()  != null ? body.getCognome() : found.getCognome());
        found.setUserPicture(found.getUserPicture());
        found.setEmail(body.getEmail()  != null ? body.getEmail() : found.getEmail());
        found.setSurname(body.getSurname() != null ? body.getSurname() : found.getSurname());
        usersRepo.save(found);
        return found;
    }


    public User uploadPicture(int id , MultipartFile file) throws IOException, NotUserFoundException  {
        User found = usersRepo.findById(id).orElseThrow(() -> new NotUserFoundException(id));
        String newImage = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setUserPicture(newImage);

        return usersRepo.save(found);
    }




}
