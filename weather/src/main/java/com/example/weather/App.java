/*package com.example.weather;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;


*//**
 * Hello world!
 *
 *//*
public class App {
	public static void main(String[] args) throws IOException {
		  读入TXT文件   
        //String pathname = "c:\\a.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
        File filename = new File("c:\\a.txt"); // 要读取以上路径的input。txt文件  
        InputStreamReader reader = new InputStreamReader(  
                new FileInputStream(filename)); // 建立一个输入流对象reader  
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
        String line = "";  
        line = br.readLine();  
        while (line != null) {  
            line = br.readLine(); // 一次读入一行数据  
        }  
		//String line="";

		System.out.println(line);
		@SuppressWarnings("null")
		String[] results = line.split(" ");
		@SuppressWarnings("unused")
		int temp;

		for(String result: results) {
			System.out.println(result);
			JSONObject obj=JSONObject.fromObject(result);
			System.out.println(obj);
            //获取返回状态码
			result=obj.getString("reason");
            //如果状态码是200说明返回数据成功
            //if(line!=null&&line.equals("200")){
			result=obj.getString("result");
                //此时result中数据有多个key,可以对其key进行遍历,得到对个属性
                obj=JSONObject.fromObject(result);
                //今日温度对应的key是today
                result=obj.getString("day_temp");
                temp = Integer.parseInt(result.split("℃")[0]);
        		//System.out.println(s1.split("℃")[0]);
			//context.write(new Text("avg_day_temp"), new IntWritable(temp));
			
		}
		Format f = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(today);

		String cityids = "";
		String txt = "";
		Date ndate = null;

		for (int i = 0; i < 3; i++) {
			c.add(Calendar.DAY_OF_MONTH, -1);
			ndate = c.getTime();
			System.out.println(f.format(ndate));
			cityids = GetCityList.weather("146", f.format(ndate));
			txt = cityids + " " + txt;
		}

		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
				// 写入Txt文件
			File writename = new File("c:\\a.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
			writename.createNewFile(); // 创建新文件
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			// out.write("我会写入文件啦\r\n"); // \r\n即为换行
			out.write(txt);
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/


package com.example.weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
 
    	String str ="";  	
    	for(int dtime=-245;dtime<-65;dtime++){
    	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();// 取当前日期。
    	cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, dtime);// 取当前日期的前N天.
    	str =format.format(cal.getTime());	
    	/*String res= GetCityList.weather("94", str);//安溪
*/    	/*String res= GetCityList.weather("119", str);//闽侯*/	  
    	//String res= GetCityList.weather("818", str);哈尔滨
    	//String res= GetCityList.weather("1844", str);上海
    	String res= GetCityList.weather("100", str);//北京
    	JSONObject obj=JSONObject.fromObject(res);
    	
    	String result=obj.getString("result");
         //此时result中数据有多个key,可以对其key进行遍历,得到对个属性
         obj=JSONObject.fromObject(result);
         //今日温度对应的key是today
         String city_id=obj.getString("city_id");//城市地区ID
         String city_name=obj.getString("city_name");//城市地区名称
         String weather_date=obj.getString("weather_date");//天气日期
         String day_weather=obj.getString("day_weather");//	白天天气
         String night_weather=obj.getString("night_weather");//夜间天气
         String day_temp=obj.getString("day_temp");//白天最高温度
         String night_temp=obj.getString("night_temp");//	夜间最低温度
         String day_wind=obj.getString("day_wind");//	白天风向
         String day_wind_comp=obj.getString("day_wind_comp");//	白天风力
         String night_wind=obj.getString("night_wind");//		夜间风向
         String night_wind_comp=obj.getString("night_wind_comp");//	夜间风力
         String day_weather_id=obj.getString("day_weather_id");//	白天天气标识
         String night_weather_id=obj.getString("night_weather_id");//	夜间天气标识
         /*System.out.println(city_name+" "+weather_date+" "+day_weather+" "+night_weather+" "+
        		 day_temp+" "+night_temp+" "+day_wind+" "+day_wind_comp+" "+night_wind+" "+
        		 night_wind_comp+" "+day_weather_id+" "+night_weather_id);*/
         
         List<String> list = new LinkedList<String>();
         list.add(city_id);
         list.add(city_name);
         list.add(weather_date);
         list.add(day_weather);
         list.add(night_weather);
         list.add(day_temp);
         list.add(night_temp);
         list.add(day_wind);
         list.add(day_wind_comp);
         list.add(night_wind);
         list.add(night_wind_comp);
         list.add(day_weather_id);
         list.add(night_weather_id);
         
         File file1 = new File("C:\\weather.txt");
         try {
             FileWriter fw = new FileWriter(file1,true);
             BufferedWriter bw = new BufferedWriter(fw);
          
             for(int i = 0; i<list.size();i++){
                 bw.write(list.get(i).toString()+" ");
                 bw.flush();
                 //System.out.println(list.size());
             }
             bw.newLine();     
             bw.close();
             fw.close();
  
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    	}
}











	/*String sb=StringUtils.substringBefore("26℃", "℃"); 
System.out.println(" is:" +sb);
int i= Integer.parseInt(sb);
int sum=1+i;
System.out.println(sum);

String str="147 仙游 2017-01-01 多云 多云 26℃ 15℃ 无持续风向 ≤3级 无持续风向 ≤3级 01 01";
String[] words = str.split(" ");
String cityname = words[1];
String datatime = words[0];
System.out.println(" is:" + words[0]);
String str ="";
java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();// 取当前日期。
cal = Calendar.getInstance();
cal.add(Calendar.DAY_OF_MONTH, -65);// 取当前日期的前N天.-65,-245
str =format.format(cal.getTime());
System.out.println("yesterday is:" + str);

}}*/