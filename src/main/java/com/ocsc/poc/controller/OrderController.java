package com.ocsc.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.poc.service.OrderService;

@RestController
@RequestMapping(path = "")
@Validated
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping(path = "/order/{orderId}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void getOrderDetailsByOrderIdAndUserId(@RequestHeader(value = "X-OCSC-UserId") Integer userId) {

	}

	@PostMapping(path = "/order/{orderId}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void updateCart(@RequestHeader(value = "X-OCSC-UserId") Integer userId,
			@PathVariable("orderId") Integer orderId) {

		orderService.placeOrderAndMakePayment(userId, orderId);

	}

}
