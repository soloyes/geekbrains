import org.junit.Assert;
import org.junit.Test;

public class Task1Test {
    Task1 task1 = new Task1();

    @Test
    public void test0(){
        int[] res = task1.method1(new int[]{1,2,4,4,2,3,4,1,7});
        int[] act = new int[]{1,7};
        Assert.assertArrayEquals(act, res);
    }

    @Test
    public void test1(){
        int[] res = task1.method1(new int[]{1,2,2,4,2,3,1,1,7});
        int[] act = new int[]{2,3,1,1,7};
        Assert.assertArrayEquals(act, res);
    }

    @Test (expected = RuntimeException.class)
    public void test2(){
        int[] res = task1.method1(new int[]{1,2,2,0,2,3,1,1,7});
    }

    //Input array length
    @Test
    public void test3(){
        int[] res = task1.method1(new int[]{});
        int[] act = null;
        Assert.assertArrayEquals(act, res);
    }
}
