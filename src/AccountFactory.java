import java.util.ArrayList;
import java.util.List;

public class AccountFactory {

	   	
	public static List<Account> createAccountsFromFile(String filename) throws Exception {
	        List<Account> AccountsList = new ArrayList<>();
	        CSVFileHandler reader = new CSVFileHandler(filename);
	        List<List<String>> CSVContent = reader.readFile();
	        for (List<String> line : CSVContent) {
	            int accountNumber = Integer.parseInt(line.get(0));
	            int pin = Integer.parseInt(line.get(1));
	            int availableBalance = Integer.parseInt(line.get(2));
	            String nume = line.get(3);
	            
	            
	            AccountsList.add(new Account(accountNumber, pin, availableBalance, nume));
	        }
	        return AccountsList;
	    }
	

  public static void writeAccountsToFile(String filename, List<Account> accounts) throws Exception {
        CSVFileHandler writer = new CSVFileHandler(filename);
        List<List<String>> csvAccounts = new ArrayList<>();
        for (Account account : accounts) {
            List<String> currentLine = new ArrayList<>();
            currentLine.add(String.valueOf(account.getAccountNumber()));
            currentLine.add(String.valueOf(account.getPin()));
            currentLine.add(String.valueOf(account.getAvailableBalance()));
            currentLine.add(account.getName());
            

            csvAccounts.add(currentLine);
        }
        writer.writeFile(csvAccounts);
    }



}

