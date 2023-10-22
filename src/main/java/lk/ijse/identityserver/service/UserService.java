package lk.ijse.identityserver.service;

import lk.ijse.identityserver.dto.AuthRequestDTO;
import lk.ijse.identityserver.dto.AuthorizedRespondsDTO;
import lk.ijse.identityserver.dto.UserDTO;
import lk.ijse.identityserver.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:51 AM on 10/21/2023
 * @project identity-server
 **/
public interface UserService {

    void saveUser(MultipartFile file, UserDTO userDTO) throws IOException;

    UserDTO updateUser(UserDTO userDTO, MultipartFile file) throws IOException;

    UserDTO updateUserMailAndPassword(UserDTO userDTO);

    void deleteUser(Integer userId);

    List<UserDTO> getPageableUsers(Integer page, Integer count);

    List<UserDTO> getAll();

    UserDTO findByNic(String nic);

    AuthorizedRespondsDTO generateToken(AuthRequestDTO authRequestDTO) throws IOException;

    void validateToken(String token);
}