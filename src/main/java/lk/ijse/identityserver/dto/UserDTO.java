package lk.ijse.identityserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.ijse.identityserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lahiru Dilshan
 * @created Sat 12:05 PM on 10/21/2023
 * @project identity-server
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    @NotNull(message = "Customer Id cannot be null !")
    private Integer userId;

    @NotNull(message = "Customer name cannot be null !")
    @NotBlank(message = "Customer name cannot be empty !")
    @NotEmpty(message = "Customer name cannot be empty !")
    private String userName;

    private String nic;

    private String address;

    private String email;

    private String password;

    private Role role;

    private byte[] userImage;

}
