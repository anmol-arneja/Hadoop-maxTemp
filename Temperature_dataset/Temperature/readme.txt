The dataset for this problem is provided in file.
The src file contains a map reduce programs:]
Max_temp.java : This program finds out the maximum temperature in every year from the given input file. The output for this program is stored in output file.

Instructions for running this program 
First create a jar file and the add the external jars given in lib folder
The input file Temperature should be in hdfs and can be copied to hdfs using following commad:
hdfs dfs -copyFromLocal '[Local location where you have saved file Temperature]'
Run the jar giving following commands

cd /[Location where your jar is stored]
hadoop jar [your created jar file] [input data in file stored in hdfs] [output file where output is to be stored]