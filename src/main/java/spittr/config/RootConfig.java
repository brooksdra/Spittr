package spittr.config;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "spittr" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })

public class RootConfig {
	
	private static final Logger LOG = Logger.getLogger(RootConfig.class);
    
	public RootConfig() {
		super();
		LOG.info("|-drb-| == > Constructing...");
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("|-drb-| == > PostConstructing...");
	}
}
