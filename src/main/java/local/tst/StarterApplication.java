package local.tst;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import local.legacy.Quoter;
import local.legacy.T1000;
import local.legacy.screensaver.ColorFrame;



@SpringBootApplication
@ComponentScan("local.legacy")
public class StarterApplication {
	
	

	public static void main(String[] args) throws InterruptedException {
		
		ApplicationContext context=  SpringApplication.run(StarterApplication.class, args);
		
		context.getBean(Quoter.class).sayQuter();
		
		T1000 t1000= (T1000) context.getBean("t1000");
		t1000.sayQuter();
		
		while (true) {
			
			//Thread.sleep(100);
			//context.getBean(Quoter.class).sayQuter();
			
			context.getBean(ColorFrame.class).showonRandomPlace();
			Thread.sleep(100);
		}
		
		
		
	}
	
	

}
