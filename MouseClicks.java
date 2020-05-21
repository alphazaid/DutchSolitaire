import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClicks extends MouseAdapter{
	MyPanel panel;

	public MouseClicks(MyPanel panel) {
		
		this.panel = panel;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		 
		clickCard(e);
		blankSwap(e);
	
	}

	//selects a card that is not blank
	public void clickCard(MouseEvent e) {
		int xClick = e.getX();
		int yClick = e.getY();

		int yPos = (int)(Math.floor((xClick-115)/75));
		int xPos = (int)(Math.floor((yClick-145)/100));
		
		if(isOutOfBounds(e))
			System.out.println("Not in bounds!!!");
		else if(!panel.checkBlank(xPos, yPos)) {
			panel.xPos = xPos;
			panel.yPos = yPos;
				
			panel.coorY2 = (xPos*100) + 100;
			panel.coorX2 = (yPos*75) + 100;
			
			panel.selectedRectangleDimensions(panel.coorX2, panel.coorY2, 75, 100, 6);
		}
	}

	//checks if clicked a blank and swaps the cards.
	public void blankSwap(MouseEvent e) {
		int xClick = e.getX();
		int yClick = e.getY();
		
		int yPos = (int)(Math.floor((xClick-100)/75));
		int xPos = (int)(Math.floor((yClick-145)/100));
		
		if(isOutOfBounds(e))
			System.out.print("");
		else if(panel.xPos == -1)
			System.out.println("Please select a card first!!!");
		else if(panel.checkBlank(xPos, yPos)) {
			panel.setCoorX(xPos);
			panel.setCoorY(yPos);
			
			if(panel.gameRules()) {
				System.out.println("Swapped! :D");
				panel.swapImage();
				panel.xPos = -1;
				panel.yPos = -1;
			}
			else
				System.out.println("Not Possible! :(");
		}
	}
	
	//checks if the point clicked is OutOfBounds
	public boolean isOutOfBounds(MouseEvent e){
		
		int xClick = e.getX();
		int yClick = e.getY();
		
		
		if(xClick>1070 || yClick>545 || xClick < 115 || yClick < 145)
			return true;
		return false;
		
	}
	
}
