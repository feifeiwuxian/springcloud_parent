package com.wf.service;

import com.wf.config.FeignConfiguration;
import com.wf.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "user-service", fallback = UserServiceImpl.class, configuration = FeignConfiguration.class) // 类似@Mapper，会动态生成代理实现类
public interface UserService {
    /**
     * 逆向生成http://localhost:9091/user/findById?id=2
     * 再通过RestTemplate发送http请求
     * @param id
     * @return
     */
    @RequestMapping("/user/findById")
    User findById(@RequestParam("id") Integer id);
}
