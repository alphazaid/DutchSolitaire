import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;



public class solitaire{
	static int r = (int)(Math.random()*256);
	static int g = (int)(Math.random()*256);
	static int b = (int)(Math.random()*256);
	public static void main(String[] args) {
		JFrame j = new JFrame();
		MyPanel m = new MyPanel();
		j.addMouseListener(new MouseClicks(m));
		j.setSize(m.getSize());
		j.add(m);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setBackground(new Color(r, g, b));
	}
}
class MyPanel extends JPanel{
	
	static Image image;
	
	static String str;
	static String c;
	static String d;
	static String h;
	static String s;
	static String fileName;
	
	static ArrayList<Image> arr = new ArrayList<Image>();
	static ArrayList<Image> imageArr = new ArrayList<Image>();
	static ArrayList<String> stringArr = new ArrayList<String>();
	static ArrayList<String> fileNameArr = new ArrayList<String>();
	static ArrayList<Integer> fileNumberArr = new ArrayList<Integer>();
	
	char[] file;
	
	solitaire dutch = new solitaire();
	
	static Image[][] ImageMatrix = new Image[4][13]; 
	static String[][] StringMatrix = new String[4][13];
	static String[][] RandomStringMatrix = new String[4][13];
	static String[][] FileNameMatrix = new String[4][13];
	static Integer[][] FileNumberMatrix = new Integer[4][13];
	
	int coorX = 0;
	int coorY = 0;
	int xPos = -1;
	int yPos = -1;
	int enter = 0;
	static int fileNumber = 0;
	int xdim = 0;
	int ydim = 0;
	int widthDimension = 0;
	int heightDimension = 0;
	int thickness = 0;
	int coorX2 = 0;
	int coorY2 = 0;
	
	static Map<Image, String> keyMap = new LinkedHashMap<Image, String>();
	static Map<String, String> fNameMap = new LinkedHashMap<String, String>();
	static Map<String, Integer> fNumMap = new HashMap<String, Integer>();
	
	public MyPanel(){
		setSize(1300, 650);
		setVisible(true);
		
		backgroundFunctions();
	
	}
	
	//overrides the paint method
	
	public void paint(Graphics g) {
		
		setBackground(g);
		RandomLoop(g);
		selectedRectangle(g);
	}
	
	//calls the background constructor 
	public void backgroundFunctions() {
		Randomize();
		makeMatrix();
		makeSMatrix();
		makeMap();
		makefNameMap();
		makefNumMap();
		makeRSMatrix();
		makefNameMatrix();
		makefNumberMatrix();
		Blanks();
	}
	
	//sets the images
	public static void Image(int num, String m ) {
		try {
			image = ImageIO.read(new File(num+m));
		}catch(IOException e) {}
		str = num + m;
		
		fileName = m.substring(0, 1); 
		fileNumber = num;
	}	

	//draws the ordered cards
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
	
	//randomizes the cards
	public static void Randomize() {
		
		for(int x = 15; x>=2 ; x--) {
			
			if(x==14)
				continue;
			
			Image(x, "c.gif");
			arr.add(image);
			stringArr.add(str);
			fileNameArr.add(fileName);
			fileNumberArr.add(fileNumber);
			
			Image(x, "d.gif");
			arr.add(image);
			stringArr.add(str);
			fileNameArr.add(fileName);
			fileNumberArr.add(fileNumber);
			
			Image(x, "h.gif");
			arr.add(image);
			stringArr.add(str);
			fileNameArr.add(fileName);
			fileNumberArr.add(fileNumber);
			
			Image(x, "s.gif");
			arr.add(image);
			stringArr.add(str);
			fileNameArr.add(fileName);
			fileNumberArr.add(fileNumber);
		}
		
		imageArr.addAll(arr);
		Collections.shuffle(arr);
		
	}
	
	//prints a string from the RandomStringMAtrix
	public String printString(int x, int y) {
		return keyMap.get(ImageMatrix[x][y]);
	}
	
	//draws the Ace Cards 
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

	//sets coordinate X
	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}

	//sets coordinate y
	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}

	// makes ImageMatrix 
	public void makeMatrix() {
		int index = 0;
		
		for(int r = 0; r<ImageMatrix.length; r++) 
			for(int c = 0; c<ImageMatrix[r].length; c++) 
				ImageMatrix[r][c] = arr.get(index++);
	}
	
	//makes RandomString matrix corresponding to Image
	public void makeRSMatrix() {
		for(int r = 0; r < ImageMatrix.length; r++) {
			for(int c = 0; c < ImageMatrix[r].length; c++) {
				RandomStringMatrix[r][c] = keyMap.get(ImageMatrix[r][c]);
			}
		}
	}
	
	//makes fileName Matrix("c", "d", "h", "s") corresponding to Images
	public void makefNameMatrix() {
		for(int r = 0; r < FileNameMatrix.length; r++) {
			for(int c = 0; c < FileNameMatrix[r].length; c++) {
				FileNameMatrix[r][c] = fNameMap.get(RandomStringMatrix[r][c]);
			}
		}
	}
	
	//makes fileNumber Matrix corresponding to Images
	public void makefNumberMatrix() {
		for(int r = 0; r < FileNumberMatrix.length; r++) {
			for(int c = 0; c < FileNumberMatrix[r].length; c++) {
				if((RandomStringMatrix[r][c]).length() == 7)
					FileNumberMatrix[r][c] = Integer.parseInt(RandomStringMatrix[r][c].substring(0,2));
				else
					FileNumberMatrix[r][c] = Integer.parseInt(RandomStringMatrix[r][c].substring(0,1));
			}
		}
	}
	
	//makes Ordered String Matrix
	public void makeSMatrix() {
		int index = 0;
		
		for(int r = 0; r<StringMatrix.length; r++) 
			for(int c = 0; c<StringMatrix[r].length; c++) 
				StringMatrix[r][c] = stringArr.get(index++);
	}
		
	//makes map Image --> Full fileName String
	public void makeMap() {
		for(int x = 0; x<imageArr.size(); x++) {
			keyMap.put(imageArr.get(x), stringArr.get(x));
		}
	}
	
	//makes map full fileName String --> fileName
	public void makefNameMap() {
		for(int x = 0; x < stringArr.size(); x++) {
			fNameMap.put(stringArr.get(x), fileNameArr.get(x));
		}
	}
	
	//makes a map fileName --> fileNumber
	public void makefNumMap() {
		for(int x = 0; x < fileNameArr.size(); x++) {
			fNumMap.put(fileNameArr.get(x), fileNumberArr.get(x));
		}
	}
	
	//prints matrix in parameter
	public void printMatrix(Object[][] mat) {
		for(int r = 0; r<mat.length; r++) {
			for(int c = 0; c<mat[r].length; c++) {
				System.out.print(mat[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();	
	}

	//makes the blanks
	public void Blanks() {
		Image(15, "c.gif");
		c = str;
		
		Image(15, "d.gif");
		d = str;
		
		Image(15, "h.gif");
		h = str;
		
		Image(15, "s.gif");
		s = str;

	}
	
	//prints the cards
	public void RandomLoop(Graphics g) {
		Aces(g);
		
		int xdim = 75;
		int ydim = 100;
			
		for (int y = 100; y <= 100+ydim*3; y+=ydim) {
				int c = 0;
			for (int x = 100; x < 100+13*xdim; x+=xdim) {
				g.drawImage(ImageMatrix[(y/100)-1][c++],x,y,this);
				}
			}

		arr.removeAll(arr);
		stringArr.removeAll(stringArr);
	}
	
	//swaps String in the stringMatrix
	public void swapString(int x, int y) {		
		String temp = RandomStringMatrix[x][y];
		
		RandomStringMatrix[x][y] = RandomStringMatrix[coorX][coorY];
		
		RandomStringMatrix[coorX][coorY] = temp;
	}
	
	
	//swaps the image in the image matrix
	public void swapImage() {
			
			swapString(xPos, yPos);
			swapFileName(xPos, yPos);
			swapFileNumber(xPos,yPos);
			
			Image temp = ImageMatrix[xPos][yPos];
			
			ImageMatrix[xPos][yPos] = ImageMatrix[coorX][coorY];
			
			ImageMatrix[coorX][coorY] = temp;
			
			repaint();
			
		}
	
	//swaps the corresponding fileName
	public void swapFileName(int x, int y) {
		String temp = FileNameMatrix[x][y];
		
		FileNameMatrix[x][y] = FileNameMatrix[coorX][coorY];
		
		FileNameMatrix[coorX][coorY] = temp;
	}
	
	//swaps the corresponding fileNumber
	public void swapFileNumber(int x, int y) {
		int temp = FileNumberMatrix[x][y];
		
		FileNumberMatrix[x][y] = FileNumberMatrix[coorX][coorY];
		
		FileNumberMatrix[coorX][coorY] = temp;
	}
	
	//checks if the selected card is a blank card
	public boolean checkBlank(int x, int y) {
		if(RandomStringMatrix[x][y].equals(c))
			return true;
		else if (RandomStringMatrix[x][y].equals(s))
			return true;
		else if(RandomStringMatrix[x][y].equals(h))
			return true;
		else if(RandomStringMatrix[x][y].equals(d))
			return true;
		return false;
	}
	
	//draws a border around the cards
	public void drawBorder(Graphics g, int x, int y, int width, int height, int thickness) {
        g.setColor(Color.BLACK);
		
        for (int i = 0; i < thickness; i++) 
            g.drawRect(x + i, y + i, width - 2 * i, height - 2 * i);
	}

	//Checks if the cards and blank selected are "swappable"
	public boolean gameRules() {
		if(coorY == 0) {
			if(FileNameMatrix[xPos][yPos].equals(FileNameMatrix[coorX][coorY+1]))
				if((FileNumberMatrix[xPos][yPos] + 1) == FileNumberMatrix[coorX][coorY+1])
				return true;
		}
		
		if(coorY == 0) {
			if(FileNumberMatrix[xPos][yPos] == 2) {
				if(coorX == 0 && FileNameMatrix[xPos][yPos].equals("c"))
					return true;
				else if(coorX == 1 && FileNameMatrix[xPos][yPos].equals("d"))
					return true;
				else if(coorX == 2 && FileNameMatrix[xPos][yPos].equals("h"))
					return true;
				else if(coorX == 3 && FileNameMatrix[xPos][yPos].equals("s"))
					return true;
				else return false;
			}
		}
		
		if(coorY == 12) {
			if(FileNameMatrix[xPos][yPos].equals(FileNameMatrix[coorX][coorY-1]))
				if((FileNumberMatrix[xPos][yPos] - 1) == FileNumberMatrix[coorX][coorY-1])
					return true;
		}
		
		if(coorY != 0)
			if(FileNameMatrix[xPos][yPos].equals(FileNameMatrix[coorX][coorY-1])) {
				if((FileNumberMatrix[xPos][yPos] - 1) == FileNumberMatrix[coorX][coorY-1])
					return true;
		}
		
		if(coorY != 12) {
			if(FileNameMatrix[xPos][yPos].equals(FileNameMatrix[coorX][coorY+1]))
				if((FileNumberMatrix[xPos][yPos] + 1) == FileNumberMatrix[coorX][coorY+1])
					return true;
		}
		
		if(coorY == 12) {
			if(FileNumberMatrix[xPos][yPos] == 13) {
				if(coorX == 0 && FileNameMatrix[xPos][yPos].equals("c"))
					return true;
				else if(coorX == 1 && FileNameMatrix[xPos][yPos].equals("d"))
					return true;
				else if(coorX == 2 && FileNameMatrix[xPos][yPos].equals("h"))
					return true;
				else if(coorX == 3 && FileNameMatrix[xPos][yPos].equals("s"))
					return true;
			}
		}
		return false;
	}
	
	//sets variables to selected card for border
	public void selectedRectangleDimensions(int x, int y, int width, int height, int thickness){
		xdim = x;
		ydim = y; 
		widthDimension = width;
		heightDimension = height;
		this.thickness = thickness;
		
		repaint();
	}

	//calls drawSelectedBorder method
	public void selectedRectangle(Graphics g) {
		drawSelectedBorder(g, xdim, ydim, widthDimension, heightDimension, thickness);
	}
	
	//draws selectedBorder
	public void drawSelectedBorder(Graphics g, int x, int y, int width, int height, int thickness) {
        g.setColor(Color.red);
		for (int i = 0; i < thickness; i++) 
            g.drawRect(x + i, y + i, width - 2 * i, height - 2 * i);
	}
	//sets Background designs
	public void setBackground(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Courier", Font.BOLD, 72));
		g.drawString("Dutch Solitaire", 320 , 75);
		
		g.setColor(new Color(solitaire.r, solitaire.g, solitaire.b));
		g.fillRect(100, 100, 1050, 400);
		
		drawBorder(g, 80, 80, 1087, 437, 22);
	}
	
}