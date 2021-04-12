package data;

public class Vastausvaihtoehdot {
		String vv;
		int id;
		
		// Peruskonstruktori
		public Vastausvaihtoehdot() {
			
		}
		
		// Konstruktori ID:llä ja tekstillä
		public Vastausvaihtoehdot(String id, String vastausteksti) {
			setId(id);
			this.vv = vastausteksti;
		}
		
		// Konstruktori tekstillä
		public Vastausvaihtoehdot(String vastausteksti) {
				this.vv = vastausteksti;
		}
		
		// Setterit ja getterit
		public void setID(int id) {
			this.id = id;
		}
		
		// MUUTTAA LOMAKKEELTA TULLEEN STRING ID:N INTIKSI
		public void setId(String id) {
			try {
				this.id = Integer.parseInt(id);
			}
			catch(NumberFormatException | NullPointerException e) {
				//Do nothing - the value of id won't be changed
			}
		}
		
		public void setVv(String vastausteksti) {
			this.vv = vastausteksti;
		}
		
		public int getId() {
			return id;
		}
		
		public String getVv() {
			return vv;
		}
		
		public String getIdString() {
			return String.valueOf(id);
		}
		
		// Olion tulostamiseen toString
		public String toString() {
			return id+""+vv;
		}

}
