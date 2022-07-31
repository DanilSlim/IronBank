package local.tst;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import local.starter.ProphetProperties;

@Service
@Profile("WINTER_HERE")
public class WhiteListBackendProphetServiceImpl implements CreditAppruveService {
	
	private final ProphetProperties prophetProperties;
	
	public WhiteListBackendProphetServiceImpl(ProphetProperties prophetProperties) {
		
		this.prophetProperties=prophetProperties;
	}
	

	@Override
	public boolean appruveCredit(String name) {
		
		return prophetProperties.getWhoReturnDebts().contains(name);
	}

}
