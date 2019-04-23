package site.fish119.adminsadp.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.Customer.Sale;
import site.fish119.adminsadp.repository.customer.SaleRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.customer
 * @Author fish119
 * @Date 2019/4/19 20:37
 * @Version V1.0
 */
@RestController
public class SaleController extends BaseController {
//    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleController(SaleRepository saleRepository){
//        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @RequestMapping(value = "/sale/sales", method = RequestMethod.GET)
    public ResponseEntity<?> getSales() {
        Map<String, Object> result = new HashMap<>();
        result.put("data",saleRepository.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/sale/sale/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id){
        Map<String, Object> result = new HashMap<>();
        result.put("data", saleRepository.getOne(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/sale/sales", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Sale reqBody) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", saleRepository.save(reqBody));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/sale/sales/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        Map<String, Object> result = new HashMap<>();
        saleRepository.deleteById(id);
        result.put("data", saleRepository.findAll());
        return ResponseEntity.ok(result);
    }
}
