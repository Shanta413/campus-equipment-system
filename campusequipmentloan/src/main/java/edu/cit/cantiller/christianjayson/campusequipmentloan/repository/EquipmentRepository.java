package edu.cit.cantiller.christianjayson.campusequipmentloan.repository;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}