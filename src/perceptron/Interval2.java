package perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Interval2 {
	
	// Основные переменные
	private static String fname = "v1.csv"; // файл с данными
	public static float k[][] = new float[178][4];
	
	
	public static int nullCount=0;
	public static float data[][]=new float[k.length-nullCount][4];
	// разделитель данных в файле
	private static final String separator = ";";

	// Основная программа
	public static void logData() {

		// Считаем данные из файла
		readingData();
		data= new float[k.length-nullCount][4];
		
		for (int i = 0; i < 178-nullCount; i++) {
			for (int j = 0; j < 4; j++) {
				data[i][j]=k[i][j];
				
//				System.out.print(data[i][j] + "\t");
			}
//			System.out.println("");
		}
	}

	// Прочитать данные из файла в массив
	public static void readingData() {
		File file = new File(fname);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));) {
			String line = "";
			int i = 0;
			
			// Считываем файл построчно \n
			line = br.readLine();
			while ((line = br.readLine()) != null) {

				if (line.charAt(0) == '2') {
					line = line.replaceFirst("2", "0");
				}
				String[] elements = line.split(separator);

				if ((elements[1].charAt(0) == '0')) {
					if ((elements[2].charAt(0) == '0')) {
						if ((elements[3].charAt(0) == '0')) 	{							
							nullCount++;
							continue;
						}						
					}
				} 												 				
				k[i][0] = Float.parseFloat(elements[0]);
				k[i][1] = Float.parseFloat(elements[1]);
				k[i][2] = Float.parseFloat(elements[2]);
				k[i][3] = Float.parseFloat(elements[3]);							
				i++;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}