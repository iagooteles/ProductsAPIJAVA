package tech.ada.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.LoginResponseDTO;
import tech.ada.products_api.dto.RegisterDTO;
import tech.ada.products_api.dto.UserDTO;
import tech.ada.products_api.exception.TokenInvalidException;
import tech.ada.products_api.model.User;
import tech.ada.products_api.service.TokenService;
import tech.ada.products_api.service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) throws TokenInvalidException {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword());
        var authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = this.tokenService.generatedtoken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {

        if (Objects.nonNull(this.userService.findByLogin(registerDTO.getLogin()))) {
            return ResponseEntity.badRequest().build();
        }

        this.userService.salvar(registerDTO);

        return ResponseEntity.ok().build();
    }

}
