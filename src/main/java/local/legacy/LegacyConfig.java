package local.legacy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import local.legacy.annotation.DeprecationHandlerBeanFactoryPosProcessor;
import local.legacy.annotation.InjectRandomIntAnnotationBeanPostProcessor;
import local.legacy.annotation.PostProxyInvokerContextListener;
import local.legacy.annotation.ProfilingHandlerBeanPostProcessor;

@Configuration
public class LegacyConfig {
	
	@Bean
	TerminatorQuoter terminatorQuoter() {
		
		return new TerminatorQuoter("I'll be back!!!");
		
		//return new T1000("I'll be back!!!");
	}
	
	@Bean
	InjectRandomIntAnnotationBeanPostProcessor injectRandomIntAnnotationBeanPostProcessor() {
		
		return new InjectRandomIntAnnotationBeanPostProcessor();
	}
	
	@Bean
	ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() throws Exception {
		return new ProfilingHandlerBeanPostProcessor();
	}
	
	@Bean
	PostProxyInvokerContextListener postProxyInvokerContextListener() {
		
		return new PostProxyInvokerContextListener();
	}
	
	@Bean
	DeprecationHandlerBeanFactoryPosProcessor deprecationHandlerBeanFactoryPosProcessor() {
		
		
		return new DeprecationHandlerBeanFactoryPosProcessor();
	}
	
	


}
