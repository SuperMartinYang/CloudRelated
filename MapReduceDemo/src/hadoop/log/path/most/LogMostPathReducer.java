package hadoop.log.path.most;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogMostPathReducer extends Reducer<LongWritable, Text, Text, LongWritable>{
	
	@Override
	protected void reduce(LongWritable key, Iterable<Text> valueList,
			Reducer<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		Map<String, Long> pathMap = new HashMap<>();
		
		for (Text value : valueList) {
			String path = value.toString();
			if (pathMap.containsKey(path)) {
				pathMap.put(path, pathMap.get(path) + 1);
			} else {
				pathMap.put(path, (long)1);
			}
		}
		
		List<Map.Entry<String, Long>> pathList = new ArrayList<Map.Entry<String, Long>>(pathMap.entrySet());
		Collections.sort(pathList, new Comparator<Map.Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		String pathResult = null;
		long hitResult = 0;
		for (Entry<String, Long> entry : pathList) {
			pathResult = entry.getKey();
			hitResult = entry.getValue();
			break;
		}
		
		context.write(new Text(pathResult), new LongWritable(hitResult));
	
	}
	
}
