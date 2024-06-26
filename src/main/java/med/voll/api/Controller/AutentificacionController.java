package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Infra.Security.TokenService;
import med.voll.api.Infra.Security.DatosJWTToken;
import med.voll.api.Usuarios.DatosAutentificacionUsuario;
import med.voll.api.Usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutentificacionUsuario datosAutentificacionUsuario){
        Authentication autenticationToken= new UsernamePasswordAuthenticationToken(datosAutentificacionUsuario.login(),datosAutentificacionUsuario.clave());
        var usuarioAutenticado= authenticationManager.authenticate(autenticationToken);
        var JWTtoken= tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
