package com.ocsc.poc.service;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ocsc.poc.entity.OrderEntity;
import com.ocsc.poc.repository.OrderDetailsRepository;
import com.ocsc.poc.repository.OrderRepository;
import com.ocsc.poc.ulti.TechnicalException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	RestTemplate restTemplate;

	final String MAKE_PAYMENT = "http://localhost:8086/payment/";

	Logger logger;

	@Override
	public void placeOrderAndMakePayment(Integer userId, Integer orderId) {

		try {
			Optional<OrderEntity> oe = orderRepository.findById(orderId);
			if (oe.get().getUserId().equals(userId) && makePayment()) {
				oe.get().setOrderStatus("SUCCESS");
				orderRepository.save(oe.get());
			} else {
				oe.get().setOrderStatus("FAILED");
				orderRepository.save(oe.get());
			}
		} catch (Exception ex) {
			throw new TechnicalException(ex.getMessage());
		}
	}

	private Boolean makePayment() {
		/*
		 * try { ResponseEntity<Boolean> response; HttpHeaders headers = new
		 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		 * headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		 * HttpEntity<Object> request = new HttpEntity<Object>(new Object(), headers);
		 * 
		 * response = restTemplate.postForEntity(MAKE_PAYMENT, request, Boolean.class);
		 * return response.getBody(); } catch (Exception ex) { throw new
		 * TechnicalException(ex.getMessage()); }
		 */
		return true;
	}

}
