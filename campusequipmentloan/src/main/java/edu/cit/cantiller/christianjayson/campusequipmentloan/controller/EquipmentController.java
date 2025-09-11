package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Equipment;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final EquipmentRepository equipmentRepository;

    // Create equipment
    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // List available equipment
    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentService.getAvailableEquipment();
    }

    // List all equipment
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
