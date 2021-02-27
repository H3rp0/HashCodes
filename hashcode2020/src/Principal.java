import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
	public static void main(String [] args){
		String name[] = {"a_example.in", "b_small.in","c_medium.in", "d_quite_big.in", "e_also_big.in"};
		int indice = 4;
		File ain = new File(name[indice]);
		Scanner fich = null;
		try {
			fich = new Scanner(ain);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		//String lectura = fich.nextLine();
		
		int maximo = fich.nextInt();
		int types = fich.nextInt();
		fich.nextLine();
		int pizzas[] = new int[types];
		int i;
		for(i=0;i<types;i++){
			pizzas[i] = fich.nextInt();
		}
		fich.close();
		
		System.out.println(Integer.toString(maximo));
		System.out.println(InttoString(pizzas));
		
		int c_usados = 0;
		int v_usados[] = new int[types];
		for(int y = 0; y < types; y++)v_usados[y] = -1;
		i=1;
		int acu = 0;
		for(i=1;i<=types;i++){
			
			acu += pizzas[types-i];
			if(acu > maximo){
				acu -= pizzas[types-i];
			}else{
				v_usados[types-i] = types-i;
				c_usados++;
			}
		}
		System.out.println(Integer.toString(acu));
		
		FileWriter salida = null;
		String name_salida = "salida"+indice+".out";
		try {
			salida = new FileWriter(name_salida);
			salida.write(Integer.toString(c_usados) + "\n");
			salida.write(conv_final(v_usados));
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
	
	static String InttoString(int []vector){
		String h = "";
		int k = 0;
		h = h + Integer.toString(vector[k]);
		for(k=1;k<vector.length;k++)h +=" " + Integer.toString(vector[k]);
		return h;
	}
	
	static String conv_final(int []vector){
		String h = "";
		int k = 0;
		for(k=0;k<vector.length;k++){
			if(vector[k] != -1)h +=Integer.toString(vector[k]) + " ";
		}
		return h;
	}
}
