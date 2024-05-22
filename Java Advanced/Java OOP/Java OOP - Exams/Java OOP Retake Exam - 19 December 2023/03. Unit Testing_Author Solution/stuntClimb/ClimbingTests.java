package stuntClimb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ClimbingTests {


    private Climbing climbing;
    private RockClimber climber;

    @Before
    public void setUp() {
        climbing = new Climbing("MountainAdventure", 5);
        climber = new RockClimber("Elijah", 120);
    }

    @Test
    public void constructor_ShouldSetSuccessfullyValues() {

        String expectedName = "MountainAdventure";
        int expectedCapacity = 5;

        String actualName = climbing.getName();
        int actualCapacity = climbing.getCapacity();

        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedName, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_ShouldThrowArgumentNullExceptionForInvalidName() {

        new Climbing(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ShouldThrowArgumentExceptionForInvalidCapacity() {

        new Climbing("MountainAdventure", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMethod_ShouldThrowsExceptionForInvalidCapacity() {
        Climbing pirin = new Climbing("Pirin" , 0);
        pirin.addRockClimber(climber);
    }

    @Test
    public void removeMethod_ShouldReturnTrueIfDiverIsFound() {

        Climbing vitosha = new Climbing("Vitosha" , 2);
        vitosha.addRockClimber(climber);

        boolean isRemove = vitosha.removeRockClimber("Elijah");

        Assert.assertTrue(isRemove);
    }

    @Test
    public void getCountMethod_ShouldReturnRightDiversCount() {

        climbing.addRockClimber(climber);
        Assert.assertEquals(1, climbing.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addClimberMethod_ShouldThrowArgumentExceptionForExistingClimber() {

        climbing.addRockClimber(climber);
        climbing.addRockClimber(climber);

    }

    @Test
    public void getStrength_ShouldReturnClimbersStrengthValue() {

        Assert.assertEquals(120, climber.getStrength(), 0.1);
    }

}
