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

    @GetMapping("findAll")
    public ResponseEntity<List<ParkignDto>> findAll(){
        List<ParkignModel> model = service.findAll();
        List<ParkignDto> dto = mapper.toParkingTOList(model);
        return ResponseEntity.ok(dto);

    }

    @GetMapping("findById{id}")
    public ResponseEntity<ParkignDto> findById(@PathVariable String id){
        ParkignModel model = service.finById(id);
        if(model == null){
            return ResponseEntity.notFound().build();
        }
        ParkignDto dto = mapper.toDto(model);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("deleteById{id}")
    public ResponseEntity<ParkignDto> deleteById(@PathVariable String id){

        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("updateByBid{id}")
    public ResponseEntity<ParkignDto> update(@PathVariable String id, @RequestBody ParkingCreateDto dto){
        var parkingCreate = mapper.toParkingCreate(dto);

        ParkignModel model = service.update(id,parkingCreate);
        ParkignDto result = mapper.toDto(model);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @PutMapping("updateExit{id}")
    public ResponseEntity<ParkignDto> exitParking(@PathVariable String id, @RequestBody ParkingCreateDto dto){
        var parkingCreate = mapper.toParkingCreate(dto);

        ParkignModel model = service.exit(id,parkingCreate);
        ParkignDto result = mapper.toDto(model);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }


    @PostMapping
    public ResponseEntity<ParkignDto> create(@RequestBody ParkingCreateDto dto){
        var parkingCreate = mapper.toParkingCreate(dto);

        ParkignModel model = service.create(parkingCreate);
        ParkignDto result = mapper.toDto(model);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

}
