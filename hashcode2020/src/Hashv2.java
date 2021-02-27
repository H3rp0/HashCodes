import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hashv2 {
	public static void main(String [] args){
		
		String name[] = {"a_example.txt", "b_read_on.txt","c_incunabula.txt", "d_tough_choices.txt", "e_so_many_books.txt","f_libraries_of_the_world.txt"};
		int indice = 1;
		File ain = new File(name[indice]);
		
		
		Scanner fich = null;
		try {
			fich = new Scanner(ain);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		int Nbooks = fich.nextInt();
		int Nlibs = fich.nextInt();
		int Days = fich.nextInt();
		fich.nextLine();
		
		int Bpoints[] = new int[Nbooks];
		for (int i =0; i<Nbooks;i++) {
			Bpoints[i] = fich.nextInt();
		}
		int aparecido[] = new int[1000000];
		int cnt = 0;
		
		
		FileWriter salida = null;
		String name_salida = "salida"+indice+".out";
		try {
			salida = new FileWriter(name_salida);
			salida.write(Integer.toString(Nlibs) + "\n");
			
			for(int j=0; j<Nlibs; j++) {
				int nbooks = fich.nextInt();
				fich.nextLine();
				int books[] = new int[nbooks];
				int pbooks[] = new int[nbooks];
				int control =0;
				for(int i=0; i<nbooks; i++) {
					int temp = fich.nextInt();
					if(!contiene(aparecido, cnt, temp)) {
						books[control]=temp;
						pbooks[control]=Bpoints[temp];
						aparecido[cnt]=temp;
						cnt++;
						control++;
					}
				}
				ordindice(pbooks, books, control);
				if(control != 0) {
					salida.write(Integer.toString(j)+" ");
					salida.write(Integer.toString(control));
					salida.write("\n");
					for(int i=0; i<control; i++)salida.write(Integer.toString(books[i])+" ");
					salida.write("\n");
				}
				fich.nextLine();
			}
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void ordindice(int valor[], int indices[], int len) {
		int temp;
        for(int r=0;r<len;r++) {
        	valor[r]=valor[r];
        }
        for(int i=1;i < len;i++){
            for (int j=0 ; j < len- 1; j++){
                if (valor[j] > valor[j+1]){
                    temp = valor[j];
                    valor[j] = valor[j+1];
                    valor[j+1] = temp;
                   
                    temp = indices[j];
                    indices[j] = indices[j+1];
                    indices[j+1] = temp;
                }
            }
        }
	}
	
	public static boolean contiene(int lista[], int len, int valor) {
		boolean contiene = false;
		int cnt=0;
		boolean salimos = false;
		if(len==0)salimos=true;
		while(!salimos) {
			if(lista[cnt] == valor) {
				contiene = true;
				salimos = true;
			}
			cnt++;
			if(cnt == len)salimos=true; 
		}
		return contiene;
	}
	
	public static String Int2String(int []vector){
		String h = "";
		int k = 0;
		h = h + Integer.toString(vector[k]);
		for(k=1;k<vector.length;k++)h +=" " + Integer.toString(vector[k]);
		return h;
	}
}
