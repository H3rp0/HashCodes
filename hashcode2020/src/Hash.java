

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hash {
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
		int si = 0;
		
		int Bpoints[] = new int[Nbooks];
		for (int i =0; i<Nbooks;i++) {
			Bpoints[i] = fich.nextInt();
		}
		fich.nextLine();
		
		int Blibs[][] = new int[Nlibs][10001];
		int Slibs[] = new int[Nlibs];
		int Dlibs[] = new int[Nlibs];
		
		int aparecido[] = new int[1000000];
		int cont =0;
		
		int paridad=0;
		int PointsLib[] = new int[Nlibs];
		for(int i=0; i<Nlibs;i++) {
			paridad++;
			Blibs[i][0] = fich.nextInt();
			Slibs[i] = fich.nextInt();
			Dlibs[i] = fich.nextInt();
			fich.nextLine();
			int temp = 0;
			if(paridad == 2) {
				paridad =0;
				fich.nextInt();
				Blibs[i][0] -=1;
			}
			for(int h = 0; h< Blibs[i][0]; h++) {
				Blibs[i][h+1]=fich.nextInt();
				temp += Bpoints[Blibs[i][h+1]];
			}
			PointsLib[i] = temp;
			fich.nextLine();
		}
		int O_Blibs[] = new int[Nlibs];
		for(int i=0; i<Nlibs; i++)O_Blibs[i]=Blibs[i][0];
		int resu[] = new int[PointsLib.length];
		for(int g=0;g<PointsLib.length;g++)resu[g]=g;
		//burbuja(PointsLib, resu);
		int bBlibs[] = new int[Nlibs];
		for(int i=0; i<Nlibs; i++)bBlibs[i] = Blibs[i][0];
		notordindice(bBlibs, resu, Nlibs);
		
		/*
		int mitad=Math.round(Nlibs/2);
		int PointsLibs2[] = new int[mitad];
		for(int i=0; i<mitad; i++) {
			int temp=0;
			for(int h = 0; h< Blibs[ resu[i] ][0]; h++) {
				temp += Bpoints[Blibs[ resu[i] ][h+1]];
			}
			PointsLibs2[i]=temp;
		}
		
		
		notordindice(PointsLibs2, resu, Math.round(Slibs.length/2));
		
		System.out.println(Int1String(PointsLibs2));
		
		
		
		*/
		
		int pbooksXlib[][] = new int[Nlibs][10000];//vector de puntuaciones
		for(int i=0; i<Nlibs; i++) {
			for(int j=0; j<Blibs[i][0]; j++) {
				pbooksXlib[i][j]=Bpoints[ Blibs[i][j+1] ];
			}
			ordindiceB(pbooksXlib[i], Blibs[i], Blibs[i][0]);
		}
		
		
		
		FileWriter salida = null;
		FileWriter daysExcel = null;
		String name_salida = "salida"+indice+".out";
		try {
			salida = new FileWriter(name_salida);
			daysExcel = new FileWriter("Days.txt");
			daysExcel.write(Int1StringExcel(Dlibs));
			
			System.out.println(Blibs[163][0]);
			salida.write(Integer.toString(Nlibs) + "\n");
			for(int g=0;g<Nlibs;g++) {
				
					salida.write(Integer.toString(resu[g])+" ");
					salida.write(Integer.toString(   Blibs[resu[g]][0] )+ "\n");
					salida.write(Int2String( Blibs[resu[g]], Blibs[resu[g]][0]+1) + "\n");
				
			}
			
			daysExcel.close();
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
		
		
		
	}
	
	public static void ordindiceB(int valor[], int indices[], int len) {
		int temp;
        for(int i=1;i < len;i++){
            for (int j=0 ; j < len- 1; j++){
                if (valor[j] < valor[j+1]){
                    temp = valor[j];
                    valor[j] = valor[j+1];
                    valor[j+1] = temp;
                   
                    temp = indices[j+1];
                    indices[j+1] = indices[j+2];
                    indices[j+2] = temp;
                }
            }
        }
	}
	
	public static void notordindice(int valor[], int indices[], int len) {
		int temp;
        for(int i=1;i < len;i++){
            for (int j=0 ; j < len- 1; j++){
                if (valor[j] < valor[j+1]){
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
	public static void ordindice(int valor[], int indices[], int len) {
		int temp;
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
	public static int[] ordenar(int[]valores){	
		int nLibros = valores.length;
		int salida[] = new int[nLibros];
		int j=0;
		for (int k=0;k<nLibros;k++){
			for(int i=0;i<nLibros;i++){
				if(valores[i]>j){
					j=i;	
				}
			}
			salida[k]=j;
			j = 0;
		}
		return salida;
	}
	static String Int2String(int []vector, int longi){
		String h = "";
		int k = 1;
		h = h + Integer.toString(vector[k]);
		for(k=2;k<longi;k++)h +=" " + Integer.toString(vector[k]);
		return h;
	}
	static String Int1String(int []vector){
		String h = "";
		int k = 0;
		h = h + Integer.toString(vector[k]);
		for(k=1;k<vector.length;k++)h +=" " + Integer.toString(vector[k]);
		return h;
	}
	static String Int1StringExcel(int []vector){
		String h = "";
		int k = 0;
		h = h + Integer.toString(vector[k]);
		for(k=1;k<vector.length;k++)h +="\n" + Integer.toString(vector[k]);
		return h;
	}
	public static void burbuja(int[]matrix, int[]resu){
        int temp;
        for(int i=1;i < matrix.length;i++){
            for (int j=0 ; j < matrix.length- 1; j++){
                if (matrix[j] > matrix[j+1]){
                    temp = matrix[j];
                    matrix[j] = matrix[j+1];
                    matrix[j+1] = temp;
                   
                    temp = resu[j];
                    resu[j] = resu[j+1];
                    resu[j+1] = temp;
                }
            }
        }
    }
	public static void burbujaBook(int[]matrix, int[]resu, int longo){
        int temp;
        int copy[] = new int[matrix.length];
        for(int r=0;r<matrix.length;r++) {
        	copy[r]=matrix[r];
        }
        for(int i=1;i < longo;i++){
            for (int j=0 ; j < matrix.length- 1; j++){
                if (copy[j] > copy[j+1]){
                    temp = copy[j];
                    copy[j] = matrix[j+1];
                    copy[j+1] = temp;
                   
                    temp = resu[j];
                    resu[j] = resu[j+1];
                    resu[j+1] = temp;
                }
            }
        }
    }
}

