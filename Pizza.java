import java.io.Serializable;
/**
 * A class to store information about Pizza.
 * <p>
 * The Size, amount of cheese and variety of topics on one pizza have been recorded. 
 * The size has three possibilities; Small, Medium and Large and the amount of cheese
 * has three possibilities; Single, Double and Triple. There are three extra toppings 
 * which are pineapple, green peppers, and ham. A pizza can also be described as 
 * vegetarian. The price of individual pizzas can be calculated without tax. This
 * class also implements Serializable (for filing).
 *
 *
 * @author Renner Siebens
 *
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = -2911456135582312852L;
	
	public enum Size {Small, Medium, Large};
	public enum Cheese {Single, Double, Triple};
	public enum Topping {None, Single};
	private Size size;
	private boolean vegitarian;
	private Cheese cheese;
	private Topping pineapple;
	private Topping greenPepper;
	private Topping ham;
	private double Small_Price = 7.00;
	private double Medium_Price = 9.00;
	private double Large_Price = 11.00;
	private double Topping_Prices = 1.50;
	
	/**
	 * The Pizza Constructor, which is immutable. 
	 * @param size Has to be "Small", "Medium", or "Large".
	 * @param veg Is a boolean value, true if vegitarian, false if not.
	 * @param amountCheese Has the options of being "Single", "Double" or "Triple".
	 * @param pineapple Is a topping and is therefore either "None" or "Single".
	 * @param greenPepper Is a topping and is therefore either "None" or "Single".
	 * @param ham Is a topping and is therefore either "None" or "Single".
	 * @throws IllegalPizza If any of the parameters are not legal or if veg is true 
	 * but ham is declared as single. The parameters are defined by enums, so we really are 
	 * checking to see if the values of any of the parameters are "null".
	 */
	public Pizza (Size size, boolean veg, Cheese amountCheese, Topping pineapple, Topping greenPepper, Topping ham) 
	throws IllegalPizza {
		if (size == null)
			throw new IllegalPizza ("Illegal Size!");
		else
			this.size = size;
		if (veg == true || veg == false)
			this.vegitarian = veg;
		else 
			throw new IllegalPizza ("Illegal response to vegitarian option!");
		if (amountCheese == null)
			throw new IllegalPizza ("Illegal cheese option!");
		else
			this.cheese = amountCheese;
		if (pineapple == null)
			throw new IllegalPizza ("Illegal pineapple choice!");
		else 
			this.pineapple = pineapple;
		if (greenPepper == null)
			throw new IllegalPizza ("Illegal green pepper choice!");
		else 
			this.greenPepper = greenPepper;
		if (ham != null) {
			if (vegitarian == true && ham.equals(Topping.Single)) //check if vegitarian is true and ham is single.
				throw new IllegalPizza ("Vegitarian Options is chosen, so pizza cannot have ham on it!");
			this.ham = ham;
		}//end if
		else 
			throw new IllegalPizza ("Illegal Ham option choice!");	
	}//end Pizza Constructor with all parameters
	
	/**
	 * This is the 5 parameter constructor for the pizza object, where vegetarian is false.
	 * @param size Has to be "Small", "Medium", or "Large".
	 * @param amountCheese Has the options of being "Single", "Double" or "Triple".
	 * @param pineapple Is a topping and is therefore either "None" or "Single".
	 * @param greenPepper Is a topping and is therefore either "None" or "Single".
	 * @param ham Is a topping and is therefore either "None" or "Single".
	 * @throws IllegalPizza If any of the parameters are not legal or if veg is true 
	 * but ham is declared as single. The parameters are defined by enums, so we really are 
	 * checking to see if the values of any of the parameters are "null".
	 */
	public Pizza (Size size, Cheese amountCheese, Topping pineapple, Topping greenPepper, Topping ham)
	throws IllegalPizza {
		this (size, false, amountCheese, pineapple, greenPepper, ham);
	} // end Pizza 5 parameter constructor with vegetarian as false.
	
	/**
	 * Empty constructor where size is small, vegitarian is false, cheese is single, and
	 * the only topping is ham.
	 * @throws IllegalPizza If any of the parameters are not legal.
	 */
	public Pizza () throws IllegalPizza {
		this (Size.Small, false, Cheese.Single, Topping.None, Topping.None, Topping.Single);
	}//end empty constructor
	
	/**
	 * Accessor that returns the cost of the pizza without tax. 
	 * @return The cost of a single pizza object with size, amount of cheese
	 * and toppings taken into account.
	 */
	public double getCost () {
		double totalCost = 0;
		if (size.equals(Size.Small))
			totalCost = Small_Price;
		else if (size.equals(Size.Medium))
			totalCost = Medium_Price;
		else
			totalCost = Large_Price;
		if (ham.equals(Topping.Single))
			totalCost += Topping_Prices;
		if (pineapple.equals(Topping.Single))
			totalCost += Topping_Prices;
		if (greenPepper.equals(Topping.Single))
			totalCost += Topping_Prices;
		if (cheese.equals(Cheese.Double))
			totalCost += Topping_Prices;
		if (cheese.equals(Cheese.Triple))
			totalCost += Topping_Prices*2;
		return totalCost;
	}//end getCost()
	
	/**
	 * Puts the description of the pizza into a string. The string includes the size, 
	 * amount of cheese, toppings and overall cost of the pizza.
	 * @return A string that describes the current pizza object. 
	 */
	//Overrides the toString method of the Object class.
	@Override
	public String toString() {
		String s = size.toString();
		//if vegetarian is true, include it in the string, otherwise don't.
		if (vegitarian)
			s += " vegetarian";
		s += " pizza, " + cheese.toString() + " cheese";
		if (pineapple.equals(Topping.Single))
			s += ", pineapple";
		if (greenPepper.equals(Topping.Single))
			s += ", green pepper";
		if (ham.equals(Topping.Single))
				s += ", ham";
		s += ". ";
		//obtains the overall cost of the pizza object and prints it to 2 decimal 
		//places.
		s += String.format("Cost: $%.2f each.", getCost());
		return s;
	}//end toString()

	/**
	 * Checks if the current object is equal to the supplied differentPizza object. 
	 * Basis for equality is all attributes are identical.
	 * @param differentPizza The object supplied to the method to check for equality with 
	 * current pizza object
	 * @return True if the two objects are the same, false if they are different
	 */
	//Overrides the equals method of the Object class
	@Override
	public boolean equals (Object differentPizza) {
		if (differentPizza instanceof Pizza) {
			Pizza otherP = (Pizza)differentPizza;
			if (size.equals(otherP.size))
				return vegitarian == otherP.vegitarian && cheese == otherP.cheese && 
				pineapple == otherP.pineapple && greenPepper == otherP.pineapple &&
				ham == otherP.ham; //returns true or false depending iff all the conditions are met.
		} // end if
		return false; // returns false is instance of Pizza was not created.
	}// end equals()
	
	/**
	 * This method creates a clone of the current pizza object incase the object is mutable.
	 * @return A copy of the current pizza object.
	 */
	//Overrides the clone method in the Object class
	@Override
	public Pizza clone () {
		Pizza pizCopy = null;//initialize the Pizza copy Object.
		try {
			pizCopy = new Pizza (size, vegitarian, cheese, pineapple, greenPepper, ham);
		}//end try
		catch (IllegalPizza e) {
			return null; //should never get here!
		}//end catch
		return pizCopy;
	}//end clone()
}// end Pizza class	
