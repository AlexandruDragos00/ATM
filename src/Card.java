import javax.swing.JPanel;
import javax.swing.Timer;

public class Card extends JPanel {
	
	int x, y, width, height;
	
	int targetX, targetY;
	
	Timer timer = new Timer(20, (e)->update());

	public Card() {
		setLayout(null);
	}
	
	private void update() {
		// determine new position
		if (x<targetX) x = x + 10;
		if (y>targetY) y = y - 1;
		// draw card at new position
		setLocation(x, y);
		repaint();
		// if target-position reached: stop timer (stop animation)
		if (x>=targetX && y<=targetY) {
			// lower the height
			height--;
			// adjust dimensions
			setSize(width, height);
			// if height is 0, timer.stop();
			if (height<=0) timer.stop();
		}
	}

	/**
	 * Start moving the card to the target position (x,y)
	 * @param tx x-value of target position
	 * @param ty y-value of target position
	 */
	public void moveToTarget(int tx, int ty) {
		// set target position
		targetX = tx;
		targetY = ty;
		// set starting position (the position at which the card has been drawn originally)
		x = getX();
		y = getY();
		// set dimensions
		width = getWidth();
		height = getHeight();
		// start Timer (clock starts ticking)
		timer.start();
	}
	
}

