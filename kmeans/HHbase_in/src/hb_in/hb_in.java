package hb_in;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.thrift2.generated.THBaseService.put_args;
import org.apache.hadoop.hbase.util.Bytes;

public class hb_in {

	public static int cnt=0;
	public static String[] name={"back.","buffer_overflow.","ftp_write.","guess_passwd."
		,"imap.","ipsweep.","land.","loadmodule.","multihop.","neptune.","nmap.","normal.","perl."
		,"phf.","pod.","portsweep.","rootkit.","satan.","smurf.","spy.","teardrop.","warezclient."
		,"warezmaster."};
	
	private static Configuration conf = null;
	static {
		conf = HBaseConfiguration.create();
	}
    public static void insertstring() throws IOException{
	// string 1 test	
    HTable table =new HTable(conf, "kmeans");
	String stest = "1	0,tcp,http,SF,208,589,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,14,25,0.00,0.00,0.00,0.00,1.00,0.00,0.08,14,255,1.00,0.00,0.07,0.02,0.00,0.00,0.00,0.01,normal.";
	String[] field1=stest.split("\t");
	int change = Integer.parseInt(field1[0]);
    Put put1=new Put(Bytes.toBytes(name[change-1]));
    String l=Integer.toString(cnt);
    put1.add(Bytes.toBytes("record"),Bytes.toBytes(l),Bytes.toBytes(field1[1]));
    cnt++;
    table.put(put1);
    }
//    public static void main(String[] args) throws IOException{
//    	insertstring();
//    }
  //	public static void ReadAndInsert(String filepath) throws IOException {
//		File f1 = new File(filepath);
//
//		BufferedReader br1 = new BufferedReader(new FileReader(f1));
//		String str = null;
//		while ((str = br1.readLine()) != null) {
//			
//		}
//
//	}

}
