package local.legacy.screensaver;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;


import org.springframework.stereotype.Service;


@Service
public abstract class ColorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//@Autowired
	//private Color color;
	
	public ColorFrame() {
		
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showonRandomPlace() {
		
		Random random=new Random();
		setLocation(random.nextInt(1200), random.nextInt(600));
		getContentPane().setBackground(getColor());
		repaint();
	}

	protected abstract Color getColor();
		
		


}
