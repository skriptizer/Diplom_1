package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient diffirentIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        burger.addIngredient(ingredient);
        burger.addIngredient(diffirentIngredient);
        burger.moveIngredient(1, 0);

        assertEquals(diffirentIngredient, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getPrice()).thenReturn(30.0f);
        Mockito.when(diffirentIngredient.getPrice()).thenReturn(60.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(diffirentIngredient);

        float actualPrice = burger.getPrice();
        float expectedPrice = bun.getPrice()*2 + ingredient.getPrice() + diffirentIngredient.getPrice();

        assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceipt() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getPrice()).thenReturn(30.0f);
        Mockito.when(diffirentIngredient.getPrice()).thenReturn(60.0f);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredient.getName()).thenReturn("Mayonnaise");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(diffirentIngredient.getName()).thenReturn("cutlet");
        Mockito.when(diffirentIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(diffirentIngredient);

        String actualReceipt = burger.getReceipt();

        float expectedPrice = bun.getPrice()*2 + ingredient.getPrice() + diffirentIngredient.getPrice();

        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : burger.ingredients) {
            expectedReceipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        expectedReceipt.append(String.format("(==== %s ====)%n", bun.getName()));
        expectedReceipt.append(String.format("%nPrice: %f%n", expectedPrice));

        assertEquals(expectedReceipt.toString(), actualReceipt);
    }
}