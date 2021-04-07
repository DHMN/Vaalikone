package data;

public class Vastaus {
		String vastausteksti;
		int id;
		
		// Peruskonstruktori
		public Vastaus() {
			
		}
		
		//Konstruktori ID:llä ja tekstillä
		public Vastaus(int id, String vastausteksti) {
			this.id = id;
			this.vastausteksti = vastausteksti;
		}
		
		//Konstruktori tekstillä
		public Vastaus(String vastausteksti) {
				this.vastausteksti = vastausteksti;
		}
		
		//Setterit ja getterit
		public void setID(int id) {
			this.id = id;
		}
		
		public void setVastausteksti(String vastausteksti) {
			this.vastausteksti = vastausteksti;
		}
		
		public int getId() {
			return id;
		}
		
		public String getIdString() {
			return String.valueOf(id);
		}
		
		//Olion tulostamiseen toString
		public String toString() {
			return id+""+vastausteksti;
		}

}
