import java.util.Comparator;

public class SortByValue implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		// TODO Auto-generated method stub
		return o2.valor - o1.valor;
	}
	
}