package edu.cit.cantiller.christianjayson.campusequipmentloan.service;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> getAvailableEquipment();
}
