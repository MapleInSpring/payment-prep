package com.io.payment.payments;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private PaymentRepository repository;

    @GetMapping("")
    public List<String> getAllPayments() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(p -> p.getName())
                .collect(Collectors.toList());
    }
}
