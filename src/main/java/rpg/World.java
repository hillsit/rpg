package rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Service
public class World {

	private List<List<String>> world;
	private List<Monster> monsters = new ArrayList<Monster>();
	private List<Thing> things = new ArrayList<Thing>();

	public World() {
		super();
		Random rand = new Random();
		System.out.println("Creating new world!");
		world = new ArrayList<List<String>>();
		 
		 // Initial Grid Setup
		for (int i=0; i<Constants.WORLD_HEIGHT; i++) {
			 world.add(i,new ArrayList<String>());
			 for (int j=0; j<Constants.WORLD_WIDTH; j++) {
				 world.get(i).add(j, getRandChar());
			 }
		}
		
		// Initialize Monsters
		for (int i=0; i<Constants.START_MONSTERS; i++) {
			int  x = rand.nextInt(Constants.WORLD_WIDTH); 
			int  y = rand.nextInt(Constants.WORLD_HEIGHT); 
			Monster m = new Monster();
			m.setIcon("m");
			m.setX(x);
			m.setY(y);
			monsters.add(m);
		}
				
		// Initialize Things
		for (int i=0; i<Constants.START_THINGS; i++) {
			int  x = rand.nextInt(Constants.WORLD_WIDTH); 
			int  y = rand.nextInt(Constants.WORLD_HEIGHT); 
			Thing t = new Thing();
			t.setIcon("x");
			t.setX(x);
			t.setY(y);
			things.add(t);
		}

	}
	
	public List<List<String>> getGrid(int x, int y) {
		List<List<String>> grid = new ArrayList<List<String>>();
		
		int centerX = (Constants.VIEW_HEIGHT-1)/2;
		int centerY = (Constants.VIEW_WIDTH-1)/2;
		
		 // Initial Grid Setup
		for (int i=0; i<Constants.VIEW_HEIGHT; i++) {
			 grid.add(i,new ArrayList<String>());
			 for (int j=0; j<Constants.VIEW_WIDTH; j++) {
				 int relX=x+i;
				 int relY=y+j;
				 String icon = getPosition(relX,relY);
				 for (int k=0; k<monsters.size(); k++) {
					 Monster m=monsters.get(k);
					 //System.out.println(m.getX()+"=="+relX +"&&"+ m.getY()+"=="+relY);
					 if (m.getX()==relX && m.getY()==relY) {
						 System.out.println("HIT!!!");
						 icon=m.getIcon();
					 }
				 }
				 for (int k=0; k<things.size(); k++) {
					 Thing t=things.get(k);
					 if (t.getX()==relX && t.getY()==relY) {
						 System.out.println("HIT!!!");
						 icon=t.getIcon();
					 }
				 }
				 if (i==centerX && j==centerY) {
					 icon="U";
				 }
				 grid.get(i).add(j, icon);
			 }
		}
		return grid;
	}
	
	private String getRandChar() {
		char c='.';
		//Random rand = new Random();
		//int  n = rand.nextInt(10) + 1; //10 is the maximum and the 1 is our minimum 
		//if (n==1) {
		//	c='~'; 
		//} else if (n==2) {
		//	c='*'; 			
		//}
		return c+"";
	}

	public String getPosition(int x, int y) {
		return world.get(x).get(y);
	}
	
	public List<List<String>> getWorld() {
		return world;
	}

}
