package ge.POS.design_pattern.core.services;

import ge.POS.design_pattern.core.DTOs.CreateUnitDTO;
import ge.POS.design_pattern.core.exceptions.UnitExistsWithNameException;
import ge.POS.design_pattern.core.exceptions.UnitNotFoundException;
import ge.POS.design_pattern.core.repositories.UnitRepository;
import ge.POS.design_pattern.core.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public Unit createUnit(String name) throws UnitExistsWithNameException {
        if (unitRepository.hasName(name)) {
            throw new UnitExistsWithNameException(name);
        }

        Unit unit = new Unit();
        unit.setName(name);
        return unitRepository.save(unit);
    }

    public Unit getUnit(String id) throws UnitNotFoundException {
        return unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }
}
