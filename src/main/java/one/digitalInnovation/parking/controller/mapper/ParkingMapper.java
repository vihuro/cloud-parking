package one.digitalInnovation.parking.controller.mapper;

import one.digitalInnovation.parking.dto.ParkignDto;
import one.digitalInnovation.parking.dto.ParkingCreateDto;
import one.digitalInnovation.parking.model.ParkignModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public ParkignDto toDto(ParkignModel model){
        return mapper.map(model, ParkignDto.class);
    }

    public List<ParkignDto> toParkingTOList(List<ParkignModel> model) {

        return model.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ParkignModel toParking(ParkignDto dto) {

        return mapper.map(dto,ParkignModel.class);
    }

    public  ParkignModel toParkingCreate(ParkingCreateDto dto) {

        return mapper.map(dto,ParkignModel.class);
    }


}
