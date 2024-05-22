package magicGame;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import java.util.List;

public class MagicianTests {
    @Test(expected = NullPointerException.class)
    public void testUsernameShouldThrowException(){
        Magician magician = new Magician(null, 50);
    }
    @Test
    public void testUsernameShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        Assert.assertEquals("Pesho", magician.getUsername());
    }

    @Test(expected = NullPointerException.class)
    public void testUsernameWithEmptyShouldWork(){
        Magician magician = new Magician("", 50);
        Assert.assertEquals("", magician.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHealthShouldThrowException(){
        Magician magician = new Magician("Pesho", -10);
    }

    @Test
    public void testHealthShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        Assert.assertEquals(50, magician.getHealth());
    }

    @Test
    public void testGunsShouldWork(){
        Magic magic = new Magic("pepo", 10);
        Magician magician = new Magician("Pesho", 50);
        magician.addMagic(magic);

        List<Magic> expected = List.of(magic);
        Assert.assertEquals(expected, magician.getMagics());
    }

    @Test
    public void testTakeDamageShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        magician.takeDamage(5);

        Assert.assertEquals(45, magician.getHealth());
    }

    @Test
    public void testTakeDamageShouldWorkV2(){
        Magician magician = new Magician("Pesho", 5);
        magician.takeDamage(5);

        Assert.assertEquals(0, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageThrowException(){
        Magician magician = new Magician("Pesho", 0);
        magician.takeDamage(10);
    }

    @Test
    public void testAddGunShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertEquals(List.of(magic), magician.getMagics());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunThrowException(){
        Magician magician = new Magician("Pesho", 50);
        magician.addMagic(null);

    }

    @Test
    public void testRemoveGunShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertTrue(magician.removeMagic(magic));
    }

    @Test
    public void testGetGunShouldWork(){
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertEquals(magic, magician.getMagic("Pecata"));
    }

    @Test
    public void testGetGunReturnNull(){
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertEquals(null, magician.getMagic("Gosho"));
    }
}

