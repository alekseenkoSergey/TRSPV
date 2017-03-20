package lr2;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Sergey on 20.03.2017.
 */
public class DoHardWork implements Runnable {
	JLabel label = new JLabel();

	public DoHardWork(JLabel label) {
		this.label = label;
	}

	@Override
	public void run() {
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
}
