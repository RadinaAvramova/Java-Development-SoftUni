package scubaDive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivingTests {

    private Diving diving;
    private DeepWaterDiver diver;

    @Before
    public void setUp() {
        diving = new Diving("SeaAdventure", 5);
        diver = new DeepWaterDiver("Elijah", 90);
    }

    @Test
    public void constructor_ShouldSetSuccessfullyValues() {

        String expectedName = "SeaAdventure";
        int expectedCapacity = 5;

        String actualName = diving.getName();
        int actualCapacity = diving.getCapacity();

        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedName, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_ShouldThrowArgumentNullExceptionForInvalidName() {
        new Diving(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ShouldThrowArgumentExceptionForInvalidCapacity() {
        new Diving("SeaAdventure", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMethod_ShouldThrowsExceptionForInvalidCapacity() {
        Diving diving1 = new Diving("Lake" , 0);
        diving1.addDeepWaterDiver(diver);
    }

    @Test
    public void removeMethod_ShouldReturnTrueIfDiverIsFound() {
        Diving ontario = new Diving("Ontario" , 2);
        ontario.addDeepWaterDiver(diver);

        boolean isRemove = ontario.removeDeepWaterDiver("Elijah");

        Assert.assertTrue(isRemove);
    }
}
