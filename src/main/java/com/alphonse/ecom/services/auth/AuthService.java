package com.alphonse.ecom.services.auth;

import com.alphonse.ecom.Dto.SignInrequest;
import com.alphonse.ecom.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    UserDto enregistrerUser(SignInrequest signInrequest);

//    ResponseEntity<?> senregistrer(@RequestBody SignInrequest signInrequest);

    public Boolean hasUserWithEmail(String email);
}
