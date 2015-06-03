package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.swing.*;

public class app extends JFrame {
	JTextField text1;
	JTextArea resultcenter;
	JTextArea result1;
	JTextArea result2;
	JTextArea result3;
	JTextArea result4;
	JButton search;
	listen res;
	JLabel name;
	JLabel label;
	JLabel label2;

	public app() {
		init();
		setBounds(100, 50, 1000, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	void init() {
		setLayout(null);
		name = new JLabel("KDDCUP99", JLabel.CENTER);
		name.setBounds(280, 40, 300, 50);
		name.setFont(new Font("ubuntu", Font.BOLD, 36));
		add(name);
		label = new JLabel("Center", JLabel.LEFT);
		label.setBounds(20, 160, 100, 30);
		label.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(label);
		label2 = new JLabel("Samples", JLabel.LEFT);
		label2.setBounds(20, 230, 100, 30);
		label2.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(label2);
		text1 = new JTextField();
		text1.setBounds(100, 100, 600, 40);
		add(text1);
		search = new JButton();
		search.setText("search");
		search.setBounds(720, 100, 100, 40);
		add(search);
		resultcenter = new JTextArea();
		resultcenter.setLineWrap(true);
		resultcenter.setBounds(100, 150, 700, 90);
		resultcenter.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(resultcenter);
		result1 = new JTextArea();
		result1.setLineWrap(true);
		result1.setBounds(100, 240, 700, 80);
		result1.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(result1);
		result2 = new JTextArea();
		result2.setLineWrap(true);
		result2.setBounds(100, 320, 700, 80);
		result2.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(result2);
		result3 = new JTextArea();
		result3.setLineWrap(true);
		result3.setBounds(100, 400, 700, 80);
		result3.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(result3);
		result4 = new JTextArea();
		result4.setLineWrap(true);
		result4.setBounds(100, 480, 700, 80);
		result4.setFont(new Font("ubuntu", Font.BOLD, 16));
		add(result4);
		res = new listen();
		search.addActionListener(res);
	}

	public class listen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String centername[] = { "DOS", "NORMAL", "PROBE", "R2L", "U2R" };
			String centers[] = {
					"0.00000,a,a,a,935.53490,0.01724,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,507.09579,507.09622,0.00000,0.00000,0.00000,0.00000,1.00000,0.00000,0.00000,254.98266,254.92969,0.99978,0.00003,0.99962,0.00000,0.00001,0.00000,0.00002,0.00000",
					"3.39710,a,a,a,2005.49682,3589.76600,0.00000,0.00000,0.00000,0.11003,0.00000,0.85847,0.02871,0.00039,0.00005,0.00467,0.00225,0.00009,0.00550,0.00000,0.00000,0.00214,8.96116,12.18990,0.00151,0.00184,0.00252,0.00508,0.99667,0.00510,0.13691,153.63950,232.82631,0.95053,0.00853,0.04390,0.01618,0.00134,0.00086,0.00586,0.00792",
					"79.95500,a,a,a,11.03466,16.64546,0.00000,0.00000,0.00000,0.00228,0.00174,0.00110,0.00004,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,155.58741,7.94707,0.00501,0.00013,0.99441,0.99766,0.26946,0.09891,0.04170,209.76732,46.53618,0.21853,0.12445,0.08694,0.03015,0.00541,0.00032,0.98282,0.98429",
					"0.00621,a,a,a,7963.91333,9.13225,0.00016,0.00000,0.00000,0.00003,0.00001,0.00011,0.00010,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,0.00000,189.97901,11.07334,0.99808,0.99979,0.00178,0.00008,0.07178,0.06604,0.00030,254.60863,11.15407,0.04404,0.06827,0.00161,0.00014,0.99794,0.99969,0.00171,0.00007",
					"1145.02038,a,a,a,20477.09276,7697.15158,0.00043,0.17179,0.00038,0.44532,0.00135,0.26710,0.14876,0.00130,0.00076,0.28310,0.01924,0.00254,0.00330,0.00000,0.00000,0.02784,7.30412,8.53376,0.00639,0.00226,0.00158,0.00124,0.92840,0.07706,0.12106,159.97592,44.90779,0.32942,0.27757,0.57348,0.06241,0.00886,0.00218,0.02323,0.00125" };
			
			hbase hb = new hbase();
			String exe = new String();
			String exb = new String();
			String temp = text1.getText();
			String[] sam = new String[4];
			String[] trans = new String[4];
			if (temp.length() < 10) {
				try {
					exe = hb.getcenter("kmeans", temp, "center");
					trans = hb.getsample("kmeans", temp, "record");
					for (int i = 0; i < 4; i++)
						sam[i] = trans[i];
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resultcenter.setText(exe);
				result1.setText(sam[0]);
				result2.setText(sam[1]);
				result3.setText(sam[2]);
				result4.setText(sam[3]);
			} else {
				
				tuple ins = new tuple(temp);
				tuple[] ct = new tuple[5];
				for (int i = 0; i < 5; i++)
					ct[i] = new tuple(centers[i]);
				double dmin = tuple.GetEuclidDist(ins, ct[0]), dis = 0.0;
				int di = 0, sel;
				for (sel = 1; sel < 5; sel++) {
					dis = tuple.GetEuclidDist(ins, ct[sel]);
					if (dmin > dis) {
						dmin = dis;
						di = sel;
					}
				}
				System.out.print(tuple.GetEuclidDist(ins, ct[0])+"-----");
				System.out.print(tuple.GetEuclidDist(ins, ct[1])+"-----");
				System.out.print(tuple.GetEuclidDist(ins, ct[2])+"-----");
				System.out.print(tuple.GetEuclidDist(ins, ct[3])+"-----");
				System.out.print(tuple.GetEuclidDist(ins, ct[4])+" | "+"\n");
				String find = centername[di];
				try {
					exb = hb.getcenter("kmeans", find, "center");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resultcenter.setText(exb);
				try {
					trans = hb.getneibor(temp, "kmeans", find, "record");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < 4; i++)
					sam[i] = trans[i];
				result1.setText(sam[0]);
				result2.setText(sam[1]);
				result3.setText(sam[2]);
				result4.setText(sam[3]);

			}

		}
	}

}
