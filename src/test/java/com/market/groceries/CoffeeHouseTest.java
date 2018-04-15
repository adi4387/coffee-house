package com.market.groceries;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CoffeeHouseTest {
	
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
