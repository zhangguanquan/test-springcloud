package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.OrderFeign;

@RestController
public class memController {
	
	@Autowired
	private OrderFeign orderFeign;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	
	@RequestMapping("payment/get/{id}")
	public CommonResult<Payment> payment(@PathVariable("id") Long id){
		return orderFeign.payment(id);
	}
	
	@HystrixCommand(fallbackMethod="fallback")
	@RequestMapping("payment/testFallbackMethod/{id}")
	public CommonResult<Payment> testFallbackMethod(@PathVariable("id") Long id){
		//throw new RuntimeException("请求异常！！！");
		return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
	}
	
	public CommonResult<Payment> fallback(Long id) {
		CommonResult result = new CommonResult();
		result.setCode(500);
		result.setMsg("太挤了！！！");
		// TODO Auto-generated method stub
		return result;
	}
	
}
