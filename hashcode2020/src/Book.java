import java.util.Comparator;

public class Book implements Comparator<Book>{
	public int indice;
	public int valor;
	public Book() {}
	public Book(int indice, int valor) {
		this.indice = indice;
		this.valor = valor;
	}
	public int get_indice() {
		return indice;
	}
	public int get_valor() {
		return valor;
	}
	public void set_valor(int a) {
		valor = a;
	}
	@Override
	public int compare(Book o1, Book o2) {
		// TODO Auto-generated method stub
		return o2.valor - o1.valor;
	}
}
