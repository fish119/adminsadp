package site.fish119.adminsadp.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.Customer.Customer;
import site.fish119.adminsadp.repository.customer.CustomerRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.customer
 * @Author fish119
 * @Date 2019/4/19 13:36
 * @Version V1.0
 */
@RestController
public class CustomerController extends BaseController {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @RequestMapping(value = "/customer/customers", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer() {
        Map<String, Object> result = new HashMap<>();
        result.put("data",customerRepository.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/customer/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id){
        Map<String, Object> result = new HashMap<>();
        result.put("data", customerRepository.getOne(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/customer/customers", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Customer reqBody) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", customerRepository.save(reqBody));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/customer/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        Map<String, Object> result = new HashMap<>();
        customerRepository.deleteById(id);
        result.put("data", customerRepository.findAll());
        return ResponseEntity.ok(result);
    }
}
