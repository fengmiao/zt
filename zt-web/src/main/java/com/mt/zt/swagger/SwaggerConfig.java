package com.mt.zt.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * 本类是配置Swagger UI测试API功能
 * 
 * @author jiekechoo
 *
 */

@Configuration
@EnableSwagger
public class SwaggerConfig {
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean
	// Don't forget the @Bean annotation
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
				apiInfo()).includePatterns("/entrants/.*","/test/.*").apiVersion("1.0.0");
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Demo API", 
				null,
				null, 
				null,
				null, 
				null);
		return apiInfo;
	}
}