package com.bedu.tarjetas.auth;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.services.ILocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    ILocationService iLocationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthenticationResponse> validate( @RequestHeader ("x-token") String token ){
        return ResponseEntity.ok( service.validate(token) );
    }

  /*  @GetMapping
    public List<Location> allLocations(){
        List<Location> locationList = iLocationService.getAllLocations();
        return locationList;
    }
*/

}
