package be.infogroep.droidtool;

public class TokenException extends Exception {
	String mistake;
	public TokenException() {
		super();
		mistake = "unknown";
	}
	public TokenException(String err) {
		super(err);
		mistake = err;
	}
	public String getError() {
		return mistake;
	}
}
