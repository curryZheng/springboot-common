package com.ygxc.aqjy.framework.core.swagger;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @ClassName SwaggerProperties
 * @Description
 * @Author LeiZheng
 * @Date 2019/8/6 18:36
 */
@Configuration
@ConfigurationProperties(prefix = "hvac.swagger")
@Data
public class SwaggerProperties {

    private Boolean enabled = true;

    private String basePackage = "";

    private String title;

    private String version;

}
