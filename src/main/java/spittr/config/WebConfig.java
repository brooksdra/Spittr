package spittr.config;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final Logger LOG = Logger.getLogger(WebConfig.class);
   
	public WebConfig() {
		super();
		LOG.info("|-drb-| == > Constructing...");
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("|-drb-| == > PostConstructing...");
	}
	
	// Standard JSP view resolver
//	@Bean
//	public ViewResolver viewResolver() {
//		// define a jsp view Resolver
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/pages/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		resolver.setExposeContextBeansAsAttributes(true);
//		return resolver;
//	}

	// Apache tiles view resolver
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
		tiles.setCheckRefresh(true);
		return tiles;
	}

	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		// String message = messageSource.getMessage("spittr.welcome", null,
		// null);
		return messageSource;
	}
	
//	@Override
//  Needed for SpringBootServletInitializer (ServletInitializer), but not for SpittrWebAppInitializer
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		// equivalent for <mvc:default-servlet-handler/> tag
//		// Tell the DispatherServlet to forward all request for static resources
//		// to the servlets container's default servlet
//		configurer.enable();
//	}
	
	//Used for resolving multi-part requests like images
	//Not needed if your controller is configured to accept the Part instead of MultiPartFile
//	@Bean
//	public MultipartResolver multipartResolver() throws IOException {
//		return new StandardServletMultipartResolver();
//	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// equivalents for <mvc:resources/> tags
	    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	    registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}
}
