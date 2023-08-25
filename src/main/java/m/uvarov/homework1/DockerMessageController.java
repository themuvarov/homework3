package m.uvarov.homework1;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import m.uvarov.homework1.model.User;
import m.uvarov.homework1.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DockerMessageController {

    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    @Timed
    @GetMapping("/health/")
    public String getMessage() {
        return "{\"status\": \"OK\"}";
    }

    /*@Timed
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @Timed
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }
    @Timed
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/users", consumes = {"application/json"})
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @Timed
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId,
                                                   @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    @Timed
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/

    @PostMapping(value = "/users/register", consumes = {"application/json"})
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerNewUser(user));
    }

    @GetMapping("/users/profile")
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateEmployee(@AuthenticationPrincipal User user,
                                               @RequestBody User userDetails)  {
        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        if (userDetails.getPassword() != null &&
                !userDetails.getPassword().isEmpty()) {
            user.setPassword(userService.encodePwd(userDetails.getPassword()));
        }
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
