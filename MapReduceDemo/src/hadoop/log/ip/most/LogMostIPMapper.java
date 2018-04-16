package hadoop.log.ip.most;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMostIPMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

	private final static LongWritable one = new LongWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		String ip = line.split(" ")[0];
		context.write(one, new Text(ip));
		
	}
}
