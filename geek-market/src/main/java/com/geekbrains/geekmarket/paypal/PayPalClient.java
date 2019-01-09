package com.geekbrains.geekmarket.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayPalClient {
    private String clientId = "Ac6mqPzlr247b0QMzUzKBLOZ9dQz3ChHuKY_2LEw6zXgbIaMnQvonkW8SuBz_GCYhBsUjEN_ZO5U_4OT";
    private String clientSecret = "EL1h1RGHIpG5C38mt0WpsNFnWbilSATbTad7Sz2PbHkiiT_fststoPlrI7A75SQsHPJ44mafrl6f_W1z";

    public String createPayment(String sum) {
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("RUB");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        System.out.println(response.get("redirect_url"));
        return "redirect:/" + response.get("redirect_url");
//        return response;
    }

    public Map<String, Object> completePayment(HttpServletRequest req) {
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment != null) {
                response.put("status", "success");
                response.put("payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        System.out.println(22222222);
        return response;
    }
}
