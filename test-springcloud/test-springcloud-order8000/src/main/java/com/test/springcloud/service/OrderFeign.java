package com.test.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.iml.MemberFeignService;

@Service
@FeignClient(name="CLOUD-PAYMENT-SERVICE",fallback = MemberFeignService.class)
public interface OrderFeign {
	///payment/get/{id}
	@RequestMapping("payment/get/{id}")
	public CommonResult<Payment> payment(@PathVariable("id") Long id);
}
