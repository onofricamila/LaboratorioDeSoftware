package e6;

public class PaintTest {
    public static void main(String[] args) {
        Paint p = new Paint();
        p.init();
        FiguraGeometrica[] fg = p.getPaleta();


        for (int i = 0; i < fg.length; i++) {
            FiguraGeometrica f = fg[i];
            System.out.println(f + " ------------> con area " + f.area());
            if (f instanceof Circulo ) {
                Circulo casted = (Circulo)f;
                System.out.println("                             ----> con radio " + casted.getRadio());
            }
        }
    }
}
