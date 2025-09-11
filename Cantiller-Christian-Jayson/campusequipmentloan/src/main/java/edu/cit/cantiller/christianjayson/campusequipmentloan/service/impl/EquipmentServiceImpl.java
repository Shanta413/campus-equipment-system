package edu.cit.cantiller.christianjayson.campusequipmentloan.service.impl;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Equipment;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailableTrue();
    }
}
