import java.util.ArrayList;
import java.util.List;

public class BankDB {
	//private Account[] accounts;
	private List<String> acc;
	private List<Account> accounts ;
	
	public BankDB() {
		try {
			accounts = AccountFactory.createAccountsFromFile("date.csv");
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		acc = new ArrayList<>();
		for(Account temp : accounts) {
			acc.add(String.valueOf(temp.getAccountNumber()));
		}
		
		
		
		
		/*
		accounts = new Account [3];
		                   //Card number, pin, availableBalance, name
		accounts[0] = new Account (11111, 1, 1000, "Andrei");
		accounts[1] = new Account (22222, 2, 500, "Razvan");
		accounts[2] = new Account (33333, 3, 200, "Rares");
		
		acc = new String[3];
		acc[0] = new String("11111");
		acc[1] = new String("22222");
		acc[2] = new String("33333"); */
	}
	
	private Account getAccount (int accountNumber) {
		for (Account currentAccount : accounts) {
			if (currentAccount.getAccountNumber() == accountNumber)
				return currentAccount;
		}
		return null;
	}
	
	public boolean authenticateUser (int userAccountNumber, int userPIN) {
		Account userAccount = getAccount(userAccountNumber);
		if (userAccount != null) 
			return userAccount.validatePIN(userPIN);
		else 
			return false;
	}
	public int getAvailableBalance (int userAccountNumber){
		return getAccount(userAccountNumber).getAvailableBalance();
	}
	
	public void setAvailableBalance (int userAccountNumber, int newAvailableBalance){
		getAccount(userAccountNumber).setAvailableBalance(newAvailableBalance);
	}
	
	
	public String getName (int userAccountNumber){
		return getAccount(userAccountNumber).getName();
	}
	
	public List<String> accList (){
		return acc;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}

