public class Methods {

    //Метод хорд
    public static String horda(double a, double b, double e) {
        if(f(a) * f(b) > 0) return "в данном интервале нет корней.";
        double x_prev, x_curr = 0;
        do {
            x_prev = x_curr;
            x_curr = (a * f(b) - b * f(a)) / (f(b) - f(a));

            if ((f(x_curr) > 0) && (f(a) < 0)) {
                b = x_curr;
            } else {
                a = b;
                b = x_curr;
            }
                //if ((f(x_curr) > 0) && (f(b) < 0))
//            } else {
//                return "значения функции на концах отрезка имеют одинаковые знаки";
//            }
        } while ((Math.abs(x_curr - x_prev) > e) && (f(x_curr) > e));
        return Double.toString(x_curr);
    }

    // Метод секущих
    public static String sekushie(double x_prev, double x_curr, double e) {
        if(f(x_prev) * f(x_curr) > 0) return "невозможно выбрать начальное приближение";
        double x_next = x_curr - (x_curr - x_prev) * f(x_curr) / (f(x_curr) - f(x_prev));
        while ((Math.abs(x_next - x_curr) > e)){
            x_prev = x_curr;
            x_curr = x_next;
            x_next = x_curr - (x_curr - x_prev) * f(x_curr) / (f(x_curr) - f(x_prev));
        }
        return Double.toString(x_next);
    }

    // Метод простых итераций
    public static String iteraz(double a, double b, double e) {
        double alf[] = proizvod(a, b);
        if(f(a) * f(b) > 0) return "в данном интервале нет корней.";
        double x_curr = alf[1], x_next ;
        x_next = x_curr + alf[0] * f(x_curr);

        while ((Math.abs(x_next - x_curr) > e)) {
            x_curr = x_next;
            x_next = x_curr + alf[0] * f(x_curr);
        }
        return Double.toString(x_next);
    }


    protected static double f(double x) {
        return Math.pow(x, 3) - 2.92 * Math.pow(x, 2) + 1.435 * x + 0.791;
    }

    private static double[] proizvod(double a, double b) {
        double a1, a2, ans[] = new double[3];
        a1 = 3 * Math.pow(a, 2) - 2.92 * 2 * a + 1.435;
        a2 = 3 * Math.pow(b, 2) - 2.92 * 2 * b + 1.435;

        ans[2] = 0;
        if (a1 > a2) {
            if(Math.abs(a1) < 1) ans[2] = 1;
            ans[0] = -1/a1;
            ans[1] = a;
            System.out.println(a1);
        }
        else{
            if(Math.abs(a2) < 1) ans[2] = 1;
            ans[0] = -1/a2;
            ans[1] = b;
            System.out.println(a2);
        }
        return ans;
    }
}