package med.voll.api.Infra.Errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//intercepta llamadas en caso que suceda algun error
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores= e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
        //Tratamos este error cuando el cliente ingresa un valor nulo en nombre y esas cosas.
        //Esto deberia retornar algun mensaje para que el cliente sepa en que se equivoco
    }

    //Record que permite devolverle al cliente la informacion que queremos que vea.
    //Por otro lado, var errores trae una lista de FieldError, por lo tanto debemos pasarle esa dato al constructor
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
