package com.wf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient//开启服务发现
//@EnableCircuitBreaker //开启熔断器支持
@SpringCloudApplication // 一个顶上面3个
public class ConsummerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsummerServiceApplication.class, args);
	}

	@Bean
	/**
	 * 一旦开启，传统URL地址就使用不了了
	 * http://127.0.0.1:9091/user/findById?id=1
	 * http://user-service/user/findById?id=1
	 */
	@LoadBalanced // 开启当前RestTemplate对象，支持负载均衡的能力
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
