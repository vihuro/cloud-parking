package one.digitalInnovation.parking.service;

import one.digitalInnovation.parking.model.ParkignModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, ParkignModel> map = new HashMap();

    static {
        var id = getUUID();
        var id1 = getUUID();
        ParkignModel model = new ParkignModel(id,"DMS-1111","SC","CELTA","PRETO");
        ParkignModel model1 = new ParkignModel(id,"SSS-222","SP","GOLF","PRETO");
        map.put(id,model);
        map.put(id1,model1);
    }
    public List<ParkignModel> findAll(){
            return map.values().stream().collect(Collectors.toList());
    }
    private static String getUUID() {

        return UUID.randomUUID().toString().replace("-","");
    }

    public ParkignModel finById(String id) {

        return map.get(id);

    }

    public ParkignModel create(ParkignModel parkingCreate) {
        var uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        map.put(uuid,parkingCreate);

        return parkingCreate;

    }
}
