package com.test.springcloud.service.iml;

import org.springframework.stereotype.Component;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.OrderFeign;

@Component
public class MemberFeignService implements OrderFeign {

	@Override
	public CommonResult<Payment> payment(Long id) {
		CommonResult result = new CommonResult();
		result.setCode(500);
		result.setMsg("请求超时！！！");
		// TODO Auto-generated method stub
		return result;
	}

	

}
