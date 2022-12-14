package local.legacy.annotation;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import local.legacy.ProfilingController;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
	
	private Map <String,Class<?>> classesMap=new HashMap<>();
	
	private ProfilingController controller= new ProfilingController();
	
	
	public ProfilingHandlerBeanPostProcessor() throws Exception {
		
		MBeanServer mBeanServer= ManagementFactory.getPlatformMBeanServer();
		
		mBeanServer.registerMBean(controller, new ObjectName("profiling", "name","controller"));
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		Class<?> beanClass=bean.getClass();
		
		if (beanClass.isAnnotationPresent(Profiling.class)) {
			
			classesMap.put(beanName, beanClass);
		}
		
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		Class<?> beanClass=classesMap.get(beanName);
		
		if (beanClass!=null) {
			
			final Object newBean=Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					
					if (controller.isEnabled()) {
					
					System.out.println("Профилирую...");
					
					long befor =System.nanoTime();
					
					Object retVal=	method.invoke(bean, args);
					
					long after = System.nanoTime();
					
					System.out.println(after-befor);
					
					System.out.println("Все...");
					
					return retVal;
					} else {
						
						return method.invoke(bean, args);
					}
				}
			});
			
			return BeanPostProcessor.super.postProcessAfterInitialization(newBean, beanName);
			
		}
		
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
