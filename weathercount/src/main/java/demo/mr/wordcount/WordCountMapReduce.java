package demo.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountMapReduce 
{
    public static void main( String[] args ) throws Exception
    {
        Configuration cfg = new Configuration();
        Job job = Job.getInstance(cfg, "worcount");
        job.setJarByClass(WordCountMapReduce.class);
        
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        boolean b = job.waitForCompletion(true);
        if(!b) {
        		System.out.println("wordcount task fail!");
        	
        }
        
    }
}
