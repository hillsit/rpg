package rpg;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CommandController {

	@Autowired
	private StateStore state;

	private User user;

	@ResponseBody
	@RequestMapping("/command")
	public String command(@RequestParam int userId, @RequestParam String command) {
		user=state.getUser(userId);
		System.out.println(user.getId());

		System.out.println("Command:"+command);
		processCommand(command);
		Random rand = new Random();
		int r = rand.nextInt(10000) + 1; //10000 is the maximum and the 1 is our minimum 

		return "OK_"+r;
	}
	
	private String processCommand (String c) {
		String response="";
		if (c.equals("south") || c.equals("s")) {
			if (user.setX(user.getX()+1))
				response="You move south.";
			else 
				response="You can't go any further";
		} else if (c.equals("north") || c.equals("n")) {
			if (user.setX(user.getX()-1))
				response="You move north.";
			else 
				response="You can't go any further";
		} else if (c.equals("east") || c.equals("e")) {
			if (user.setY(user.getY()+1)) 
				response="You move east.";
			else 
				response="You can't go any further";
		} else if (c.equals("west") || c.equals("w")) {
			if (user.setY(user.getY()-1))
				response="You move west.";
			else 
				response="You can't go any further";
		} else if (c.equals("look")) {
			response = "You look around.";
		}
		
		System.out.println(user.getX() + ":"+ user.getY());
		return response;
	}

}