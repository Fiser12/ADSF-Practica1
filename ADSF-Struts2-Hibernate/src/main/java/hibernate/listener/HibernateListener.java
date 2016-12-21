package hibernate.listener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;
 
public class HibernateListener implements ServletContextListener{
 
	private AnnotationConfiguration config;
    private SessionFactory factory;
    private String path = "/hibernate.cfg.xml";
    private static Class clazz = HibernateListener.class;
 
   public static final String KEY_NAME = clazz.getName();

	   
	public void contextDestroyed(ServletContextEvent event) {
        factory.close();
	}
 
	public void contextInitialized(ServletContextEvent event) {
 
		try {
	         URL url = HibernateListener.class.getResource(path);
	         config = new AnnotationConfiguration().configure(url);
	         factory = config.buildSessionFactory();
	         event.getServletContext().setAttribute(KEY_NAME, factory);
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }

	}
}