import java.io.Serializable;

/**
 * A class to store information about a single line item for a pizza order.
 * <p>
 * The type of pizza, with specified size, cheese and toppings as well 
 * as the number of these pizza's is recorded. 
 * <p>
 * The number of pizza's is mutable and therefore has a mutator, where
 * the Pizza attribute is immutable and is included only in the 
 * constructor. There is a method within the class that gets the total
 * cost of the line item. This total price changes depending on the 
 * amount of pizza's ordered.
 * <p>
 * The class implements Comparable (for sorting) and Serializable (for
 * filing). A compareTo method has be incorporated to organize how the 
 * pizza's are listen on the screen. Highest total cost at the top.
 * 
 * @author Renner Siebens (18rgs1)
 *
 */

public class LineItem implements Comparable<LineItem>, Serializable {

	private static final long serialVersionUID = -3662105641268627036L;
	private int numPizza;
	private Pizza pizza;
	
	/**
	 * Full parameter constructor for LineItem.
	 * @param numberPizza The number of the same Pizza objects. Must lie between 
and 100 inclusively.
	 * @param pizza A pointer to the Pizza object for that order.
	 * @throws IllegalPizza If arguments are not legal or null or if pizzas are less than 
1 or greater than 100 .
	 */
	
	public LineItem(int numberPizza, Pizza pizza) throws IllegalPizza {
		setNumber (numberPizza); //For number of pizza's attribute to be mutable.
		if (pizza == null) //check if a pizza object was supplied.
			throw new IllegalPizza ("A pizza was not supplied!");
		this.pizza = pizza;
	}//end LineItem 2 parameter constructor
	
	/**
	 * 1 parameter constructor, where number of pizza's is set to 1.
	 * @param pizza A pointer to the pizza object.
	 * @throws IllegalPizza If arguments are not legal.
	 */
	public LineItem (Pizza pizza) throws IllegalPizza {
		this (1, pizza);
	}//end LineItem constructor that defaults to a single pizza
	
	/**
	 * Sets the number of pizza's for the specific pizza object.
	 * @param number The number of pizza's.
	 * @throws IllegalPizza If the number of pizza's is greater than 
100 or less than 1.
	 */
	public void setNumber (int number) throws IllegalPizza {
		if (number > 100 || number < 1)
			throw new IllegalPizza ("Number of pizzas must lie between 1 and 100!");
		numPizza = number; 
	}// end setNumber
	
	/**
	 * Accessor for the pizza object. 
	 * @return A pointer to the pizza object.
	 */
	public Pizza getPizza () {
		return pizza;
	}// end getPizza()
	
	/**
	 * Accessor for the number of pizza's.
	 * @return The number of pizza's.
	 */
	public int getNumber () {
		return numPizza;
	}//end getNumber()
	
	/**
	 * Calculates the total cost of the line item order. 
	 * The method gives a 10% discount if the number of pizza's is between
10 and 20 inclusively. The method gives a 15% discount if the number of
pizza's is greater than 20.
	 * @return The total cost of the line item for the pizza object.
	 */
	public double getCost () {
		double totalCost = numPizza*pizza.getCost();
		if (numPizza >= 10 && numPizza <= 20)
			totalCost = totalCost*0.90;
		else if (numPizza > 20)
			totalCost = totalCost*0.85;
		return totalCost;
	}//end getCost()
	
	/**
	 * to string method that the number of pizza's of the 
	 * specific pizza item.The method puts a space in front of 
	 * the number of pizza's if it is less than 10.
	 * @return The string created in the method.
	 */
	//Overrides the toString method in the Object class.
	@Override
	public String toString () {
		String s;
		if (numPizza < 10)
			s = " " + numPizza + " " + pizza;
		else
			//over 10 number of pizza's, so no space.
			s = numPizza + " " + pizza;
		return s;
	}//end toString()
	
	/**
	 * Compares LineItem objects on the basis of total cost.
	 * @param other The other LineItem Object.
	 * @return The difference in price of the 2 line item objects. If the difference is 
less than 1 dollar, then the method returns 0.
	 */
	public int compareTo (LineItem other) {
		double item1 = getCost();
		double item2 = other.getCost();
		double difference = item2 - item1;
		// need to check both positive and negative one, in-case item1 is more expensive than item2.
		if (difference < 1 && difference > -1) 
			return 0;
		return (int) difference; 
	}//end compareTo()
}//end LineItem class
