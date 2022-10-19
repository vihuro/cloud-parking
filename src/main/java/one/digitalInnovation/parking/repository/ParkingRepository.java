package one.digitalInnovation.parking.repository;

import one.digitalInnovation.parking.model.ParkignModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<ParkignModel,String> {
}
