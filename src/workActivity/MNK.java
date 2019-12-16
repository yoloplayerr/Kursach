package workActivity;

import urlParser.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MNK {
    public static float[] mnk(ArrayList<Data> y,String name) {
        float[] r = new float[2];
        
        float sum_y = 0;
        float sum_x_sqr = 0;
        float sum_xy = 0;
        float sum_x = 0;

        float[] x = new float[y.size()];

        for (int i = 0; i < y.size(); i++) {
            x[i] = i;
            sum_y += y.get(i).value(name);
            sum_x_sqr += x[i] * x[i];
            sum_xy += x[i] * y.get(i).value(name);
            sum_x += x[i];
        }
        r[0] = (y.size() * sum_xy - sum_x * sum_y) / (y.size() * sum_x_sqr - sum_x * sum_x);
        r[1] = (sum_y - r[0] * sum_x) / y.size();
        return r;
    }

    public static float[] sqrtStat(ArrayList<Data> y,String name) {
        float[] r = new float[3];
        float n = y.size();
        float sum_y = 0;
        float sum_xy = 0;
        float sum_x2y = 0;
        float sum_x = 0;
        float sum_x_2 = 0;
        float sum_x_3 = 0;
        float sum_x_4 = 0;
        for (int i = 0; i < n; i++) {
            sum_y += y.get(i).value(name);
            sum_xy += y.get(i).value(name) * i;
            sum_x2y += y.get(i).value(name) * i * i;
            sum_x += i;
            sum_x_2 += i * i;
            sum_x_3 += i * i * i;
            sum_x_4 += i * i * i * i;
        }
        float det = n * sum_x_2 * sum_x_4 + sum_x_3 * sum_x * sum_x_2 + sum_x_3 * sum_x * sum_x_2
                - sum_x_2 * sum_x_2 * sum_x_2 - sum_x_3 * sum_x_3 * n - sum_x_4 * sum_x * sum_x;
        float det1 = n * sum_x_2 * sum_x2y + sum_y * sum_x * sum_x_3 + sum_xy * sum_x * sum_x_2
                - sum_y * sum_x_2 * sum_x_2 - sum_x_3 * n * sum_xy - sum_x2y * sum_x * sum_x;
        float det2 = n * sum_xy * sum_x_4 + sum_x2y * sum_x * sum_x_2 + sum_x_3 * sum_y * sum_x_2
                - sum_x_2 * sum_x_2 * sum_xy - sum_x_3 * sum_x2y * n - sum_x_4 * sum_y * sum_x;
        float det3 = sum_y * sum_x_2 * sum_x_4 + sum_x_3 * sum_xy * sum_x_2 + sum_x_3 * sum_x * sum_x2y
                - sum_x_2 * sum_x_2 * sum_x2y - sum_x_3 * sum_x_3 * sum_y - sum_x_4 * sum_x * sum_xy;
        r[0] = det1 / det;
        r[1] = det2 / det;
        r[2] = det3 / det;

        return r;
    }

    public static float[] expStat(ArrayList<Data> y,String name) {
        float[] r = new float[2];
        float n = y.size();
        float sum_lny = 0;
        float sum_x2 = 0;
        float sum_lny_x = 0;
        float sum_x = 0;
        for (int i = 0; i < n; i++) {
            sum_lny += Math.log(y.get(i).value(name));
            sum_lny_x += Math.log(y.get(i).value(name)) * i;
            sum_x2 += i * i;
            sum_x += i;
        }
        r[0] = (sum_lny * sum_x2 - sum_lny_x * sum_x) / (n * sum_x2 - sum_x * sum_x);
        r[1] = (n * sum_lny_x - sum_lny * sum_x) / (n * sum_x2 - sum_x * sum_x);
        return r;
    }

    public static float[] hyperStat(ArrayList<Data> y,String name){
        float[] r= new float[3];
        int n = y.size();
        float sum_y_x = 0;
        float sum_1_x = 0;
        float sum_y = 0;
        float sum_1_x2 = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                sum_1_x += 1 / (i);
                sum_1_x2 += 1 / ((i) * (i));
                sum_y_x +=  y.get(i).value(name) / (i);
            }
            sum_y += y.get(i).value(name);
        }
        r[1] = (n * sum_y_x - sum_1_x * sum_y) / (n * sum_1_x2 - sum_1_x * sum_1_x);
        r[0] = sum_y/n - r[1]*sum_1_x/n;
        return r;
    }
}
