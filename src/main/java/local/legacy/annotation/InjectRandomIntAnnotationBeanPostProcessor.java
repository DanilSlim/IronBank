package local.legacy.annotation;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		Field[] fields=bean.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			
			if(annotation!=null) {
				
				int min=annotation.min();
				
				int max=annotation.max();
				
				Random random = new Random();
				
				int i = min+ random.nextInt(max-min);
				
				field.setAccessible(true);
				
				//ReflectionUtils.setField(field, beanName, i); dousn't work - can't set field to null and can't create bean TerminatorQuoter
				
				try {
					
					field.setInt(bean, i);
				
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
