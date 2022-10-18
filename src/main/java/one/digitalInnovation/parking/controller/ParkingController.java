package one.digitalInnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalInnovation.parking.controller.mapper.ParkingMapper;
import one.digitalInnovation.parking.dto.ParkignDto;
import one.digitalInnovation.parking.dto.ParkingCreateDto;
import one.digitalInnovation.parking.model.ParkignModel;
import one.digitalInnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService service;
    private final ParkingMapper mapper;

    public ParkingController(ParkingService service, ParkingMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("finAll")
    public ResponseEntity<List<ParkignDto>> findAll(){
        List<ParkignModel> model = service.findAll();
        List<ParkignDto> dto = mapper.toParkingTOList(model);
        return ResponseEntity.ok(dto);

    }

    @GetMapping("findById{id}")
    public ResponseEntity<ParkignDto> findById(@PathVariable String id){
        ParkignModel model = service.finById(id);
        ParkignDto dto = mapper.toDto(model);
        return ResponseEntity.ok(dto);

    }
    @PostMapping
    public ResponseEntity<ParkignDto> create(@RequestBody ParkingCreateDto dto){
        var parkingCreate = mapper.toParkingCreate(dto);

        ParkignModel model = service.create(parkingCreate);
        ParkignDto result = mapper.toDto(model);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

}
