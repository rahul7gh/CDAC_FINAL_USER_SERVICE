package com.customermanagement.service_communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("product-service")
public interface ProducService {

	@RequestMapping(
			 method= RequestMethod.GET,
			 value="/")
			 String hello();
	
	

}
