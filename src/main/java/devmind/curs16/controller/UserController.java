package devmind.curs16.controller;

import devmind.curs16.ErrorResponse;
import devmind.curs16.MyCustomException;
import devmind.curs16.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Data
public class UserController {

    private List<User> userList = new ArrayList<>();
    private List<User> loggedInUsers = new ArrayList<>();

    @PostMapping("/register")
    public User handleRegister(@Valid @RequestBody User user) {
        // Save the user to your in-memory data-store
        if (userList.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            // throw exception
            throw new IllegalStateException("User with this email already exists");
        }

        userList.add(user);
        System.out.println(userList.getLast());
        return user;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handleIllegalStateException(IllegalStateException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> handleLogin(@Valid @RequestBody User user) {
        if (loggedInUsers.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);// ;
        }
        loggedInUsers.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping(path = "/logout/{email}")
    public ResponseEntity<User> handleLogout(@PathVariable String email) throws MyCustomException {
        if (loggedInUsers.stream().noneMatch(u -> u.getEmail().equals(email))) {
            throw new MyCustomException("User with this email is not logged in");
        }
        loggedInUsers.removeIf(u -> u.getEmail().equals(email));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}