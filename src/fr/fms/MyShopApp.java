package fr.fms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import fr.fms.entities.User;


public class MyShopApp {	
	private static User robert;
	private static User julie;		
//	private static Current firstAccount;
//	private static Saving secondAccount;	
		
	
	private static Pattern regExp = Pattern.compile("[0-9]+");
	
	public static void main(String[] args) {		
//		initBank();	//lancement de notre banque avec 2 clients et 2 comptes		
//		welcome();	//message de bienvenue
//		
		Scanner scan = new Scanner(System.in);		
		while(true) 
		{			//authentification
			System.out.println("saisissez un numéro de compte bancaire valide :");
			int numAccount = 0;
			
			numAccount = scanInt(scan);		//vérification si saisie conforme + compte existant ou pas
					
			int action = 0;
			System.out.println("Bienvenu " + bankJob.consultAccount(numAccount).getCustomer().getName() + ", que souhaitez vous faire ? taper le numéro correspondant");
			while(action != 6) 
			{	
				try {
					System.out.println("\n1:versement - 2:retrait - 3:virement - 4:information sur ce compte - 5:liste des opérations - 6:sortir");							
					action = scan.nextInt();
					double amount;
					switch(action) {							
						case 1 : System.out.println("saisissez le montant à verser sur ce compte");
							amount = scan.nextDouble();
							bankJob.pay(numAccount, amount);								
							break;
						
						case 2 : System.out.println("saisissez le montant à retirer sur ce compte");
							amount = scan.nextDouble();
							bankJob.withdraw(numAccount, amount);
							break;
						
						case 3 : System.out.println("saisissez le numéro de compte destinataire");
							int numAccountDest = scan.nextInt();
							System.out.println("saisissez le montant à virer sur ce compte");
							amount = scan.nextDouble();
							bankJob.transfert(numAccount,numAccountDest, amount);
							break;
							
						case 4 : System.out.println(bankJob.consultAccount(numAccount));
							break;
							
						case 5 : for(Transaction trans : bankJob.listTransactions(numAccount))	System.out.println(trans);
							break;
							
						case 6 : System.out.println("sortie" + "\n");
							break;
							
						default : System.out.println("mauvaise saisie");							
					}	
				}
				catch (Exception e) {					
					System.out.println(e.getMessage());
				}
			}				
		}
	}

	private static int scanInt(Scanner scan) {
		int numAccount = 0;
		
		while(scan.hasNext()) {
			if(scan.hasNext(regExp)) {
				numAccount = scan.nextInt();
				try {
					if(bankJob.consultAccount(numAccount) != null)	break;
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			else {
				System.out.println("un numéro de compte bancaire est constitué que de chiffres");
				scan.next();
			}
		}		
		return numAccount;
	}

	private static void welcome() {
		System.out.println();
		System.out.println("********************************************");
		System.out.println("BIENVENU DANS MA BOUTIQUE V1                ");
		System.out.println("********************************************");		
		System.out.println();
	}

	private static void initBank() {			
		robert = new User("robert", "!ù:*/àçè5");
		julie = new User(2, "julie", "çèç'35_543");		
//		firstAccount = new Current(100200300, new Date(), 1500, 200 , robert);
//		secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);	
		bankJob = new IBankBusinessImpl();		
		
		System.out.println("Liste des comptes dans ma banque");		
		bankJob.listAccounts().stream().forEach(c -> System.out.println(c));
	}
}
