import org.junit.Assert;
import org.junit.Test;

public class Task2Test {

    Task2 task2 = new Task2();

    @Test
    public void test0(){
        boolean res = task2.method2(new int[]{});
        Assert.assertFalse(res);
    }

    @Test
    public void test1(){
        boolean res = task2.method2(new int[]{1,2,3,4});
        Assert.assertFalse(res);
    }

    @Test
    public void test2(){
        boolean res = task2.method2(new int[]{1,1,4,4});
        Assert.assertTrue(res);
    }

    @Test
    public void test3(){
        boolean res = task2.method2(new int[]{1,4});
        Assert.assertTrue(res);
    }
}
