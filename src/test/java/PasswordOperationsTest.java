import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PasswordOperationsTest {

    @Parameterized.Parameters
    public static Collection createTestData(){
        Object[][] data = new Object[][]{
                {"d5<T2Qb%d5<T2Qb%",true},
                { "w7w[;JB8d5<T2Qb%d",false},
                {"Th2?kK3-",true},
                {"74NU:xy)",true},
                {"}x3Sr8M{:",true},
                {"uV3.Pf$4",true},
                {"_lB7[d6N",true},
                {"n\"<Sd8B1",true},
                {",v3d5W",false},
                {",v3d W]Z",false}
        };
        return Arrays.asList(data);
    }

    String value1;
    Boolean result;

    public PasswordOperationsTest(String value1,Boolean result){
        this.value1 = value1;
        this.result = result;
    }

    @Test
    public void testParam(){
        Assert.assertTrue(PasswordOperations.isStringOk(value1) == result);
    }

    @Test
    public void testLowercaseLetterMissing(){
        Assert.assertTrue(PasswordOperations.isStringOk("AXA123#AMB")==false);
    }

    @Test
    public void testUppercaseLetterMissing(){
        Assert.assertTrue(PasswordOperations.isStringOk("axa123;acv668")==false);
    }

    @Test
    public void testNumberMissing(){
        Assert.assertTrue(PasswordOperations.isStringOk("axaWyu;acvrRr")==false);
    }

    @Test
    public void testTooLongString(){
        Assert.assertTrue(PasswordOperations.isStringOk("axa123;AAAAAacv668axa123;acv66")==false);
    }

    @Test
    public void testTooShortString(){
        Assert.assertTrue(PasswordOperations.isStringOk("aXa123;")==false);
    }

}
