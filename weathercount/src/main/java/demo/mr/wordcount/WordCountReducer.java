package demo.mr.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import com.sun.tools.javac.util.Convert;

public class WordCountReducer extends Reducer<Text, Text , Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {

		Integer tsum=0;
		Integer nsum=0;String s=null;
		int tavgtemperture=0;
		int navgtemperture=0;
		Text t=null;
		List<Integer> list = new ArrayList();
		int i=0;
		for(Text value :  values) {
			s=value.toString();
			String[] words = s.split("--");   
			tsum += Integer.parseInt(words[0]);
			nsum += Integer.parseInt(words[1]);
			list.add(Integer.parseInt(words[0]));
			list.add(Integer.parseInt(words[1]));
			i++;		
		}
		int min= Collections.min(list);
		int max= Collections.max(list);
		tavgtemperture=tsum/i;
		navgtemperture=nsum/i;
		t =new Text("白天平均温度是"+tavgtemperture+"℃        夜间平均温度是"+navgtemperture+"℃"+"       本月最高温度是"+max+"℃        本月最低温度是"+min+"℃");
		context.write(key,new Text(t));
		
	}


}




/*Integer count = 0;
for(IntWritable value :  values) {
	count+=value.get();
}
context.write(key, new IntWritable(count));*/

/*for(Text value :  values) {
t=value;
}*/
