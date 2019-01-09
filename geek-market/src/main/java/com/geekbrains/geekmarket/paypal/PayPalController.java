package com.geekbrains.geekmarket.paypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {
    private final PayPalClient payPalClient;

    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/make/payment")
    public String makePayment(@RequestParam("sum") String sum){
        return payPalClient.createPayment(sum);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request){
        return payPalClient.completePayment(request);
    }
}
