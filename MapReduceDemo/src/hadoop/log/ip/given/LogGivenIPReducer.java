package hadoop.log.ip.given;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogGivenIPReducer extends Reducer<LongWritable, Text, Text, LongWritable> {

	@Override
	protected void reduce(LongWritable key, Iterable<Text> valueList,
			Reducer<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		String ip = "10.153.239.5";
		long count = 0;
		for (Text value : valueList) {
			 if (ip.equals(value.toString())) {
				count++;
			}
		}
		
		context.write(new Text(ip), new LongWritable(count));
		
	}

}
