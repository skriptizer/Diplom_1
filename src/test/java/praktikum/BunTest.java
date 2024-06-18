package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {
    private  final String name;
    private  final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] Buns() {
        return new Object[][] {
                {"Черная", 100.0f},
                {null, 100f},
                {"", -100.0f},
                {"Black", 0.0f},
                {"Black123", 100.25f},
                {"Black bun", Float.MAX_VALUE},
                {"#Black", Float.MIN_VALUE},
        };
    }

    @Test
    public void getName() {
        Bun bun = new Bun(name, price);
        String actualName = bun.getName();

        assertEquals(name, actualName);
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        float actualPrice = bun.getPrice();

        assertEquals(price, actualPrice, 0);
    }
}