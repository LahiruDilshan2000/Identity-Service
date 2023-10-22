package lk.ijse.identityserver.repository;

import lk.ijse.identityserver.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:46 AM on 10/21/2023
 * @project identity-server
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByNic(String nic);

    boolean existsByEmail(String email);

    Optional<User> findByNic(String string);

    void deleteByNic(String nic);

    @Query(value = "from User u")
    List<User> getUserHQLWithPageable(Pageable pageable);
}
