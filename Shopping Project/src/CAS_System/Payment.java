package CAS_System;

//Validates the payment information
public class Payment {

	public static Boolean pay_by_paypal(String email) {//Validates a user's email address for paypal
		try{
			//Email is validated by checking  "_@_._"
			//Checks for (1 or more) characters, then "@", then (1 or more) characters, then ".", then (1 or more) characters
			if	(email.indexOf('@')>0){ //Checks if there a character before the @ sign
				email=email.substring(email.indexOf('@'));//Gets rid of everything before of @ sign to find a "." after it
				if ((email.indexOf('.'))>(email.indexOf('@')+1)){//Checks there is a dot at least 1 character after the @ sign
					if (email.length()-1>email.indexOf('.')){//Checks if there is a character after the "." character
						return true;
					}
				}
			}
			return false;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	//I will be validating my card number using the Luhn Formula:
	//This is a real validation check done on card numbers to ensure they are real
	// https://www.freeformatter.com/credit-card-number-generator-validator.html
	public static Boolean pay_by_card(String card, String cvc) {//Validates a card and cvc
		try{
			if(card.matches("-?\\d+") && cvc.matches("-?\\d+")){//Checks the card and cvc are integers
				if ((card.length()==16) && (cvc.length()==3)){//Checks lengths
					//-----------------------------------Luhn Formula-Part 1
					//Split card into first 15 digits and its last digit
					String check_digit_str=card.substring(15,16);
					card=card.substring(0,15);
					//-----------------------------------Luhn Formula-Part 2
					//Reverse the 15 digit card
					String temp="";
					for (int i = 15; i >0; i--) {
						temp+=card.substring(i-1,i);
					}
					card=temp;
					//-----------------------------------Luhn Formula-Part 3
					//Multiply the digits in odd positions by 2
					//And subtract 9 to all any result higher than 9
					String card_list[] = card.split("");
					int[] card_int=new int [15];//Converts string into list of integers
					for (int i = 0; i <15; i++) {
						int val=Integer.parseInt(card_list[i]);
						card_int[i]=val;
					}//Multiplies odd positions by 2
					for (int i = 0; i <15; i++) {
						if ((i+1)%2==1){
							card_int[i]=card_int[i]*2;
						}
					}
					//Subtracts 9 to all any number higher than 9
					for (int i = 0; i <15; i++) {
						if (card_int[i]>9){
							card_int[i]=card_int[i]-9;
						}
					}
					//-----------------------------------Luhn Formula-Part 4
					//Add all the numbers together
					int total=0;
					for (int i = 0; i <15; i++) {
						total+=card_int[i];
					}
					//-----------------------------------Luhn Formula-Part 5
					//The (check digit+total) mod10 is equal to 0
					int check_digit=Integer.parseInt(check_digit_str);
					if ((total+check_digit)%10==0){
						return true;
					}
				}
			}
			return false;
		}
		catch(Exception e) {
			return false;
		}
	}
}