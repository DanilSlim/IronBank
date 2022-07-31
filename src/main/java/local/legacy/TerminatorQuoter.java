package local.legacy;

import javax.annotation.PostConstruct;

import local.legacy.annotation.DeprecatedClass;
import local.legacy.annotation.InjectRandomInt;
import local.legacy.annotation.PostProxy;
import local.legacy.annotation.Profiling;

@Profiling
@DeprecatedClass(NewImpl = T1000.class)
public class TerminatorQuoter implements Quoter {
	
	private String message;
	
	@InjectRandomInt(min=2, max=7)
	private int repeat;
	
	public TerminatorQuoter() {
		
	}
	
	public TerminatorQuoter(String message) {
		
		this.message=message;
		
		System.out.println("PHASE 1");
	}

	@Override
	@PostProxy
	public void sayQuter() {
		
		System.out.println("PHASE 3");
		for (int i = 0; i < repeat; i++) {
			
			System.out.println("Message= "+ message);
			
		}	

	}
	
	@PostConstruct
	public void init() {
		
		
		
		System.out.println("PHASE 2");
		
		System.out.println(repeat);
	}

	

}
