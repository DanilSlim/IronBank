package local.tst;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IronBankController {
	
	private CreditAppruveService creditAppruveService;


	public IronBankController (CreditAppruveService creditAppruveService) {
		
		this.creditAppruveService=creditAppruveService;
	};
	
	
	@GetMapping("/credit")  
	public String getCredit(@RequestParam String name){
		
		if(!creditAppruveService.appruveCredit(name))
	    
		 return "Rejected<br/>" + name + " <b>will`t</b> survive this winter";
		
		else 
			
		  return String.format( "<i>Credit approved for %s</i> <br/>",name);
	  }
}
