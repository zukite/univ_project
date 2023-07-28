package new_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class start_view extends JFrame {
	start_view() {	// 시작화면 셋팅
		JFrame frame = new JFrame("조각 그림 맞추기 게임");	
		frame.setLocation(500, 200);
		frame.setPreferredSize(new Dimension(1000, 700));
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("게임");
		label.setBounds(440, 85, 100, 350);
		label.setFont(new Font("Gothic", Font.BOLD,45));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label);
		
		JButton button1 = new JButton("3x3");
		button1.setBounds(125, 415, 150, 100);
		button1.addActionListener(new ActionListener() {	// 3x3 버튼이 눌리면
			public void actionPerformed(ActionEvent e) {	// 시작화면은 사라지고 게임화면이 나타남
				new next_view();
				frame.setVisible(false);
			}
		});
		contentPane.add(button1);
		button1.setFont(new Font("Gothic", Font.BOLD,25));
		JButton button2 = new JButton("4x4");
		button2.setBounds(425, 415, 150, 100);
		contentPane.add(button2);
		button2.setFont(new Font("Gothic", Font.BOLD,25));
		JButton button3 = new JButton("5x5");
		button3.setBounds(725, 415, 150, 100);
		button3.setFont(new Font("Gothic", Font.BOLD,25));
		contentPane.add(button3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
}
class next_view extends JFrame{
	next_view() {		// 게임 화면 셋팅
		JFrame frame = new JFrame("조각 그림 맞추기 게임");
		frame.setLocation(500, 200);
		frame.setPreferredSize(new Dimension(1000, 700));
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		MyPanel imagePanel = new MyPanel();
		imagePanel.setBounds(0, 0, 600, 600);
		imagePanel.addMouseListener(new MouseHandler(imagePanel));
		contentPane.add(imagePanel);
		JLabel count_num = new JLabel("이동횟수 = "+Integer.toString(imagePanel.main_count));
		count_num.setFont(new Font("Gothic", Font.BOLD,20));
		count_num.setBounds(680, 150, 180, 200);
		frame.add(count_num);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
//		new BGM();
	}
}
