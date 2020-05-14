package com.ygxc.aqjy.framework.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
    private SwaggerProperties swaggerProperties;

	@Bean
	public Docket createRestApi() {// 创建API基本信息
		ParameterBuilder ticketPar = new ParameterBuilder();
	    List<Parameter> pars = new ArrayList<Parameter>(); 
	  	ticketPar.name("token").description("user token")
	  	.modelRef(new ModelRef("string")).parameterType("header") 
	  	.required(false).build(); //header中的ticket参数非必填，传空也可以
	  	pars.add(ticketPar.build());  //根据每个方法名也知道当前方法在设置什么参数
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
				.paths(PathSelectors.any())	
				.build()
				.globalOperationParameters(pars) ;
	}

	private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
		return new ApiInfoBuilder().title(swaggerProperties.getTitle())// API
																			// 标题
				.description(swaggerProperties.getTitle())// API描述
				.contact(swaggerProperties.getTitle())// 联系人
				.version(swaggerProperties.getVersion())// 版本号
				.build();
	}

}
