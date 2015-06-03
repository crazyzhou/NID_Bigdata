package accurate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileReader;

public class Test_Accurate {
	
	public static String[] name={"back.","buffer_overflow.","ftp_write.","guess_passwd."
		,"imap.","ipsweep.","land.","loadmodule.","multihop.","neptune.","nmap.","normal.","perl."
		,"phf.","pod.","portsweep.","rootkit.","satan.","smurf.","spy.","teardrop.","warezclient."
		,"warezmaster."};

	public static double Test(String filepath1)
			throws IOException {
		double result = 0;
		int count = 0;
		File f1 = new File(filepath1);

		BufferedReader br1 = new BufferedReader(new FileReader(f1));
	
		String s1 = null;
	
		while ((s1 = br1.readLine()) != null) {
//			System.out.println(s1);
			String[] field = s1.split("\t");
//			System.out.println(field[0]);
			String[] field1 = field[1].split(",");
//			System.out.println(field1[41]);
			for (int i = 0; i < 23; i++) {
//				System.out.println(Integer.toString(i) == field[0]);
//				System.out.print(field1[41] == name[i]);
//				System.out.println(name[i]);
				if (i == Integer.parseInt(field[0])
					&& (field1[41].equals(name[i]))) {
	//				System.out.println("***");
					count++;
	//				System.out.println("***");
				}
			}
		}
		br1.close();
//		System.out.println(count);
		result = (double)count / 494022;
		return result;
	}
	public static void main(String[] args){
		String filepath1="/home/choi/Desktop/part-r-00000_old";
		
		double result=0;
		
		try {
			result = Test(filepath1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(result);
	}
}
