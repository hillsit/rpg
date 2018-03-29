package rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
public class StateStore {

	private HashMap<Integer,User> users;
	
	@Autowired
	private World world;

	public StateStore() {
		super();
		System.out.println("Creating new state store!");

		users=new HashMap<Integer,User>();
	}


	public User getUser(int userId) {
		User user;
		if (!users.containsKey(userId)) {
			user = new User(world);
			user.setUserId(userId);
			user.setX(10);
			user.setY(10);
			users.put(userId,user);
		} else {
			user = users.get(userId);
		}
		return user;
	}
}
