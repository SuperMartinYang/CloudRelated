package hadoop.ngram.demo;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NGramMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	private final static LongWritable one = new LongWritable(1); 
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		
		Parser p = new Parser();
		
		String line = value.toString().replaceAll("[^a-zA-Z]", " ").toLowerCase().trim();
		
		long n = Long.parseLong(context.getConfiguration().get("size")); 

		String[] grams = p.ngrams(n, line); 
				
		for (String gram : grams) {
			context.write(new Text(gram), one);
		}
	}
}
