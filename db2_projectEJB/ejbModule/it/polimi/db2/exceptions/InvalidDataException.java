package it.polimi.db2.exceptions;

public class InvalidDataException extends Exception {
	
	public InvalidDataException() { super("Submitted data is invalid"); }
	
	public InvalidDataException(String message) { super(message); }
}
