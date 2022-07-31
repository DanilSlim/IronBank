package local.tst;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("WINTER_NEARE")
public class CreditAppruveServiceImpl implements CreditAppruveService {

	@Override
	public boolean appruveCredit(String name) {
		
		return !name.contains("Stark") && ThreadLocalRandom.current().nextBoolean();
	}

}
