package local.legacy.screensaver;

import java.awt.Color;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScreenSaverConfig {
	
	@Bean
	//@Scope(value="prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	//@Scope(value="prototype")
	@Scope("periodical")
	Color color(){
		
		Random random =new Random();
		
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	@Bean
	ColorFrame colorFrame() {
		
		return new ColorFrame() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Color getColor() {
				// TODO Auto-generated method stub
				return color();
			}
		};
	}
	
	@Bean
	CustomScopeRegistryBeanFactoryPostProcessor customScopeRegistryBeanFactoryPostProcessor() {
		
		return new CustomScopeRegistryBeanFactoryPostProcessor();
	}

}
