import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HashV3 {
	public static void main(String args[]) {
		int indice=3;
		if (args.length == 1)indice = Integer.parseInt(args[0]);
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
			for(int i=0; i<Nlibs;i++) {
				System.out.println("Dia número: "+current_day+" de "+Days);
				ArrayList<Score> puntos = new ArrayList<Score>();
				ArrayList<Book> aparecidos = new ArrayList<Book>();
				for(int j=0;j<libs.size();j++) {	
					puntos.add( libs.get(j).score(Days, current_day, libs.get(j)) );
				}
				Collections.sort(puntos, new Score());
				if( puntos.get(0).puntos == -10)break;
				int id = puntos.get(0).id;
				salida.write(libs.get(id).real_id + " " + Integer.toString(puntos.get(0).Nbooks) + "\n");
				for(int j=0; j< puntos.get(0).Nbooks;j++) {
					Libreria temp = libs.get(id);
					salida.write(Integer.toString(temp.books.get(j).get_indice()) + " ");
					aparecidos.add(libs.get(id).books.get(j));
				}
				for (int j=0;j<libs.size();j++) {
					if(libs.get(j).books.size() !=0)libs.get(j).books4scoring(aparecidos);
				}
				salida.write("\n");
				current_day +=libs.get(id).sign_up_time;
				
				if(current_day >=Days)break;
				donete.add(libs.remove(id));
				for (int j=0;j<libs.size();j++) {
					libs.get(j).setid(j);
				}
			}
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
