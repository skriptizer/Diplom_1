package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final String name;
    private final float price;
    private final IngredientType type;

    public IngredientTest(IngredientType type, String name, float price) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Parameterized.Parameters
    public static Object[][] Ingredients() {
        return new Object[][] {
                {IngredientType.SAUCE, "Майонез", 100.0f},
                {IngredientType.SAUCE, "Mayonnaise", 100f},
                {IngredientType.SAUCE, null, -100.0f},
                {IngredientType.SAUCE, "Mayonnaise123", 0.0f},
                {IngredientType.SAUCE, "#Mayonnaise", 100.25f},
                {IngredientType.SAUCE, "Mayonnaise with cheese", Float.MAX_VALUE},
                {IngredientType.FILLING, "", Float.MIN_VALUE},
        };
    }

    @Test
    public void getPriceReturnPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        float actualPrice = ingredient.getPrice();

        assertEquals(price, actualPrice, 0);
    }

    @Test
    public void getNameReturnName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        String actualName = ingredient.getName();

        assertEquals(name, actualName);
    }

    @Test
    public void getTypeReturnType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType actualType = ingredient.getType();

        assertEquals(type, actualType);
    }
}