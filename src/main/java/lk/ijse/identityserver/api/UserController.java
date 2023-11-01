package lk.ijse.identityserver.api;

import jakarta.validation.Valid;
import lk.ijse.identityserver.dto.UserDTO;
import lk.ijse.identityserver.dto.UserUpdateDTO;
import lk.ijse.identityserver.entity.Role;
import lk.ijse.identityserver.exception.UnauthorizedException;
import lk.ijse.identityserver.service.UserService;
import lk.ijse.identityserver.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:37 AM on 10/7/2023
 * @project nexttravel
 **/
@RestController
@RequestMapping("/api/v1/auth/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateUser(@RequestPart("file") MultipartFile file,
                                   @Valid @RequestPart("user") UserDTO userDTO,
                                   @RequestHeader("X-ROLE") Role role) throws IOException {

        if (!role.equals(Role.ADMIN_USER))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .message("User update successfully !")
                .data(userService.updateUser(userDTO, file))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil UpdateUsernameAndPassword(@Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                                  @RequestHeader("X-ROLE") Role role){

        if (!role.equals(Role.USER))
            throw new UnauthorizedException("Un authorized access to application");

        System.out.println(userUpdateDTO.toString());
        return ResponseUtil
                .builder()
                .code(200)
                .message("User update successfully !")
                .data(userService.updateUserUserNameAndPassword(userUpdateDTO))
                .build();
    }

    @DeleteMapping(params = {"userId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteUser(@RequestParam Integer userId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_USER))
            throw new UnauthorizedException("Un authorized access to application");

        userService.deleteUser(userId);
        return ResponseUtil
                .builder()
                .code(200)
                .message("User delete successfully !")
                .build();
    }

    @GetMapping(value = "/get", params = {"nic"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil findByNic(@RequestParam("nic") String nic) {

        return ResponseUtil
                .builder()
                .code(200)
                .message("Getting user by ID successfully !")
                .data(userService.findByNic(nic))
                .build();
    }

    @GetMapping(value = "/getAll", params = {"page", "count"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getUserPageable(@RequestParam Integer page,
                                        @RequestParam Integer count,
                                        @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_USER))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .message("Getting pageable user successfully !")
                .data(userService.getPageableUsers(page, count))
                .build();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getAll() {

        return ResponseUtil
                .builder()
                .code(200)
                .message("Getting all user successfully !")
                .data(userService.getAll())
                .build();
    }

    @GetMapping(value = "/search", params = {"text"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil searchByText(@RequestParam String text, @RequestHeader("X-ROLE") Role role) {

        if(!role.equals(Role.ADMIN_USER))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .message("Search guide by text successfully !")
                .data(userService.searchByText(text))
                .build();
    }
}
