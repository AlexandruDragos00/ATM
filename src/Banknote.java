import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

public class Banknote extends JPanel {
	public Banknote() {
		
		lblAmount = new JLabel("100");
		lblAmount.setFont(new Font("Bell MT", Font.BOLD, 30));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAmount, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(380, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAmount)
					.addContainerGap(254, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	int width, height, maxheight;
	
	Timer timer = new Timer(20, (e)->update());

	private JLabel lblAmount;

	private void update() {
		// increase height:
		height++;
		// set new dimensions:
		setSize(width, height);
		// draw again:
		repaint();
		// if height is at maximum, stop timer
		if (height==maxheight)
			timer.stop();
	}

	public void animate(String amount) {
		// make note visible:
		this.setVisible(true);
		// set properties:
		width=getWidth();
		height=0;
		maxheight=getHeight();
		// do first update
		update();
		// start timer: clock starts ticking
		timer.start();
		lblAmount.setText(amount);
	}
	
}
