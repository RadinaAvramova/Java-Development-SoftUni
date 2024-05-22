package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarShopTests {
    private CarShop carShop;
    private Car Car;

    @Before
    public void setUp() {
        carShop = new CarShop();
        Car = new Car("Skoda", 198, 599.45);

        carShop.add(Car);
    }

    @Test(expected = NullPointerException.class)  // Tested N1
    public void addCarShouldThrow() {
        Car carNull = null;

        carShop.add(carNull);
    }

    @Test
    public void getCOuntShouldReturnOne() { // Tested N3
        int actualCount = carShop.getCount();
        int expectedCount = 1;
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void getCarsShouldReturnCorrectList() { //Not
        List<Car> expected = new ArrayList<>();
        expected.add(Car);

        List<Car> actualCars = carShop.getCars();

        assertEquals(expected, actualCars);
    }

    @Test
    public void findCarsMaxSPeedAboveShouldReturnCorrectList() { //Tested N2
        Car car2 = new Car("Car2", 10, 24);
        Car car3 = new Car("Car3", 199, 24);
        Car car4 = new Car("Car4", 192, 22);
        carShop.add(car2);
        carShop.add(car3);
        carShop.add(car4);

        List<Car> expectedCars = new ArrayList<>(Arrays.asList(Car, car3, car4));
        List<Car> actualCars = carShop.findAllCarsWithMaxHorsePower(190);

        assertEquals(expectedCars, actualCars);
    }


    @Test
    public void getMOstExpensiveCarShudlReturnCorrectCar() { //Tested N4
        Car car2 = new Car("Car2", 10, 24);
        Car car3 = new Car("Car3", 199, 24);
        Car car4 = new Car("Car4", 192, 22);
        carShop.add(car2);
        carShop.add(car3);
        carShop.add(car4);


        Car actualResult = carShop.getTheMostLuxuryCar();
        assertEquals(Car, actualResult);
    }

    @Test
    public void findAllCarsByBrandShouldReturnCorrectList() {
        Car car2 = new Car("Skoda", 10, 24);
        Car car3 = new Car("Car3", 199, 24);
        Car car4 = new Car("Car4", 192, 22);
        carShop.add(car2);
        carShop.add(car3);
        carShop.add(car4);


        List<Car> expectedCars = new ArrayList<>(Arrays.asList(Car, car2));
        List<Car> actualCars = carShop.findAllCarByModel("Skoda");

        assertEquals(expectedCars, actualCars);
    }

    @Test
    public void testRemoveCar() {
        Car car = new Car("Audi", 200, 20000.15);
        CarShop carShop = new CarShop();
        carShop.add(car);

        Assert.assertTrue(carShop.remove(car));
    }
}

