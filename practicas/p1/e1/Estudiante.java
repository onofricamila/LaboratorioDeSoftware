package practicas.p1.e1;

public class Estudiante {
    private String nombre;
    private String apellido;
    private Integer legajo;

    public Estudiante(String nombre, String apellido, Integer legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    /* To improve performance, instead of using string concatenation, use StringBuffer.append().
    String objects are immutable—they never change after creation. */
    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer( "Estudiante{" );
        tmp.append(  nombre );
        tmp.append(  apellido );
        tmp.append(  legajo );
        tmp.append(   '}' );

        return tmp.toString();
    }

    public static void main(String[] args){
        Estudiante [] vector;
        Estudiante e = new Estudiante("camila", "onofri", 137356);
        System.out.print(e);
    }
}
