import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Streetmanager {
	
	public static void main (String[] args) {
		
		//Nombre de los ficheros a leer
		String[] ficheros = new String[]{"a.txt","b.txt","c.txt","d.txt","e.txt","f.txt"};
		int factor = 4;
		if (args.length >1)factor = Integer.parseInt(args[1]);
		//Abrimos el fichero
		File file = new File(ficheros[Integer.parseInt(args[0])-1]);
		//File file = new File(ficheros[0]);
		Scanner fich = null;
		try {
			fich = new Scanner(file);
		
			//Lectura de fichero
			
			int tSim = fich.nextInt();
			int nInt = fich.nextInt();
			int nCal = fich.nextInt();
			int nCoc = fich.nextInt();
			int bonus = fich.nextInt();
			fich.nextLine();
			//Crea calles
			ArrayList<Calle> streets = new ArrayList<Calle>();
			for(int i = 0; i < nCal; i++){
				int inicio = fich.nextInt();
				int fin = fich.nextInt();
				String aux = fich.nextLine();
				String nombre = aux.split(" ")[1];
				int tiempo = Integer.parseInt(aux.split(" ")[2]);
				streets.add(new Calle(inicio, fin, nombre, tiempo));
			}
			//Crea coches
			ArrayList<Coche> cars = new ArrayList<Coche>();
			for(int i = 0; i < nCoc ; i++){
				int nRec = fich.nextInt();
				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(fich.nextLine().split(" ")));
				temp.remove(0);
				ArrayList<Calle> calles = new ArrayList<Calle>();
				for (int j = 0; j < temp.size(); j++) {
					for (int h = 0; h < streets.size(); h++) {
						if (   streets.get(h)._nombre.equals(temp.get(j))   ) {
							calles.add(streets.get(h));
							break;
						}
					}
				}
				
				cars.add(new Coche(nRec, calles));
			}
			ArrayList<Calle> node_s = (ArrayList<Calle>) streets.clone();
			ArrayList <Nodo> inter= new ArrayList<Nodo>();
			//Coches que no llegan por tener un camino muy largo
	        for (int i=0; i< cars.size(); i++) {
	            int sum = 0;
	            for(int j=1; j< cars.get(i)._calles.size();j++) {
	            	sum += cars.get(i)._calles.get(j)._tiempo;
	            }
	            if (sum > tSim) {
	            	cars.remove(i);
	            	i--;
	            }else {
	            	cars.get(i).desp = tSim - sum;
	            	for (int j = 0; j < cars.get(i)._calles.size()-1 ; j++) {
	            		boolean esta = false;
	            		for(int h=0; h<inter.size(); h++) {
	            			if(cars.get(i)._calles.get(j)._fin == inter.get(h)._id) {
	            				esta = true;
	            				break;
	            			}
	            		}
	            		if(!esta) {
	            			int id = cars.get(i)._calles.get(j)._fin;
	            			ArrayList <Calle> lista= new ArrayList<Calle>();
	            			for(int h = 0; h<node_s.size(); h++) {
	            				if (node_s.get(h)._fin == id) {
	            					lista.add(node_s.get(h));
	            					node_s.remove(h);
	            					h--;
	            				}
	            			}
	            			inter.add(new Nodo(id, lista));
	            			//System.out.print(inter.get(inter.size()-1).pintarNodo());
	            		}
	            	}
	            }
	        }
			
			//Creamos el fichero de escritura
			FileWriter salida = null;
			String name_salida = ficheros[Integer.parseInt(args[0])-1] + ".out";
			//String name_salida = ficheros[0]+"out";
			salida = new FileWriter(name_salida);
			//coches por calle
			for (int i=0; i<cars.size();i++) {
				for (int j = 0; j < cars.get(i)._calles.size()-1; j++) {
					cars.get(i)._calles.get(j)._coinc++;
				}
			}
			//Escritura de fichero y calculos
			salida.write(inter.size()+"\n");
			int nodosTotales = 0;
			for(int i = 0; i < inter.size(); i++) {
				
				int []suma = new int[inter.get(i)._calles.size()];
				int total = 0;
				for (int j = 0; j < inter.get(i)._calles.size(); j++ ) {
					suma[j] = inter.get(i)._calles.get(j)._coinc;
					total += inter.get(i)._calles.get(j)._coinc;
				}
				if (total != 0) {
					nodosTotales++;
					salida.write(inter.get(i)._id+"\n");
					int salidas = inter.get(i)._calles.size();
					for(int j=0; j<inter.get(i)._calles.size(); j++) {
						if (suma[j] == 0) {
							salidas--;
						}
					}
					salida.write(salidas+"\n");
					for (int j = 0; j < inter.get(i)._calles.size(); j++ ) {
						if (suma[j] != 0) {
							int temp =  Math.round(Math.max(factor*((float)suma[j]/total), 1));
							salida.write(inter.get(i)._calles.get(j)._nombre + " ");
							salida.write(temp + "\n");
						}
					}
				}else System.out.println("KEKW TOTAL = 0");
				
			}
			
			System.out.println(nodosTotales);
			
			salida.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}


class Calle{
    public int _inicio;
    public int _fin;
    public String _nombre;
    public int _tiempo;
    public int _coinc = 0;

    public Calle(int inicio, int fin, String nombre, int tiempo){
        _inicio = inicio;
        _fin = fin;
        _nombre = nombre;
        _tiempo = tiempo;
    }
}

class Coche{
    public int _nRec;
    public ArrayList<Calle> _calles;
    public int lectura=0;
    public int desp = 0;

    public Coche(int nRec, ArrayList<Calle> calles){
        _nRec = nRec;
        _calles = calles;
    }
}

class Nodo{
	public int _id;
	public ArrayList<Calle> _calles;
	
	public Nodo(int id , ArrayList<Calle> calles) {
		_id = id;
		_calles = calles;
	}
	public String pintarNodo() {
		String str = "Nodo "+_id+": \n";
		for (int i = 0; i<_calles.size(); i++) {
			str += _calles.get(i)._nombre+"\n";
		}
		return str;
	}
}



