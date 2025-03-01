package ge.POS.design_pattern.infra.api;

import ge.POS.design_pattern.core.CoreFacade;
import ge.POS.design_pattern.core.DTOs.ProductForReceiptDTO;
import ge.POS.design_pattern.core.DTOs.ReceiptStatusDTO;
import ge.POS.design_pattern.core.exceptions.ProductNotFoundException;
import ge.POS.design_pattern.core.exceptions.ReceiptClosedException;
import ge.POS.design_pattern.core.exceptions.ReceiptNotFoundException;
import ge.POS.design_pattern.core.models.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private CoreFacade core;

    @PostMapping
    public ResponseEntity<Receipt> createReceipt() {
        Receipt receipt = core.createReceipt();
        return new ResponseEntity<>(receipt, HttpStatus.CREATED);
    }

    @GetMapping("/{receipt_id}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable("receipt_id") String receipt_id) {
        try {
            Receipt receipt = core.getReceipt(receipt_id);
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        } catch (ReceiptNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{receipt_id}")
    public ResponseEntity<Void> closeReceipt(@PathVariable("receipt_id") String receipt_id,
                                         @RequestBody ReceiptStatusDTO receiptStatusDTO) {
        try {
            core.updateReceiptStatus(receipt_id, receiptStatusDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ReceiptNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{receipt_id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable("receipt_id") String receipt_id) {
        try {
            core.deleteReceipt(receipt_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ReceiptNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ReceiptClosedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{receipt_id}")
    public ResponseEntity<Receipt> addProductInReceipt(@PathVariable("receipt_id") String receipt_id,
                               @RequestBody ProductForReceiptDTO productForReceiptDTO) {
        try {
            Receipt receipt = core.addProductToReceipt(receipt_id, productForReceiptDTO);
            return new ResponseEntity<>(receipt, HttpStatus.CREATED);
        } catch (ReceiptNotFoundException | ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ReceiptClosedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
