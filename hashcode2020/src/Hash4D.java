import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hash4D {
	public static void main(String args[]) {
		int indice=3;
		//if (args.length == 1)indice = Integer.parseInt(args[0]);
		System.out.println("Empezamos;");
		String name[] = {"a_example.txt", "b_read_on.txt","c_incunabula.txt", "d_tough_choices.txt", "e_so_many_books.txt","f_libraries_of_the_world.txt"};
		File ain = new File(name[indice]);
		
		
		Scanner fich = null;
		try {
			fich = new Scanner(ain);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		int Nbooks = fich.nextInt();
		int Nlibs = fich.nextInt();
		int Days = fich.nextInt();
		fich.nextLine();
		
		ArrayList<Book> books = new ArrayList<Book>();
		for (int i =0; i<Nbooks;i++) {
			books.add(new Book(i, fich.nextInt()));
		}
		fich.nextLine();
		ArrayList <Libreria> libs = new ArrayList<Libreria>();
		for(int i=0;i<Nlibs;i++) {
			int temp = fich.nextInt();
			libs.add(new Libreria(temp, fich.nextInt(), fich.nextInt(), i));
			
			fich.nextLine();
			for(int j=0; j<temp; j++) {
				libs.get(i).add_book( books.get( fich.nextInt() ) );
			}
			libs.get(i).sort();
			fich.nextLine();
		}
		fich.close();
		System.out.println("Lectura terminada;\nEmpieza el procesado;");
		//ArrayList<Book> aparecidos = new ArrayList<Book>();
		ArrayList<Libreria> donete = new ArrayList<Libreria>();
		String name_salida = "salida"+indice+".out";
		int current_day=0;
		try {
			FileWriter salida = new FileWriter(name_salida);
			for(int i=1; i<libs.size();i+=0) {
				System.out.println("Dia número: "+libs.size()+" hasta 0 ");
				Collections.sort(libs, new CompareNbooks());
				current_day += libs.get(0).sign_up_time;
				if(current_day >=Days | libs.get(0).Nbooks == 0)break;
				ArrayList<Book> aparecidos = new ArrayList<Book>();
				for(int j=0; j<libs.get(0).Nbooks; j++)aparecidos.add( libs.get(0).books.get(j) );
				donete.add(libs.remove(0));
				for(int j=0; j<libs.size(); j++) {
					if(libs.get(j).Nbooks == 0) {
						libs.remove(j);
						j--;
					}else libs.get(j).books4scoring(aparecidos);
				}
			}
			salida.write(donete.size()+"\n");
			for(int i=0; i<donete.size(); i++) {
				Libreria temp = donete.get(i);
				salida.write(temp.id + temp.Nbooks + "\n");
				for(int j=0; j<temp.Nbooks; j++)salida.write(temp.books.get(j).indice + " ");
				salida.write(" ");
			}
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
