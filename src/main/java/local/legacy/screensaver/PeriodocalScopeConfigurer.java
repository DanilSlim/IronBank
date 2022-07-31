package local.legacy.screensaver;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;



public class PeriodocalScopeConfigurer implements Scope {
	Map <String, Pair<LocalTime,Object>> map= new HashMap<>();

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		
		if(map.containsKey(name)) {
			
			Pair<LocalTime, Object>pair = map.get(name);
			
			int secondsSinceLastRequest = LocalTime.now().getSecond()-pair.getKey().getSecond();
			
			if(secondsSinceLastRequest>5) {
				
				map.put(name, new Pair<LocalTime, Object>(LocalTime.now(), objectFactory.getObject()));
			}
				
			}else {
				
				map.put(name, new Pair<LocalTime, Object>(LocalTime.now(), objectFactory.getObject()));
			
		}
		
		return map.get(name).getValue();
	}

	@Override
	public Object remove(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
