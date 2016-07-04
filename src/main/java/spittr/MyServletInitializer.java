package spittr;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;

import spittr.web.MyFilter;
import spittr.web.MyServlet;

public class MyServletInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// you can add a custom servlet
		Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
		myServlet.addMapping("/custom/*");
		
		// you can add a custom filter
		javax.servlet.FilterRegistration.Dynamic filter =
				servletContext.addFilter("myFilter", MyFilter.class);
				filter.addMappingForUrlPatterns(null, false, "/custom/*");
	}
}