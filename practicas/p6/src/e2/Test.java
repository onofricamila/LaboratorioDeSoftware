package e2;

public class Test {
    public static void main(String[] args) {
        // Veterinaria <Animal> vet = new Veterinaria <Gato>();

        // Veterinaria <Gato> vet = new Veterinaria <Animal>();

        // Veterinaria <?> vet = new Veterinaria<Gato>();
        // vet.setAnimal(new Gato());

        Veterinaria vet1 = new Veterinaria ();
        vet1.setAnimal(new Perro());

        // Veterinaria vet = new Veterinaria <?>();

        Veterinaria <? extends Animal> vet2 = new Veterinaria<Gato>();
        // no anda esto de setearle algo
         // vet2.setAnimal(new Gato());
    }
}
