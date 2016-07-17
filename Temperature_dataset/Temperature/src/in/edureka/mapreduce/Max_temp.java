
package in.edureka.mapreduce;


import java.io.IOException; 
import java.util.StringTokenizer; 




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

 

/**

* The Temperature program finds out the maximum temperature in every year from the given input file. 
* The output data is in the form of �Year and Maximum temperature corresponding to that year".
* We write a map reduce code to achieve this, where mapper makes key value pair from the input file
* and reducer does aggregation on this key value pair. 
*/

public class Max_temp { 
	
	/** 
	 * @author edureka!
	 * @interface Mapper
	 * <p>Map class is static and extends MapReduceBase and implements Mapper 
	 * interface having four hadoop generics type LongWritable, Text, Text, 
	 * IntWritable.
	 */
 
	public static class Map extends
	Mapper<LongWritable, Text, Text, IntWritable> {
    	//Mapper
		
		
    
    	
    	 
    	Text k= new Text(); 

    	
    	
        @Override 
        public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {

        		
                String line = value.toString(); 
                StringTokenizer tokenizer = new StringTokenizer(line," "); 
 
                while (tokenizer.hasMoreTokens()) { 

               
            	String year= tokenizer.nextToken();
            	k.set(year);

            	
            	String temp= tokenizer.nextToken().trim();

            	    	
            	int v = Integer.parseInt(temp); 

                context.write(k,new IntWritable(v)); 
            } 
        } 
    } 
 
    //Reducer
	
    
    
	public static class Reduce extends
	Reducer<Text, IntWritable, Text, IntWritable> {

    	 
        @Override 
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
		throws IOException, InterruptedException {
        	
        	int maxtemp=0;
            for(IntWritable it : values) { 

           
            int temperature= it.get();
            	if(maxtemp<temperature)
            	{
            		maxtemp =temperature;
            	}
            } 
            
            context.write(key, new IntWritable(maxtemp)); 
        } 
 
    } 
    
  //Driver
	

    
 
    public static void main(String[] args) throws Exception { 
 
    	
		
		Configuration conf = new Configuration();
		
		
		
		
		Job job = new Job(conf, "Max_temp");
		
		
		job.setJarByClass(Max_temp.class);
		
	
		
		job.setMapperClass(Map.class);
		
		
		job.setReducerClass(Reduce.class);
		
		
		job.setOutputKeyClass(Text.class);
		
		
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		
		
		job.setOutputFormatClass(TextOutputFormat.class);
 
        
		
        Path outputPath = new Path(args[1]);
		
       
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		outputPath.getFileSystem(conf).delete(outputPath);
		
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
							
 
    } 
}
