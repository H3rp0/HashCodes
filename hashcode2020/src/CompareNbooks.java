import java.util.Comparator;

public class CompareNbooks implements Comparator<Libreria>{

	@Override
	public int compare(Libreria o1, Libreria o2) {
		// TODO Auto-generated method stub
		return o2.Nbooks - o1.Nbooks;
	}

}
