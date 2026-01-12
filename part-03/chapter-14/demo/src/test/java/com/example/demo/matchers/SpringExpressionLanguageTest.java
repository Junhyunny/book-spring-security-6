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
        Expression sut = parser.parseExpression("'Hello World'");


        String message = (String) sut.getValue();


        assertEquals("Hello World", message);
    }


    @Test
    void method_invoke() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("'Hello World'.concat('!')");


        String message = (String) sut.getValue();


        assertEquals("Hello World!", message);
    }

    @Test
    void access_property() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("'Hello World'.bytes.length");


        var length = (Integer) sut.getValue();


        assertEquals(11, length);
    }

    @Test
    void using_context() {
        class CustomRoot {
            public String securityWorld() {
                return "Security World";
            }
        }
        EvaluationContext context = new StandardEvaluationContext(new CustomRoot());
        ExpressionParser parser = new SpelExpressionParser();
        Expression sut = parser.parseExpression("securityWorld()");


        String message = sut.getValue(context, String.class);


        assertEquals("Security World", message);
    }
}
