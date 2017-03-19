package lr2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
				ArraySumm.initializeAndFillArray(1000000);
				ArraySumm.multiThreadCalculator(50000);
				ArrayList<Thread> threads = ArraySumm.multiThreadCalculator(4);
				for (int i = 0; i < threads.size(); i++) {
					try {
						threads.get(i).join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				label.setText(Long.toString(ArraySumm.multiThreadSumm));
			}
		});

	}
}
