package one.digitalInnovation.parking.service;

import one.digitalInnovation.parking.exception.ParkingFoundException;
import one.digitalInnovation.parking.model.ParkignModel;
import one.digitalInnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository repository;
    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ParkignModel> findAll(){
        return repository.findAll();
    }
    private static String getUUID() {

        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ParkignModel finById(String id) {

        return repository.findById(id).orElseThrow(() ->
                 new ParkingFoundException(id));
    }

    @Transactional
    public ParkignModel create(ParkignModel parkingCreate) {
        var uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());

        repository.save(parkingCreate);
        return parkingCreate;

    }

    @Transactional
    public void  delete(String id) {
        finById(id);
        repository.deleteById(id);

    }


    @Transactional
    public ParkignModel update(String id, ParkignModel parkingCreate) {
        ParkignModel model = finById(id);
        model.setColor(parkingCreate.getColor());
        model.setState(parkingCreate.getState());
        model.setModel(parkingCreate.getModel());
        model.setLicense(parkingCreate.getLicense());
        repository.save(model);

        return model;
    }

    @Transactional
    public ParkignModel exit(String id, ParkignModel parkingCreate) {

        ParkignModel model = finById(id);
        model.setExitDate(LocalDateTime.now());
        model.setBill(ParkingCheckOut.getBill(model));
        repository.save(model);

        return model;
    }
}
