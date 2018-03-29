package rpg;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GridController {

	@Autowired
	private StateStore state;

	private User user;

	@ResponseBody
	@RequestMapping("/grid")
	public List<List<String>> grid(@RequestParam int userId) {
		user=state.getUser(userId);
		System.out.println(user.getId());
		return user.getGrid();
	  //return Arrays.<String>asList(new SomePojo("someProp", Arrays.<String>asList("prop1", "prop2"));
	}

}