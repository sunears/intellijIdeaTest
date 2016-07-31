package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Converter {

	Scanner scanner;
	PrintWriter writer;
	ArrayList<srtRecord> records;
	private float fps;
	
	
	public Converter(String source, String destination)
	{
		
		
		
		try {
			scanner = new Scanner(new File(source));
	        scanner.useDelimiter("\\n");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			writer = new PrintWriter(new File(destination), "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		records = new ArrayList<srtRecord>();
		
	}
	
	public void readFile()
	{
		int titNumber = 1;
		String tmp = "";
		while(scanner.hasNext())
		{
			tmp = scanner.next();
			if(srtRecord.parseTxtLine(titNumber, tmp, fps)!=null)
			{
				records.add(srtRecord.parseTxtLine(titNumber, tmp, fps));
				titNumber++;
			}
		}
		scanner.close();
	};
	
	
	public void writeFile()
	{
		for (srtRecord record : records)
		{
			System.out.println(record.toString());
			writer.print(record.toString());
		}
		writer.close();
		//czysci tablice
		records.clear();

	}

	public float getFps() {
		return fps;
	}

	public void setFps(float fps) {
		this.fps = fps;
	};
	
	
	
}
