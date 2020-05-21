import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class test extends Applet {
	static Image image;
	static String s;
	static ArrayList<Image> arr = new ArrayList<Image>();
	static ArrayList<Image> imageArr = new ArrayList<Image>();
	static ArrayList<String> holding = new ArrayList<String>();
	static ArrayList<String> RandomString = new ArrayList<String>();
	static Map<String, Integer> map = new LinkedHashMap<String, Integer>();
	static Map<Image, String> imap = new LinkedHashMap<Image, String>();
	static Image[][] ImageMatrix = new Image[4][13]; 
	
	public void init(){
		
		
	}
	public void paint(Graphics g) {
		RandomLoop(g);
	}
	public static void Image(int num, String m ) {
		try {
			image = ImageIO.read(new File(num+m));
		}catch(IOException e) {}
		s = num + m;
	}
	
	public void OrderedLoop(Graphics g) {
		int z = 1050;
		
		for(int x = 15; x>=2; x--) {
			z-=75;
			Image(x, "c.gif");
			g.drawImage(image, z, 0, this);
			
			Image(x,"d.gif");
			g.drawImage(image, z, 100, this);
			
			Image(x, "h.gif");
			g.drawImage(image, z, 200, this);
			
			Image(x, "s.gif");
			g.drawImage(image, z, 300, this);
		}
		repaint();
	}
		
	public static void Randomize() {
		
		for(int x = 15; x>=2 ; x--) {
			
			if(x==14)
				continue;
			
			Image(x, "c.gif");
			arr.add(image);
			holding.add(s);
			
			
			Image(x, "d.gif");
			arr.add(image);
			holding.add(s);
			
			
			Image(x, "h.gif");
			arr.add(image);
			holding.add(s);
			
			Image(x, "s.gif");
			arr.add(image);
			holding.add(s);
		}
		imageArr.addAll(arr);
		Collections.shuffle(arr);
		
	}
	
	public void Aces(Graphics g) {
		Image(14, "c.gif");
		g.drawImage(image, 1075, 100, this);
		
		Image(14, "d.gif");
		g.drawImage(image, 1075, 200, this);
		
		Image(14, "h.gif");
		g.drawImage(image, 1075, 300, this);
		
		Image(14, "s.gif");
		g.drawImage(image, 1075, 400, this);
	}

	public void makeMatrix() {
		int index = 0;
		for(int r = 0; r<ImageMatrix.length; r++) 
			for(int c = 0; c<ImageMatrix[r].length; c++) 
				ImageMatrix[r][c] = arr.get(index++);
	}
	
	public void RandomLoop(Graphics g) {
		
		Randomize();
		Aces(g);
		makeMatrix();
		int xdim = 75;
		int ydim = 100;
		
		for (int y = 100; y <= 100+ydim*3; y+=ydim) {
			int c = 0;
			for (int x = 100; x < 100+13*xdim; x+=xdim) {
				g.drawImage(ImageMatrix[(y/100)-1][c++],x,y,this);
			}
		}
		arr.removeAll(arr);
		holding.removeAll(holding);
		
	}
	
	public void swap() {
		Image temp = ImageMatrix[1][1];
		ImageMatrix[1][1] = ImageMatrix[2][2];
		ImageMatrix[2][2] = temp;
		
	}

}
