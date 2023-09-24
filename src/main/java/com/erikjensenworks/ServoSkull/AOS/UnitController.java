package com.erikjensenworks.ServoSkull.AOS;

import com.erikjensenworks.ServoSkull.AOS.Pojos.Unit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {

    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id) {
        Optional<Unit> unitOp = unitService.getUnitById(id);
        if(unitOp.isPresent()){
            return new ResponseEntity<>(unitOp.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Unit> addUnit(@RequestBody Unit unit) {
        Unit newUnit = unitService.createUnit(unit);
        return new ResponseEntity<>(newUnit, HttpStatus.CREATED);
    }

    @PostMapping("/many")
    public ResponseEntity<List<Unit>> addUnits(@RequestBody List<Unit> units) {
        List<Unit> newUnits = unitService.createUnits(units);
        return new ResponseEntity<>(newUnits, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@PathVariable Long id, @RequestBody Unit unit) {
        try{
            Unit updatedUnit = unitService.updateUnit(id, unit);
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Unit> deleteUnit(@PathVariable Long id) {
        try{
            unitService.deleteUnit(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
