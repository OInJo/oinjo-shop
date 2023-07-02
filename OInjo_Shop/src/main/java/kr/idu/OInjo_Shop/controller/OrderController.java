package kr.idu.OInjo_Shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/orders/payment")
    public String Orders() {
        return "orderpayment";
    }
}
