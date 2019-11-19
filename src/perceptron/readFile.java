package perceptron;



import java.io.FileWriter;
import java.util.ArrayList;


public class readFile {

	public static void writingFunc(ArrayList<Double> y) {
		try {
			FileWriter writer = new FileWriter("b.csv", false);
			// запись всей строкиy
			for (int j = 0; j < y.size(); j++) {
				writer.write(Double.toString(y.get(j)));
				// запись по символам
				writer.append('\n');
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}