package com.alphonse.ecom.services.auth;
import com.alphonse.ecom.Dto.SignInrequest;
import com.alphonse.ecom.Dto.UserDto;
import com.alphonse.ecom.enums.UserRole;
import com.alphonse.ecom.models.User;
import com.alphonse.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto enregistrerUser(SignInrequest signInrequest) {
        System.out.println(signInrequest);
        User utilisateur = new User();
        System.out.println("signInrequest"+signInrequest);
        System.out.println("utilisateur"+utilisateur);
        utilisateur.setEmail(signInrequest.getEmail());
        utilisateur.setNom(signInrequest.getNom());
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(signInrequest.getPassword()));
        utilisateur.setRole(UserRole.CUSTOMER);
        System.out.println("UserRole.CUSTOMER"+utilisateur);

        User utilisateurEnregistre = userRepository.save(utilisateur);
        System.out.println("utilisateurEnregistre"+utilisateurEnregistre);
        UserDto userDto = new UserDto();
        userDto.setId(utilisateurEnregistre.getId());

        System.out.println("userDto"  + userDto);
        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void creerCompteAdmin() {
        User compteAdministrateur = userRepository.findByRole(UserRole.ADMIN);

        if (compteAdministrateur ==null){
            //creer un nouvel utilisateur ici
            User user = new User();
            user.setEmail("azigani8@gmail.com");
            user.setNom("zigani");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
            userRepository.save(user);

        }
    }

}
