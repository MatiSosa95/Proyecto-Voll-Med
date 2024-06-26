package med.voll.api.Infra.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.Usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token y eso se hace a traves de los headers
        var token= request.getHeader("Authorization");//.replace("Bearer ", "");
        if (token != null ) {
            token = token.replace("Bearer ", "");
            var subject= tokenService.getSubject(token);//Etraemos el nombre de usuario
            if (subject!=null){
                //Token valido
                var usuario= usuarioRepository.findByLogin(subject);
                var authenticacion= new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());//Forzamos inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authenticacion);
            }
        }
        filterChain.doFilter(request, response);//Es la unica forma de hacer un filtro para que se devuelva algo
    }
}
