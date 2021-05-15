package data;

public class Kayttaja {
	private int id;
	private String name;
	private String email;
	private String password;

	public Kayttaja() {

	}

	public Kayttaja(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isOK() {
		if (this.name == null || this.email == null) {
			return false;
		}
		return true;
	}
}
