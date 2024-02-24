package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상있으면 중복오류가 발생한다")
    public void findBeanByParentTypeDuplicate(){

//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        assertThrows(NoUniqueBeanDefinitionException.class,()->
                ac.getBean(DiscountPolicy.class));
    }
    
    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상있으면 빈 이름을 지정하면 된다")
    public void findBeanByParentTypeBeanName(){

        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회하기")
    public void findBeanBySubType(){

        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    public void findAllBeanByParentType(){

        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String beanName : beansOfType.keySet()) {
            System.out.println("빈 이름 = " + beanName+","+ "빈 타입 = "+beansOfType.get(beanName));
        }
        System.out.println("beansOfType = "+beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Object 타입으로 모두 조회하기")
    public void findAllBeansByObjectType(){

        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String beanName: beansOfType.keySet()){
            Object bean= ac.getBean(beanName);
            System.out.println("빈 이름="+beanName+","+"빈 타입= "+beansOfType.get(beanName));
        }

    }

    /*@Test
    @DisplayName("Object 타입으로 우리가 등록한 빈 조회하기")
    public void findApplicationBeansByObjectType(){


        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){

                System.out.println("빈 이름 = " + beanDefinitionName);

            }

        }


        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String beanName : beansOfType.keySet()) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanName);
                System.out.println("빈 이름 = " + beanName+","+ "빈 타입 = "+beansOfType.get(beanName));
            }

        }


    }*/

    @Configuration

    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }



}
