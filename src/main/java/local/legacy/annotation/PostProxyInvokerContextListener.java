package local.legacy.annotation;

import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>{
	
	
	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		ApplicationContext context = event.getApplicationContext();
		
		String [] names=context.getBeanDefinitionNames();
		
		for (String name : names) {
			
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			
			String originalClassName = beanDefinition.getBeanClassName();
			
			
			if(name.equals("terminatorQuoter")) {//Костыль так как извлекаемое значение originalClassName = beanDefinition.getBeanClassName();
												// равно null. Если конфиг в xml то все ок.
				
				originalClassName = "local.legacy.TerminatorQuoter";
				
			
			}
			
			
			if(originalClassName!=null) {
			
			try {
				
				Class<?> originalClass = Class.forName(originalClassName);
				
				Method[] methods = originalClass.getMethods();
				
				for (Method method : methods) {
					
					
					if(method.isAnnotationPresent(PostProxy.class)){
						
						Object bean= context.getBean(name);
						
						Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
						
						currentMethod.invoke(bean);
						
					}
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			}
			
		}
		
	}
	

}
