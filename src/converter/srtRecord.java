package converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class srtRecord {

	// number of the title
	private int number;
	// time at which the title appears 
	private float startTime;
	// time t which the title disappears
	private float stopTime;
	//Lines of text to display
	private String lines;
	
	
	
	public srtRecord()
	{
		number = 0;
		startTime = 0;
		stopTime = 0; 
		lines = "";
	}

	// Dzia�a tylko dla napis�w w formacie MPL2 -> SRT
	public static srtRecord parseTxtLine(int titleNumber,String txtLine, float fps)
	
	{
		 float start=0;
		    float stop=0;
		    String text ="";
		
		srtRecord object = new srtRecord();
		Pattern p= Pattern.compile("^\\D(\\d*)\\D\\D(\\d*)\\D(.*)$");
		Matcher m = p.matcher(txtLine);
		if (m.find()) {
			System.out.print(m+m.group(1));
		     start = (Float.valueOf(m.group(1))*0.1f);
		     stop = (Float.valueOf(m.group(2))*0.1f);
		     text = m.group(3);
		}
		else
		{
			System.out.println("Warning! Reading one of the lines failed");
			return null;
		}
		// nie wiem co / robi ale na pewno tego nie widac na ekranie
		text = text.replace("/", "");
		text = text.replace("|", "%n");
		text = String.format(text);
		

		object.number = titleNumber;
		object.startTime = start;
		object.stopTime = stop;
		object.lines = text;
		
		//System.out.println(object.toString());
		
		return object;
		
	}
	
	
	private String timeFormat(float value)
	{
		String time ="";
		int minutes = (int) ( value/60)%60;
		int hours = (int) minutes/60;
		String decimalVal = String.format("%.3f",Float.valueOf(value%1)).substring(2);
		time = String.format("%02d:%02d:%02d,"+ decimalVal, hours,minutes,(int) value%60);
		return time;
	}
	
	
	@Override
	public String toString()
	{
		String box ="";
		//number of the title
		box = box + this.number + "%n";
		// time of display
		box = box + timeFormat(this.startTime) + " --> " + timeFormat(this.stopTime)  + "%n";
		//content of the title
		box += this.lines;
		//gap
		box += "%n%n";
		
		return String.format(box);
	}
	
	
}
