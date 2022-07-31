package local.legacy.annotation;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class DeprecationHandlerBeanFactoryPosProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		
		String[] beanDefenitionsNames = beanFactory.getBeanDefinitionNames();
		
		for (String name : beanDefenitionsNames) {
			
			BeanDefinition beanDefenition = beanFactory.getBeanDefinition(name);
			
			String beanClassName = beanDefenition.getBeanClassName();
			
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			if(name.equals("terminatorQuoter")) {//Костыль так как извлекаемое значение originalClassName = beanDefinition.getBeanClassName();
													// равно null. Если конфиг в xml то все ок.
												//связано с Java config
				beanClassName = "local.legacy.TerminatorQuoter";


				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			if (beanClassName!=null) {
			
			
			try {
				
				Class<?> beanClass = Class.forName(beanClassName);
				
				
				DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
				
				if(annotation!=null) {
					
					
					String className = annotation.NewImpl().getName();
					
					Class<?> targetClass = Class.forName(className);//Get target class
					
					
					GenericBeanDefinition bd = new GenericBeanDefinition();//Create new Bean Definition
					
					bd.setBeanClass(targetClass);                         //with target class
					
					((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("t1000", bd); //register own Bean Definition
					
					((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(name);//delete old Bean Definition
					
					//BeanDefinition beanDefenition1 = beanFactory.getBeanDefinition(name);		
					
					//System.out.println(beanDefenition1.getAttribute(className));
					
					//beanDefenition.setBeanClassName(annotation.NewImpl().getName());//Не работает, класс не заменяется
					
					
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
	}

}
