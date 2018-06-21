package com.my.sample.core.config;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"com.my.sample.core.dao.mapper"})  // mybatis自动描包
@EnableSwagger2Doc // 开启Swagger2，自动生成文档，Url：/swagger-ui.html
@EnableConfigurationProperties(AppProperties.class)
@Import(value = {RestTemplateAutoConfiguration.class})
public class AppConfig {

    @Resource
    private ObjectProvider<List<RestTemplateCustomizer>> restTemplateCustomizers;

    @PostConstruct
    public void init() {
        List<RestTemplateCustomizer> cs = restTemplateCustomizers.getIfAvailable();
        cs.add(new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate restTemplate) {
                System.out.println("————————————————————++++++++++++++++");
            }
        });
    }

}
