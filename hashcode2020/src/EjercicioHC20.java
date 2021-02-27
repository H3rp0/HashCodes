import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EjercicioHC20 {
	public static void main(String [] args){
		
		String name[] = {"a_example.txt", "b_read_on.txt","c_incunabula.txt", "d_tough_choices.txt", "e_so_many_books.txt","f_libraries_of_the_world.txt"};
		int indice = 3;
		File ain = new File(name[indice]);
		Scanner fich = null;
		try {
			fich = new Scanner(ain);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		int Nbooks = fich.nextInt();
		int Nlibs = fich.nextInt();
		int Days = fich.nextInt();
		fich.nextLine();
		fich.nextLine();
		int diasRestantes=Days;
		
		FileWriter salida = null;
		String name_salida = "salida"+".out";
		try {
			salida = new FileWriter(name_salida);
		
		
		for(int i=0;i<Nlibs;i++){
			int numeroLibros=fich.nextInt();
			int tiempoSignup=fich.nextInt();
			int librosParalelo=fich.nextInt();
			int librosUsados[]=new int[1000000];
			int libroActual;
			int contador=0;
			int si=1;
			fich.nextLine();
			int numeroLibr=0;
			diasRestantes=diasRestantes-tiempoSignup;
			if(diasRestantes>=0){
				salida.write(i+ " "+ Math.min(diasRestantes*librosParalelo,numeroLibros)+ " \n");
				for(int j=0;j<Math.min(diasRestantes*librosParalelo,numeroLibros);j++){
					libroActual=fich.nextInt();
					for(int h=0;h<contador;h++){
						if(libroActual==librosUsados[h]){
							si=0;
							break;
						}
					}
					if(si==1){
						salida.write(libroActual+" ");
						librosUsados[contador]=libroActual;
						contador++;
					}else{
						si=1;
					}
					
				}
				salida.write("\n");
				numeroLibr++;
				fich.nextLine();
			}else{
				Nlibs=i;
				break;
			}
		}
		salida.close();
		} catch (IOException e) {e.printStackTrace();}	
	}
}