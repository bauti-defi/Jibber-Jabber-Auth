package edu.austral.ingsis.jibberjabberauth.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import edu.austral.ingsis.jibberjabberauth.domain.dto.*;
import edu.austral.ingsis.jibberjabberauth.security.JwtTokenUtil;
import edu.austral.ingsis.jibberjabberauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(userService.login(loginDto, response));
    }

    @PostMapping("/change-pass")
    public Boolean changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) throws Exception {
        return userService.changePassword(changePasswordDto);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateJwt(){
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public JJUserDto getById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public JJUserDto register(@RequestBody @Valid CreateUserDto userDto){
        return userService.save(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable @Valid Long id){
        return userService.delete(id);
    }
}
