import java.util.Comparator;

public class Score implements Comparator<Score>{
	public int puntos;
	public int id;
	public int Nbooks;
	
	public Score() {}
	public Score(int p, int i, int b) {
		puntos = p;
		id = i;
		Nbooks = b;
	}
	@Override
	public int compare(Score o1, Score o2) {
		// TODO Auto-generated method stub
		return o2.puntos - o1.puntos;
	}
}
