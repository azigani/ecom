package com.alphonse.ecom.controllers;

import com.alphonse.ecom.Dto.Authenticationrequest;
import com.alphonse.ecom.Dto.SignInrequest;
import com.alphonse.ecom.Dto.UserDto;
import com.alphonse.ecom.models.User;
import com.alphonse.ecom.repository.UserRepository;
import com.alphonse.ecom.services.auth.AuthService;
import com.alphonse.ecom.utils.JwtUtils;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public final AuthenticationManager authenticationManager;
    public final UserDetailsService userDetailsService;
    public final AuthService authService;
    public final UserRepository userRepository;
    public final JwtUtils jwtUtils;


    @PostMapping("/authentification")
    public void createAuthenticationToken(@RequestBody Authenticationrequest authenticationrequest, HttpServletResponse response) throws IOException, Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));
            System.out.println("authenticationrequest.getUsername() + authenticationrequest.getPassword()");
            System.out.println(authenticationrequest.getUsername() + authenticationrequest.getPassword());
        } catch (BadCredentialsException badcredentials) {
            System.out.println("Erreur authenticationrequest.getUsername() + authenticationrequest.getPassword()");
            System.out.println("response +");
            System.out.println(response +    "    " );

            System.out.println("badcredentials ");
            System.out.println("authenticationrequest "+authenticationrequest);
            System.out.println(badcredentials);
            throw new BadCredentialsException("Username ou password incorrects!");

        }

        System.out.println("2   authenticationrequest.getUsername() + authenticationrequest.getPassword()");
        System.out.println(2 + authenticationrequest.getUsername() + authenticationrequest.getPassword());
        final UserDetails userDatails = userDetailsService.loadUserByUsername(authenticationrequest.getUsername());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDatails.getUsername());
        System.out.println("optionalUser"+optionalUser);

        final String generateJwt = jwtUtils.generateToken(userDatails.getUsername());
        System.out.println(generateJwt +"generateJwt");
        if (optionalUser.isPresent()) {
            response.getWriter().write(
                    new JSONObject()
                            .put("userId", optionalUser.get().getId())
                            .put("role", optionalUser.get().getRole())
                            .toString()

            );
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            response.setHeader("Access-Control-Allow-Headers", "Authorization ,X-PINGOTHER ,Origin ," +
                    "X-Requested-With , Content-Type , Accept , X-Custom-header");

            response.addHeader(HEADER_STRING, TOKEN_PREFIX + generateJwt);
        }
    }


    //    @PostMapping("/authentification")
//    public ResponseEntity<Object> createAuthenticationToken(@RequestBody Authenticationrequest authenticationrequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Username ou password incorrects!", e);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationrequest.getUsername());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//
//        if (optionalUser.isPresent()) {
//            JSONObject responseBody = new JSONObject();
//            responseBody.put("userId", optionalUser.get().getId());
//            responseBody.put("role", optionalUser.get().getRole());
//
//            final String generateJwt = jwtUtils.generateToken(userDetails.getUsername());
//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.add(HEADER_STRING, TOKEN_PREFIX + generateJwt);
//
//            return ResponseEntity.ok()
//                    .headers(responseHeaders)
//                    .body(responseBody.toMap());
//        } else {
//            // Gérer le cas où l'utilisateur n'existe pas
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
    @PostMapping("/signup")
    public ResponseEntity<?> senregistrer(@RequestBody SignInrequest signInrequest) {
        System.out.println("SignUp du contfolleur" + signInrequest);
        if (authService.hasUserWithEmail(signInrequest.getEmail())) {

            return new ResponseEntity<>("utilisateur avec ce mail dejà existant!", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto = authService.enregistrerUser(signInrequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }


}