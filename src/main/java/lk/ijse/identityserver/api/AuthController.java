package lk.ijse.identityserver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lk.ijse.identityserver.dto.AuthRequestDTO;
import lk.ijse.identityserver.dto.UserDTO;
import lk.ijse.identityserver.service.UserService;
import lk.ijse.identityserver.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author Lahiru Dilshan
 * @created Sat 12:03 PM on 10/21/2023
 * @project identity-server
 **/
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveUser(@RequestPart("file") MultipartFile file,@Valid @RequestPart("user") UserDTO user) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(user.toString());
        //@Valid
        //UserDTO userDTO = new ObjectMapper().readValue(user, UserDTO.class);
        /*if (errors.hasFieldErrors()){
            System.out.println(errors.getFieldErrors().get(0).getDefaultMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }*/

        //System.out.println(userDTO.toString());

        //userService.saveUser(file, userDTO);
        throw new RemoteException("adwdadad");
        /*return ResponseUtil
                .builder()
                .code(200)
                .message("User save successful")
                .build();*/
    }


    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getToken(@RequestBody AuthRequestDTO requestDTO) throws IOException {

        return ResponseUtil
                .builder()
                .code(200)
                .data(userService.generateToken(requestDTO))
                .message("User log in successful")
                .build();
    }

    @GetMapping(value = "/validate", params = {"token"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String validateToken(@RequestParam String token){

        userService.validateToken(token);
        return "Token is valid ";

    }

}
