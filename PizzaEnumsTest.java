import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

class PizzaEnumsTest {

	// For convenience:
	Pizza.Size sSmall = Pizza.Size.Small;
	Pizza.Size sMedium = Pizza.Size.Medium;
	Pizza.Size sLarge = Pizza.Size.Large;
	Pizza.Cheese cSingle = Pizza.Cheese.Single;
	Pizza.Cheese cDouble = Pizza.Cheese.Double;
	Pizza.Cheese cTriple = Pizza.Cheese.Triple;
	Pizza.Topping tNone = Pizza.Topping.None;
	Pizza.Topping tSingle = Pizza.Topping.Single;

	// Some legal argument tests:
	// Default constructor.
	// Also tests for toString and getCost.
	@Test
	public void testPizza() throws Exception {
		Pizza pizza1 = new Pizza();
		assertEquals("Small pizza, Single cheese, ham. Cost: $8.50 each.", pizza1.toString());
		assertEquals(8.50, pizza1.getCost(), 0.01);
	}
	
	// Six parameter constructor, parameters:
	// size, vegetarian, cheese, pineapple, greenPepper, ham
	@Test
	public void testPizza1() throws Exception {
		Pizza pizza1 = new Pizza(sSmall, false, cSingle, tNone, tNone, tNone);
		assertEquals("Small pizza, Single cheese. Cost: $7.00 each.", pizza1.toString());
		assertEquals(7.00, pizza1.getCost(), 0.01);		
	}
	@Test
	public void testPizza2() throws Exception {
		Pizza pizza1 = new Pizza(sMedium, true, cTriple, tNone, tNone, tNone);
		assertEquals("Medium vegetarian pizza, Triple cheese. Cost: $12.00 each.", pizza1.toString());
		assertEquals(12.00, pizza1.getCost(), 0.01);		
	}

	// Five parameter constructor, parameters:
	// size, cheese, pineapple, greenPepper, ham
	@Test
	public void testPizza3() throws Exception {
		Pizza pizza1 = new Pizza(sLarge, cDouble, tSingle, tSingle, tSingle);
		assertEquals("Large pizza, Double cheese, pineapple, green pepper, ham. Cost: $17.00 each.", pizza1.toString());
		assertEquals(17.00, pizza1.getCost(), 0.01);		
	}
	@Test
	public void testPizza4() throws Exception {
		Pizza pizza1 = new Pizza(sLarge, cSingle, tSingle, tNone, tSingle);
		assertEquals("Large pizza, Single cheese, pineapple, ham. Cost: $14.00 each.", pizza1.toString());
		assertEquals(14.00, pizza1.getCost(), 0.01);		
	}

	// Some illegal argument tests
	// Six parameter constructor
	@Test
	public void testPizza7() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(null, false, cSingle, tSingle, tSingle, tSingle));
	}
	@Test
	public void testPizza10() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(sSmall, false, null, tSingle, tSingle, tSingle));
	}
	@Test
	public void testPizza13() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(sSmall, false, cSingle, null, tSingle, tSingle));
	}
	@Test
	public void testPizza16() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(sSmall, false, cSingle, tSingle, null, tSingle));
	}
	@Test
	public void testPizza19() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(sSmall, false, cSingle, tSingle, tSingle, null));
	}
	
	// Cannot have ham if vegetarian
	@Test
	public void testPizza20() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(sSmall, true, cSingle, tSingle, tNone, tSingle));
	}

	@Test
	public void testEqualsObject() throws Exception {
		Pizza pizza1 = new Pizza(sLarge, cSingle, tSingle, tSingle, tSingle);
		Pizza pizza2 = new Pizza(sLarge, cSingle, tSingle, tSingle, tSingle);
		Pizza pizza3 = new Pizza(sMedium, cSingle, tSingle, tSingle, tSingle);
		Pizza pizza4 = new Pizza(sLarge, cDouble, tSingle, tSingle, tSingle);
		Pizza pizza5 = new Pizza(sLarge, cSingle, tNone, tNone, tSingle);
		assertEquals(pizza1, pizza2);
		assertTrue(pizza1.equals(pizza2));
		assertFalse(pizza1.equals(pizza3));
		assertFalse(pizza1.equals(pizza4));
		assertFalse(pizza1.equals(pizza5));
		assertFalse(pizza1.equals("Hello! I am not a pizza!"));
	}

	@Test
	public void testClone() throws Exception {
		Pizza pizza1 = new Pizza(sLarge, cSingle, tSingle, tSingle, tSingle);
		Pizza pizza2 = pizza1.clone();
		assertEquals(pizza1, pizza2);
	}

	@Test
	public void testFileSave() throws Exception {
    	String testFile = "OnePizza.dat";
		Pizza pizza1 = new Pizza(sLarge, cTriple, tSingle, tSingle, tSingle);
		Pizza pizza2;
    	try (ObjectOutputStream binFileOut = new ObjectOutputStream(new FileOutputStream(testFile))) {
            binFileOut.writeObject(pizza1);
        }
        try (ObjectInputStream binFileIn = new ObjectInputStream(new FileInputStream(testFile))) {
            pizza2 = (Pizza)binFileIn.readObject();
        }
        assertEquals(pizza1, pizza2, "Comparing object from file to object saved.");
    }

}
