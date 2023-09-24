package com.erikjensenworks.ServoSkull.AOS;

import com.erikjensenworks.ServoSkull.AOS.Pojos.Unit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    // Create : Saves a new unit to the database
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    // Create : Saves multiple new units to the database
    public List<Unit> createUnits(List<Unit> units) {
        return unitRepository.saveAll(units);
    }

    // Read : Get a unit by id or get all available units
    public Optional<Unit> getUnitById(Long id) {
        return unitRepository.findById(id);
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    // Update : Modify an existing unit
    public Unit updateUnit(Long id, Unit updatedUnit) {
        if (unitRepository.existsById(id)) {
            updatedUnit.setId(id);
            return unitRepository.save(updatedUnit);
        }
        else {
            throw new EntityNotFoundException("Unit with ID " + id + " not found.");
        }
    }

    // Delete : Remove a unit
    public void deleteUnit(Long id) {
        if(unitRepository.existsById(id)) {
            unitRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Unit with ID " + id + " not found.");
        }
    }

}
