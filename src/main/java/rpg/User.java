package rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
public class User {

	private int x=1;
	private int y=1;
	private int id=0;
	private int userId=0;
	private World world;	
	
	public User(World world) {
		super();
		System.out.println("Creating new user!");
		this.world=world;
		Random rand = new Random();
		id = rand.nextInt(10000) + 1; //10000 is the maximum and the 1 is our minimum 
	}

	public List<List<String>> getGrid() {
		//if (world==null) {
			//System.out.println("World is null!");
		//} else {
			//System.out.println("WOrld is good!");
		//}
		return world.getGrid(x,y);
	}

	public int getX() {
		return x;
	}

	public boolean setX(int x) {
		if (x<(Constants.WORLD_WIDTH-Constants.VIEW_WIDTH) &&
			x>Constants.VIEW_WIDTH) {
			this.x = x;
			return true;
		}
		return false;
	}

	public int getY() {
		return y;
	}

	public boolean setY(int y) {
		if (y<(Constants.WORLD_HEIGHT-Constants.VIEW_HEIGHT) &&
				y>Constants.VIEW_HEIGHT) {
			this.y = y;
			return true;
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
