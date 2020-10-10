package com.test.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment) {
         int result = paymentService.insert(payment);
//         log.info("====== 插入结果：" + result);
         if(result > 0) {
             return new CommonResult(200, "插入数据成功，服务端口：" + serverPort);
         }else {
             return new CommonResult(500, "插入数据失败");
         }

    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        System.out.println("访问111111");
//        log.info("====== 查询结果：" + result);
        if(result != null) {
            return new CommonResult(200, "查询成功，服务端口：" + serverPort, result);
        }else {
            return new CommonResult(500, "查询失败");
        }

    }
    
	@RequestMapping("payment/testFallbackMethod/{id}")
	@HystrixCommand(fallbackMethod="fallback")
	public CommonResult<Payment> testFallbackMethod(@PathVariable("id") Long id){
    	 Payment result = paymentService.getPaymentById(id);
         System.out.println("访问111111");
//         log.info("====== 查询结果：" + result);
         if(result != null) {
             return new CommonResult(200, "查询成功，服务端口：" + serverPort, result);
         }else {
             return new CommonResult(500, "查询失败");
         }
	}
	
	public CommonResult<Payment> fallback(Long id) {
		CommonResult result = new CommonResult();
		result.setCode(500);
		result.setMsg("太挤了！！！");
		// TODO Auto-generated method stub
		return result;
	}
}
