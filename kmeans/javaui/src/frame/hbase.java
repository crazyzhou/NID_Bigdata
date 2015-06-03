package frame;


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

	public class hbase {

		private static Configuration conf = null;  
		static{
			conf = HBaseConfiguration.create();
		}
		
		public String getcenter(String tablename,String rowkey,String columnfamily) throws IOException
		{
			HTable table = new HTable(conf,tablename);
			Get get=new Get(rowkey.getBytes());
			get.addFamily(columnfamily.getBytes());
			Result r=table.get(get);
			
			String back=new String();
			for(KeyValue kv:r.raw())
			{
				//String keyvalue=new String(kv);
				String row=new String(kv.getRow());
				String famString=new String(kv.getFamily());
				String valString=new String(kv.getValue());
				back=row + "   " + valString;
				//System.out.println("KeyValue---"+kv);
				//System.out.println("row=>"+new String(kv.getRow()));
				//System.out.println("family=>"+new String(kv.getFamily())+" : "+new String(kv.getValue()));
			}
			return back;
		}
		
		public String[] getsample(String tablename,String rowkey,String columnfamily) throws IOException
		{
			HTable table = new HTable(conf,tablename);
			Get get=new Get(rowkey.getBytes());
			get.addFamily(columnfamily.getBytes());
			Result r=table.get(get);
			
			String[] back=new String[4];
			int i=0;
			for(KeyValue kv:r.raw())
			{
				//String keyvalue=new String(kv);
				String row=new String(kv.getRow());
				//String famString=new String(kv.getFamily());
				String valString=new String(kv.getValue());
				back[i]=row + "   " + valString;
				//System.out.println("KeyValue---"+kv);
				//System.out.println("row=>"+new String(kv.getRow()));
				//System.out.println("family=>"+new String(kv.getFamily())+" : "+new String(kv.getValue()));
				i++;
				if(i==4)break;
				
			}
			return back;
		}
		
		public String[] getneibor(String point,String tablename,String rowkey,String columnfamily) throws IOException
		{
			HTable table = new HTable(conf,tablename);
			Get get=new Get(rowkey.getBytes());
			get.addFamily(columnfamily.getBytes());
			Result r=table.get(get);
			tuple query=new tuple(point);
			String[] back=new String[4];
			String[] top4=new String[4];
			String row=new String();
			int i = 0,first=0;
			for(KeyValue kv:r.raw())
			{
				//String keyvalue=new String(kv);
				row=new String(kv.getRow());
				//String famString=new String(kv.getFamily());
				String valString=new String(kv.getValue());
				tuple temp=new tuple(valString);
				double com=tuple.GetEuclidDist(query, temp);
//				if(first<4)
//				{
//					top4[first]=valString;
//					first++;
//				}
//				if(first==4)
//				{
//					for(int ii=0;ii<4;ii++)
//					{
//						tuple temi=new tuple(top4[ii]);
//						for(int j=ii;j<4;j++)
//						{
//							tuple temj=new tuple(top4[j]);
//							if(tuple.GetEuclidDist(query, temi)>tuple.GetEuclidDist(query, temj))
//							{
//								String tems=top4[j];
//								top4[j]=top4[ii];
//								top4[ii]=tems;
//							}
//						}
//					}
//					first++;
//				}
//				if(first>4)
//				{
//					for(int iq=0;iq<4;iq++)
//					{
//						tuple t=new tuple(top4[iq]);
//						if(com<tuple.GetEuclidDist(query, t))
//						{
//							for(int inside=4;inside>iq;inside--)
//							{
//								top4[inside]=top4[inside-1];
//							}
//							top4[iq]=valString;
//						}
//					}
//				}
				if(com<40.0)
				{
					back[i]=row + "   " + valString;
					i++;
				}
				//System.out.println("KeyValue---"+kv);
				//System.out.println("row=>"+new String(kv.getRow()));
				//System.out.println("family=>"+new String(kv.getFamily())+" : "+new String(kv.getValue()));
				
				if(i==4)break;
				
			}
//			for(int c=0;c<4;c++)
//			{
//				back[c]=row + "  "+top4[c];
//			}
			return back;
		}
		
		
		public static void main(String[] args) throws IOException
		{
			hbase tt=new hbase();
			String tablename="kmeans";
			String row="DOS";
			//String family="center";
			
			System.out.println("=======query a family=======");  
			String use=tt.getcenter(tablename, row, "center");
			System.out.println(use);
			
		}

}
