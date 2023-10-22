package lk.ijse.identityserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lahiru Dilshan
 * @created Sat 12:24 PM on 10/21/2023
 * @project identity-server
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequestDTO {

    private String email;

    private String password;
}
