package one.digitalInnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingFoundException extends RuntimeException {

    public ParkingFoundException(String id){
        super("Parkign not found with id: " +id);
    }
}
