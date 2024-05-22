package restaurant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;



public class RestaurantTests {

    private Restaurant restaurant;
    private FullTimeWaiter waiter;

//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        restaurant = new Restaurant("Happy", 5);
        waiter = new FullTimeWaiter("Toma", 8);
//        System.setOut(new PrintStream(outContent));

    }

//    @After
//    public void restoreStreams() {
//        System.setOut(originalOut);
//    }

    @Test
    public void constructor_ShouldSetSuccessfullyValues() {
        String expectedName = "Happy";
        int expectedCapacity = 5;

        assertEquals(expectedName, restaurant.getName());
        assertEquals(expectedCapacity, restaurant.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void constructor_ShouldThrowArgumentNullExceptionForInvalidName() {
        new Restaurant(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ShouldThrowArgumentExceptionForInvalidCapacity() {
        new Restaurant("Parma", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMethod_ShouldThrowExceptionForInvalidCapacity() {
        Restaurant happy = new Restaurant("Happy", 0);
        happy.addFullTimeWaiter(waiter);
    }

    @Test
    public void removeMethod_ShouldReturnTrueIfWaiterIsFound() {
        Restaurant greek = new Restaurant("GreekRestaurant", 2);
        waiter = new FullTimeWaiter("Nikos", 8);
        greek.addFullTimeWaiter(waiter);
        assertTrue(greek.removeFullTimeWaiter("Nikos"));
    }

    @Test
    public void getCountMethod_ShouldReturnWaitersCount() {
        restaurant.addFullTimeWaiter(waiter);
        assertEquals(1, restaurant.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addWaiterMethod_ShouldThrowArgumentExceptionForExistingWaiter() {
        restaurant.addFullTimeWaiter(waiter);
        restaurant.addFullTimeWaiter(waiter);
    }

    @Test
    public void getEfficiency_ShouldReturnWaitersStrengthValue() {
        assertEquals(8, waiter.getEfficiency(), 0.1);
    }

//    @Test
//    public void mainMethod_ShouldPrintText() {
//
//        Main.main(new String[0]);
//        assertEquals("Welcome to the Restaurant!", outContent.toString());
//
//    }
}
