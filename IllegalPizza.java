/**
 * An Exception  thrown by the Pizza and LineItem objects if 
 * parameters are
 * <ul>
 * <li> The size is null.</li>
 * <li> The vegetarian option isn't a boolean value.</li>
 * <li> The amount of cheese is null.</li>
 * <li> The pineapple is null.</li>
 * <li> The amount of green pepper is null. </li>
 * <li> The amount of ham is null. </li>
 * <li> The vegetarian option is true and ham is single.</li>
 * </ul>
 * @author Renner Siebens (18rgs1)
 */

public class IllegalPizza extends Exception {

	private static final long serialVersionUID = -5935590159508055457L;
	
	/**
	 * Supplied a default message.
	 */
	public IllegalPizza() {
		super ("Illegal parameter value supplied to the Pizza object.");
	}//end IllegalPizza()
	/**
	 * Passes the message supplied to the exception from LineItem 
	 * or Pizza Objects.
	 * @param message A specific message.
	 */
	public IllegalPizza (String message) {
		super(message);
	}//end IllegalPizza (String message)
}//end IllegalPizza class
