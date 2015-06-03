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
import java.nio.ByteBuffer;

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
import org.apache.hadoop.hbase.generated.master.table_jsp;
import org.apache.hadoop.hbase.thrift2.generated.THBaseService.put_args;
import org.apache.hadoop.hbase.util.Bytes;

public class H_test1 {
	public static int cnt=0;
	
	
	private static Configuration conf = null;
	static {
		conf = HBaseConfiguration.create();
	}
	
	@SuppressWarnings("deprecation")
	public static void ReadAndInsert(String filepath) throws IOException{
		//read from file:"result";
		
		File F1=new File(filepath);
		BufferedReader Br1 = new BufferedReader(new FileReader(F1));
		String str= null;
		HTable table = new HTable(conf, "kmeans");
		//auto flush false,buffer size 20MB
		table.setAutoFlush(false);
		table.setWriteBufferSize(209715200);
		while((str=Br1.readLine()) != null){
			
			//start buffer insert
			
			//0	0,tcp,http,SF,200,527,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,2,0.00,0.00,0.00,0.00,1.00,0.00,1.00,1,255,1.00,0.00,1.00,0.02,0.00,0.00,0.00,0.00,normal.
			String[] field = str.split("\t");
			//put in
			Put put1=new Put(Bytes.toBytes(field[0]));
			put1.setWriteToWAL(false);
			String l=Integer.toString(cnt);
			put1.add(Bytes.toBytes("record"),Bytes.toBytes(l),Bytes.toBytes(field[1]));
			cnt++;
			//System.out.println("***");
			table.put(put1);
		}
		table.flushCommits();
	}
	//main
	public static void main(String[] args) throws IOException{
		//setup filepath
		String filepath = "/home/choi/Desktop/result_5";
		//String filepath = "/home/choi/Desktop/final-cen5";
		//String filepath = "/home/choi/Desktop/test";
		ReadAndInsert(filepath);
	}
}
