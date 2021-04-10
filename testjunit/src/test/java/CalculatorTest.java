import com.yc.biz.Calculator;
import org.junit.*;

//测试用例类
public class CalculatorTest {
    private Calculator cal;  //待测试的单元
    @BeforeClass
    public static void bc(){
        System.out.println("beforeclass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
        cal = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void ac(){
        System.out.println("afterclass");
    }

    @Test
    public void add() {
        System.out.println("add测试");
        Assert.assertEquals(3,cal.add(1,2));
    }

    @Test
    public void sub() {
        System.out.println("sub测试");
        Assert.assertEquals(1,cal.add(2,1));
    }

}