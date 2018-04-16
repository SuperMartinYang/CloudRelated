package hadoop.log.path.given;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogGivenPathReducer extends Reducer<LongWritable, Text, Text, LongWritable>{

	@Override
	protected void reduce(LongWritable key, Iterable<Text> valueList,
			Reducer<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		String path = "/assets/img/home-logo.png";
		
		int count = 0;
		for (Text value : valueList) {
			if (path.equals(value.toString())) {
				count++;
			}
		}
		
		context.write(new Text(path), new LongWritable(count));
		
	}
}
