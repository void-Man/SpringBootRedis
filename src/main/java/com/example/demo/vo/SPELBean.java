package com.example.demo.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/1
 */
@Component
public class SPELBean {
    @Value("#{10 * 20}")
    private int intValue;

    @Value("#{'hello'+'world'}")
    private String strValue;

    @Value("#{T(java.lang.Math).random()*100}")
    private int randomIntl;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public int getRandomIntl() {
        return randomIntl;
    }

    public void setRandomIntl(int randomIntl) {
        this.randomIntl = randomIntl;
    }

    /**
     * 展示SpEl上下文变量
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/11/1
     */
    public String getNomalContext() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("key1", "value1");
        String value = parser.parseExpression("#key1").getValue(context, String.class);
        System.out.println("key1 = " + value);
        return value;
    }

    /**
     * 展示SpEl上下文变量
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/11/1
     */
    public String getRootContext() {
        ExpressionParser parser = new SpelExpressionParser();
        // 传递一个root对象
        EvaluationContext context = new StandardEvaluationContext("i am root");
        String root = parser.parseExpression("#root").getValue(context, String.class);
        System.out.println("root value = " + root);
        return root;
    }
}
