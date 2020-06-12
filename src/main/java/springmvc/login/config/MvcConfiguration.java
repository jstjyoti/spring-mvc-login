package springmvc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import springmvc.login.dao.UserDao;
import springmvc.login.dao.UserDaoImpl;

@Configuration
@ComponentScan(basePackages="springmvc.login")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
    public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(springTemplateEngine());
		return resolver;
	}

	@Bean
	public SpringTemplateEngine springTemplateEngine() {
	  SpringTemplateEngine engine = new SpringTemplateEngine();
	  engine.setEnableSpringELCompiler(true);
	  engine.setTemplateResolver(templateResolver());
	  return engine;
	}

	@Bean
    public SpringResourceTemplateResolver templateResolver() {
	  SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	  resolver.setApplicationContext(this.applicationContext);
	  resolver.setPrefix("/WEB-INF/views/");
	  resolver.setSuffix(".jsp");
	  resolver.setTemplateMode(TemplateMode.HTML);
	  return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
	
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("/jdbc:mysql://localhost:3306/loginsys?useSSL=false");
		ds.setUsername("root");
		ds.setPassword("root");

		return ds;

	}

	@Bean
	public UserDao getUserDao() {
		return new UserDaoImpl(getDataSource());
	}
	
	
}
