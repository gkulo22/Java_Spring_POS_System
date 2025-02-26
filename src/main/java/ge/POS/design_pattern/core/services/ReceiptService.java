package ge.POS.design_pattern.core.services;

import ge.POS.design_pattern.core.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;


}
