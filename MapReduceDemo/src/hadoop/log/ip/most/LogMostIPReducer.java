package hadoop.log.ip.most;

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

public class LogMostIPReducer extends Reducer<LongWritable, Text, Text, LongWritable> {

	@Override
	protected void reduce(LongWritable key, Iterable<Text> valueList,
			Reducer<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		Map<String, Long> ipMap = new HashMap<>();
		
		for (Text value : valueList) {
			String ip = value.toString();
			if (ipMap.containsKey(ip)) {
				ipMap.put(ip, ipMap.get(ip) + 1);
			} else {
				ipMap.put(ip, (long) 1);
			}
		}
		
		List<Map.Entry<String, Long>> ipList = new ArrayList<Map.Entry<String, Long>>(ipMap.entrySet());
		Collections.sort(ipList, new Comparator<Map.Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		String ipResult = null;
		long hitResult = 0;
		for (Entry<String, Long> entry : ipList) {
			ipResult = entry.getKey();
			hitResult = entry.getValue();
			break;
		}
		
		context.write(new Text(ipResult), new LongWritable(hitResult));
		
//		int count = 0;
//		for (LongWritable value : valueList) {
//			count += value.get();
//		}
//		
//		
//		
//		context.write(key, new LongWritable(count));
		
	}

}
