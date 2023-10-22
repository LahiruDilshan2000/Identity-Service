package lk.ijse.identityserver.dto;

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

    private Integer userId;

    private String userName;

    private String nic;

    private String address;

    private String email;

    private String password;

    private Role role;

    private byte[] userImage;

}
