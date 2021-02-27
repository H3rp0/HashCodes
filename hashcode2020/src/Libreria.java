import java.util.*;

public class Libreria {

	public int Nbooks;
	public int sign_up_time;
	public int books_per_day;
	public ArrayList<Book> books;
	public int id;
	public int real_id;

	public Libreria() {
	}

	public Libreria(int Nbooks, int sign_up_time, int books_per_day, int id) {
		this.Nbooks = Nbooks;
		this.books_per_day = books_per_day;
		this.sign_up_time = sign_up_time;
		real_id = id;
		this.id = real_id;
		books = new ArrayList<Book>();
	}
	public void add_book(Book book) {
		books.add(book);
	}

	/*	Ordena 
	 * 
	 * 
	 */
	public void setid(int id) {
		this.id = id;
	}
	public void sort() {
		Collections.sort(books, new Book());
	}
	public void books4scoring(ArrayList<Book> apariciones) {
		
		if (apariciones.size() > 0) {
			
			
			int ita = 0;
			while(ita < apariciones.size()) {
				int itb = 0;
				boolean encontrado = false;
				boolean salir = false;
				while( !salir && !encontrado ) {
					if(books.get(itb) == apariciones.get(ita)) {
						encontrado = true;
						books.remove(itb);
						//Nbooks--;
					}
					else itb++;
					if(itb >= books.size())salir = true;
				}
				if (books.size() == 0)ita = apariciones.size();
				else ita++;
				Nbooks = books.size();
			}
		}
		
	}
	public Score score(int Ttotal, int curr_time, Libreria self) {
		
		int time=Ttotal - curr_time - sign_up_time;
		Score temp = null;
		if(time <= 0 || Nbooks == 0) {
			temp = new Score(-10,id,0);
		}else {
			int score=0;
			int ind = Math.min(books.size(), books_per_day*time);
			for(int i=0;i<ind;i++) {
				score += books.get(i).get_valor();
			}
			score /= sign_up_time;
			temp = new Score(score,id, ind);
		}
		return temp;
	}
}
