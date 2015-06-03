package frame;
import java.text.*;
public class tuple {
	public static final int DIMENSION = 38;
	// dim数组存下每一维度的信息
	public double dim[];

	static DecimalFormat df = new DecimalFormat("#####0.00000");

	public tuple() {
		dim = new double[DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
			dim[i] = 0;
	}

	// 原字符串类型数据转为tuple结构，忽略协议类型、网络服务类型和连接状态三个字符串类型数据
	public tuple(String s) {
		dim = new double[DIMENSION];
		String[] field = s.split(",");
		String tmp = field[0];
		dim[0] = Double.parseDouble(tmp);
		for (int i = 4; i < DIMENSION + 3; i++) {
			tmp = field[i];
			dim[i - 3] = Double.parseDouble(tmp);
		}
	}

	// 将之前有聚类名的字符串转换为tuple类型
	public static tuple tuple2(String s) {
		String[] st = s.split("\\s+");
		tuple tup = new tuple(st[1]);
		return tup;
	}

	// v1，v2的欧几里得距离
	public static double GetEuclidDist(tuple v1, tuple v2) {
		if (!(v1.dim.length == DIMENSION && v2.dim.length == DIMENSION)) {
			System.exit(1);
		}
		double dist = 0.0;
		for (int i = 0; i < DIMENSION; i++) {
			dist += (v1.dim[i] - v2.dim[i]) * (v1.dim[i] - v2.dim[i]);
		}
		return Math.sqrt(dist);
	}

	// 将v1，v2标准化后的欧几里得距离
	public static double GetStandardEuclidDist(tuple v1, tuple v2,
			double[] min, double[] max) {
		if (!(v1.dim.length == DIMENSION && v2.dim.length == DIMENSION)) {
			System.exit(1);
		}
		double dist = 0.0;
		double[] v1std = new double[DIMENSION];
		double[] v2std = new double[DIMENSION];
		for (int i = 0; i < DIMENSION; i++) {
			if (max[i] != min[i]) {
				// 标准化
				v1std[i] = (v1.dim[i] - min[i]) / (max[i] - min[i]);
				v2std[i] = (v2.dim[i] - min[i]) / (max[i] - min[i]);
			} else
				v1std[i] = v2std[i] = min[i];
		}
		for (int i = 0; i < DIMENSION; i++) {
			dist += (v1std[i] - v2std[i]) * (v1std[i] - v2std[i]);
		}
		return Math.sqrt(dist);
	}

	//v1,v2标准化后的夹角余弦值
	public static double GetCosTheta(tuple v1, tuple v2, double[] min,
			double[] max) {
		if (!(v1.dim.length == DIMENSION && v2.dim.length == DIMENSION)) {
			System.exit(1);
		}
		double dist = 0.0;
		double[] v1std = new double[DIMENSION];
		double[] v2std = new double[DIMENSION];
		double v1m = 0, v2m = 0;
		for (int i = 0; i < DIMENSION; i++) {
			if (max[i] != min[i]) {
				// 标准化
				v1std[i] = (v1.dim[i] - min[i]) / (max[i] - min[i]);
				v2std[i] = (v2.dim[i] - min[i]) / (max[i] - min[i]);
			} else
				v1std[i] = v2std[i] = 0;
			v1m += v1std[i] * v1std[i];
			v2m += v2std[i] * v2std[i];
		}
		for (int i = 0; i < DIMENSION; i++) {
			dist += v1std[i] * v2std[i];
		}
		dist = dist / Math.sqrt(v1m * v2m);
		return dist;
	}

	// 每一维度进行累加
	public tuple Add(tuple v1) {
		for (int i = 0; i < DIMENSION; i++) {
			this.dim[i] += v1.dim[i];
		}
		return this;
	}

	// 转化成字符串，其中协议类型、网络服务类型和连接状态设为字符串“a”
	public String Tostring() {
		String str = df.format(this.dim[0]);
		str += "," + "a";
		str += "," + "a";
		str += "," + "a";// dim[0],a,a,a
		for (int i = 1; i < DIMENSION; i++) {
			str += ',' + df.format(this.dim[i]);
		}
		return str;
	}

	// 每一维度进行除法
	public tuple Divide(int div) {
		if (div == 0) {
			System.exit(1);
		}

		for (int i = 0; i < DIMENSION; i++) {
			this.dim[i] = this.dim[i] / div;
		}
		return this;
	}

}