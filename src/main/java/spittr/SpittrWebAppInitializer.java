package spittr;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.config.RootConfig;
import spittr.config.WebConfig;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOG = Logger.getLogger(SpittrWebAppInitializer.class);

	public SpittrWebAppInitializer() {
		super();
		LOG.info("|-drb-| == > Constructing...");
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("|-drb-| == > PostConstructing...");
	}

	// @Override
	protected String[] getServletMappings() {
		String servletMapping = "/";
		LOG.info("|-drb-| Setting up servlet mapping for " + servletMapping);
		return new String[] { servletMapping };
	}

	// @Override
	protected Class<?>[] getRootConfigClasses() {
		LOG.info("|-drb-| Setting up root config class with " + RootConfig.class.getCanonicalName());
		return new Class<?>[] { RootConfig.class };
	}

	// @Override
	protected Class<?>[] getServletConfigClasses() {
		LOG.info("|-drb-| Setting up servlet config class with " + WebConfig.class.getCanonicalName());
		return new Class<?>[] { WebConfig.class };
	}

	// @Override
	// protected Filter[] getServletFilters() {
	// //a shortcut to add a filter to DispatcherServlet (the default)
	// return new Filter[] { new MyFilter() };
	// }

	@Override
	protected void customizeRegistration(Dynamic registration) {
		/// registration.setMultipartConfig(new
		/// MultipartConfigElement("/tmp/spittr/uploads"));
		registration.setMultipartConfig(new MultipartConfigElement("C:\\web\\uploads"));
	}

}