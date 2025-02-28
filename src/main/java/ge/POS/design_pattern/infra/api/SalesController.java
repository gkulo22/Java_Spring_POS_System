package ge.POS.design_pattern.infra.api;

import ge.POS.design_pattern.core.CoreFacade;
import ge.POS.design_pattern.core.models.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private CoreFacade core;

    @GetMapping
    public ResponseEntity<Sales> getSales() {
        Sales sales = core.getSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}
