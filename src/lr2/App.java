package lr2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 19.03.2017.
 */
public class App {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Check this out!");
		JButton button = new JButton("ClickMe!");
		final JLabel label = new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(button, BorderLayout.SOUTH);
		frame.add(label, BorderLayout.NORTH);


		frame.setSize(300,100);
		frame.setVisible(true);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread doHardWork = new Thread(new DoHardWork(label));
				doHardWork.start();
			}
		});

	}
}
