package com.example.demo.matchers;

import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringExpressionLanguageTest {

    @Test
    void simple_message() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("'Hello World'"); // 1


        String message = (String) sut.getValue(); // 2


        assertEquals("Hello World", message); // 3
    }


    @Test
    void method_invoke() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("'Hello World'.concat('!')"); // 1


        String message = (String) sut.getValue(); // 2


        assertEquals("Hello World!", message); // 3
    }

    @Test
    void access_property() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("'Hello World'.bytes.length"); // 1


        var length = (Integer) sut.getValue(); // 2


        assertEquals(11, length); // 3
    }

    @Test
    void using_context() {
        class CustomRoot {
            public String securityWorld() {
                return "Security World";
            }
        }
        EvaluationContext context = new StandardEvaluationContext(new CustomRoot()); // 1
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("securityWorld()"); // 2


        String message = sut.getValue(context, String.class); // 3


        assertEquals("Security World", message); // 4
    }
}
