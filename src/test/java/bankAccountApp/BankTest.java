package bankAccountApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import com.imt.mines.BankAccount;
import com.imt.mines.Bank;
import com.imt.mines.Person;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	String name = "John";
	char gender = 'm';
	int age = 22;
	int weight = 190;
	float height = 172;
	String hairColor = "brown";
	String eyeColor = "green";
	String email = "jufm@gmail.com";

	int ifloadaccManager = 0;
	int initMoneyAmount = 5000;
	int withdrawLimit = 760;
	String dateCreated = "05/21/2019";
	BankAccount bankAccount = new BankAccount();
	String text = "C:\\Users\\jay4k\\Desktop\\stuff\\Bankaccountinfo\\BankAccountinfotext.text";
	// String text = "/Users/markkelly/BankAccountinfotext.text";
	Bank bank = null;
	Person accountHolder = null;

	@Before
	public void setup() {
		// Create Person
		try {
			accountHolder = new Person(name, gender, age, weight, height, hairColor, eyeColor, email);
		} catch (Exception e) {
			System.out.print("Unexpected failure during test setup");
			e.printStackTrace();
		}
		bank = new Bank();
	}

	@Test
	public void testCreateAccount_DeleteAccount() throws Exception {
		// Given
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc1, ifloadaccManager);
		int accountNumber = acc1.getAccountNumber();
		bank.deleteAccount(acc1.getAccountNumber());

		// Then
		assertNull("Account was not deleted:" + accountNumber, bank.findAccount(accountNumber));
	}

	@Test
	public void testFindAccount() {
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(initMoneyAmount, 400, dateCreated, accountHolder);

		bank.addAccount(acc1, ifloadaccManager);
		bank.addAccount(acc1, 2);
		bank.addAccount(acc1, 2);
		bank.addAccount(acc2, 2);
		BankAccount tmpacc = bank.findAccount(4);
		assertThat(tmpacc.getWithdrawLimit(), equalTo(400.0));
	}

	@Test
	public void testGetAccounts() throws Exception {
		// Given
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(1000, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc2, ifloadaccManager);
		bank.addAccount(acc1, ifloadaccManager);

		// Then
		ArrayList<BankAccount> accounts = bank.getAccounts();
		assertThat(accounts.size(), equalTo(2));
	}

	@Test
	public void testgetAccountsLoaded() {
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(initMoneyAmount, 400, dateCreated, accountHolder);

		bank.addAccount(acc1, ifloadaccManager);
		bank.addAccount(acc1, 2);
		bank.addAccount(acc1, 2);
		bank.addAccount(acc2, 2);
		assertThat(bank.getAccountsLoaded(), equalTo(4));
	}

	@Test
	public void testCreateAccount_GetAverageBalance() throws Exception {
		// Given
		int acct1Amount = 5000;
		int acct2Amount = 10000;
		BankAccount acc1 = new BankAccount(acct1Amount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(acct2Amount, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc1, ifloadaccManager);
		bank.addAccount(acc2, ifloadaccManager);

		// Then
		assertEquals(7500, bank.getAverageBalance(), 0f);
	}

	@Test
	public void testCreateAccount_GetMaximumBalance() throws Exception {
		// Given
		int acct1Amount = 5000;
		int acct2Amount = 10000;
		BankAccount acc1 = new BankAccount(acct1Amount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(acct2Amount, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc2, ifloadaccManager);
		bank.addAccount(acc1, ifloadaccManager);

		// Then
		assertEquals(10000, bank.getMaximumBalance(), 0f);
	}

	@Test
	public void testCreateAccount_GetMinimumBalance() throws Exception {
		// Given
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		BankAccount acc2 = new BankAccount(1000, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc2, ifloadaccManager);
		bank.addAccount(acc1, ifloadaccManager);

		// Then
		assertEquals(1000, bank.getMinimumBalance(), 0f);
	}
	
	@Test
	public void testTransferAmount() throws Exception {
		// Given
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc1, ifloadaccManager);

		// Then
		boolean value = bank.transferAmount(acc1.getAccountNumber(), 1234, 5678, 1234567, 100.00F);
		assertEquals(true, value);
	}

	// ========== TEST POUR convertToText() ==========

	@Test
	public void testConvertToText_SingleAccount() throws Exception {
		// Given: une banque avec un seul compte
		BankAccount acc1 = new BankAccount(initMoneyAmount, withdrawLimit, dateCreated, accountHolder);
		bank.addAccount(acc1, ifloadaccManager);

		// When: on convertit la banque en texte
		String serializedBank = bank.convertToText();

		// Then: la chaîne devrait contenir les informations du compte
		assertThat("Le texte sérialisé ne devrait pas être vide", serializedBank.length() > 0);

		// Vérifier que le texte contient les informations clés du compte
		String[] tokens = serializedBank.split(Person.DELIM);
		assertThat("Le nombre de tokens devrait être 12", tokens.length, equalTo(12));

		// Vérifier les valeurs du compte
		assertThat("Numéro de compte incorrect", Integer.valueOf(tokens[0]), equalTo(acc1.getAccountNumber()));
		assertThat("Solde incorrect", Double.valueOf(tokens[1]), equalTo(acc1.getBalance()));
		assertThat("Limite de retrait incorrecte", Double.valueOf(tokens[2]), equalTo(acc1.getWithdrawLimit()));
		assertThat("Date de création incorrecte", tokens[3], equalTo(dateCreated));

		// Vérifier les informations du titulaire du compte
		assertThat("Nom du titulaire incorrect", tokens[4], equalTo(name));
		assertThat("Genre du titulaire incorrect", tokens[5].charAt(0), equalTo(gender));
		assertThat("Âge du titulaire incorrect", Integer.valueOf(tokens[6]), equalTo(age));
	}

	@Test
	public void testConvertToText_MultipleAccounts() throws Exception {
		// Given: une banque avec plusieurs comptes
		BankAccount acc1 = new BankAccount(5000, 700, "05/21/2019", accountHolder);
		BankAccount acc2 = new BankAccount(10000, 500, "06/15/2020", accountHolder);
		bank.addAccount(acc1, ifloadaccManager);
		bank.addAccount(acc2, ifloadaccManager);

		// When: on convertit la banque en texte
		String serializedBank = bank.convertToText();

		// Then: la chaîne devrait contenir les informations des deux comptes
		assertThat("Le texte sérialisé ne devrait pas être vide", serializedBank.length() > 0);

		// Compter le nombre de délimiteurs pour vérifier qu'on a bien 2 comptes (24 délimiteurs total)
		int delimCount = serializedBank.length() - serializedBank.replace(Person.DELIM, "").length();
		assertThat("Nombre de délimiteurs incorrect pour 2 comptes", delimCount >= 22);

		// Vérifier que la chaîne contient les soldes des deux comptes
		assertThat("Le texte devrait contenir le solde du premier compte",
				   serializedBank.contains("5000.0"));
		assertThat("Le texte devrait contenir le solde du second compte",
				   serializedBank.contains("10000.0"));
	}

	@Test
	public void testConvertToText_EmptyBank() {
		// Given: une banque sans compte
		// When: on convertit la banque en texte
		String serializedBank = bank.convertToText();

		// Then: la chaîne devrait être vide
		assertThat("La sérialisation d'une banque vide devrait donner une chaîne vide",
				   serializedBank, equalTo(""));
	}

	@Test
	public void testConvertToText_AfterAccountDeletion() throws Exception {
		// Given: une banque avec deux comptes
		BankAccount acc1 = new BankAccount(5000, 700, "05/21/2019", accountHolder);
		BankAccount acc2 = new BankAccount(10000, 500, "06/15/2020", accountHolder);
		bank.addAccount(acc1, ifloadaccManager);
		bank.addAccount(acc2, ifloadaccManager);

		// When: on supprime un compte puis on convertit en texte
		bank.deleteAccount(acc1.getAccountNumber());
		String serializedBank = bank.convertToText();

		// Then: seul le second compte devrait être présent
		String[] tokens = serializedBank.split(Person.DELIM);
		assertThat("Le nombre de tokens devrait être 12 (un seul compte)", tokens.length, equalTo(12));
		assertThat("Le texte devrait contenir le solde du second compte",
				   serializedBank.contains("10000.0"));
		assertThat("Le texte ne devrait PAS contenir le solde du premier compte supprimé",
				   !serializedBank.contains("5000.0"));
	}

}
