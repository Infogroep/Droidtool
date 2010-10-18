package be.infogroep.droidtool;

public class TokenException extends Exception {
	private static final long serialVersionUID = 5829016279439216351L;
	String mistake;
	public TokenException() {
		super();
		mistake = "unknown";
	}
	public TokenException(String err) {
		super(err);
		mistake = err;
	}
	public String getMessage() {
		return mistake;
	}
}
