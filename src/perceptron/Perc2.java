package perceptron;

import java.util.ArrayList;

public class Perc2 {
	static double[] x;

	static double y;
	static double s;
	static double[][] wxh;
	static double[] why;
	static double[] h;
	static double[] se;
	static double[][] pat;
	static double[] r;
	static double alpha = 0.5;
	static double sum;
	static double sumFirst[];

	static ArrayList<Double> kek = new ArrayList<Double>();

	public Perc2() {
		Interval2.logData();
		int length = Interval2.data.length;

		pat = new double[length][3];
		r = new double[length];
		s = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 3; j++) {
				pat[i][j] = Interval2.data[i][j + 1];

			}
		}
		for (int i = 0; i < length; i++) {
			r[i] = Interval2.data[i][0];

		}

		x = new double[pat[0].length];

		se = new double[3];
		h = new double[3];

		wxh = new double[x.length][h.length];
		why = new double[h.length];
		init();

		study();
		

	}

	public void init() {
		System.out.println("Начальные значения весов первого слоя");
		for (int i = 0; i < wxh.length; i++) {
			for (int j = 0; j < wxh[i].length; j++) {
				wxh[i][j] = Math.random() * 0.3 + 0.08;
				System.out.println("wxh[" + i + "][" + j + "]=" + wxh[i][j]);
			}
		}

		System.out.println("Начальные значения весов второго слоя");
		for (int i = 0; i < why.length; i++) {
			why[i] = Math.random() * 0.3 + 0.08;
			System.out.println("why[" + i + "]=" + why[i]);
		}
	}

	public void cy() {

		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < x.length; j++) {
				se[i] += x[j] * wxh[j][i];
			}
			h[i] = sigmoid(se[i]);

		}

		s = 0;
		for (int i = 0; i < h.length; i++) {
			s += h[i] * why[i];
		}

		y = sigmoid(s);
		sumFirst = sumFirst();

		sum = sumSecond();
	}

	private static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public void study() {
		int m = 0;
		double error = 0;
		double sumError = 0;
		for (int p = 0; p < pat.length; p++) {

			for (int i = 0; i < x.length; i++) {
				x[i] = pat[p][i];
			}

			for (int i = 0; i < 100000; i++) {
				cy();
				error = r[p] - y;
				sumError = +error;
				for (int j = 0; j < h.length; j++) {
					for (int z = 0; z < h.length; z++) {
						wxh[j][z] = wxh[j][z] + 2 * alpha * sumError * why[z] * x[j] * sigmoid(sum) * (1 - sigmoid(sum))
								* sigmoid(sumFirst[z]) * (1 - sigmoid(sumFirst[z]));
					}
					why[j] = why[j] + 2 * alpha * sumError * sigmoid(sum) * (1 - sigmoid(sum)) * h[j];
				}
				m++;

			}
			cy();
			System.out.println("наше значение=" + y + " нужное значение" + r[p]);

			kek.add(y);

		}
		readFile.writingFunc(kek);
		System.out.println("Количество итераций равно" + m);
		System.out.println("wxh[0][0]=" + wxh[0][0]);
		System.out.println("wxh[0][1]=" + wxh[0][1]);
		System.out.println("wxh[0][2]=" + wxh[0][2]);
		System.out.println("wxh[1][0]=" + wxh[1][0]);
		System.out.println("wxh[1][1]=" + wxh[1][1]);
		System.out.println("wxh[1][2]=" + wxh[0][2]);
		System.out.println("wxh[2][0]=" + wxh[2][0]);
		System.out.println("wxh[2][1]=" + wxh[2][1]);
		System.out.println("wxh[2][2]=" + wxh[2][2]);
		System.out.println("wxh[2][2]=" + wxh[2][2]);

		System.out.println("why[0]=" + why[0]);
		System.out.println("why[1]=" + why[1]);
		System.out.println("why[2]=" + why[2]);

	}

	public static double sumSecond() {
		sum = 0;
		for (int i = 0; i < h.length; i++) {
			sum += h[i] * why[i];
		}
		return sum;
	}

	public static double[] sumFirst() {
		sumFirst = new double[h.length];
		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < h.length; j++) {
				sumFirst[i] = sumFirst[i] + x[i] * wxh[j][i];
			}

		}

		return sumFirst;
	}

	public static void main(String[] args) {

		Perc2 perc2 = new Perc2();

	}

}
