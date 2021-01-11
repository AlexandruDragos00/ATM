
public class Account {
	private int accountNumber;
	private int pin;
	private int availableBalance;
	private String name;
	
	public Account (int theAccountNumber, int thePIN, 
			int theAvailableBalance, String theName) {
		accountNumber = theAccountNumber;
		pin = thePIN;
		availableBalance = theAvailableBalance;
		name = theName;
	}
	public boolean validatePIN (int userPIN) {
		if (userPIN == pin)
			return true;
		else 
			return false;
	}
	public int getAvailableBalance() {
		return availableBalance;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
	
	public int getPin() {
		return pin;
	}
	
}
