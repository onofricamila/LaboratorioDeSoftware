package e2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Bean {

    public void beanToFile(Object object){
        try {
            Class myClass = object.getClass();
            for(Annotation a : myClass.getAnnotations()){
                if(a.annotationType() == Archivo.class){
                    Archivo b = (Archivo) a;
                    FileWriter fileWriter = this.createFile(b.nombre());
                    this.writeFile("<nombreClase>"+myClass.getName()+"</nombreClase>", fileWriter);
                    for(Field f : myClass.getDeclaredFields()){
                        for(Annotation af : f.getDeclaredAnnotations()){
                            if(af.annotationType() == AlmacenarAtributo.class){
                                f.setAccessible(true);
                                this.writeFile("<nombreAtributo>"+f.getName()+"</nombreAtributo>", fileWriter);
                                this.writeFile("<nombreValor>"+f.get(object)+"</nombreValor>", fileWriter);
                            }
                        }
                    }
                    fileWriter.close();
                }
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public FileWriter createFile(String fileName) throws IOException {
        return new FileWriter(fileName, true);
    }

    public void writeFile(Object obj, FileWriter fileWriter) throws IOException {
        fileWriter.write(obj.toString());
        fileWriter.write("\n");
    }

    public static void main(String[] args){
        Bean bean = new Bean();
        bean.beanToFile(new Mapeado());
    }
}
