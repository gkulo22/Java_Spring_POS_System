package ge.POS.design_pattern.infra.api;

import ge.POS.design_pattern.core.DTOs.CreateUnitDTO;
import ge.POS.design_pattern.core.CoreFacade;
import ge.POS.design_pattern.core.exceptions.UnitExistsWithNameException;
import ge.POS.design_pattern.core.exceptions.UnitNotFoundException;
import ge.POS.design_pattern.core.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private CoreFacade core;

    @PostMapping
    public ResponseEntity<Unit> createUnit(@RequestBody CreateUnitDTO request) {
        try {
            Unit unit = core.createUnit(request);
            return new ResponseEntity<>(unit, HttpStatus.CREATED);
        } catch (UnitExistsWithNameException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{unit_id}")
    public ResponseEntity<Unit> getUnit(@PathVariable("unit_id") String unit_id) {
        try {
            Unit unit = core.getUnit(unit_id);
            return new ResponseEntity<>(unit, HttpStatus.OK);
        } catch (UnitNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        return new ResponseEntity<>(core.getAllUnits(), HttpStatus.OK);
    }

}
