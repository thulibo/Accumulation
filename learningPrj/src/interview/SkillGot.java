package interview;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @version 创建时间：2015年4月2日 下午4:26:38
 *
 * @author bjlibo
 *
 */

public class SkillGot {

    /**
     * tx的 关于int<->Integer 装箱/拆箱的深入理解
     * 
     */
    public static void testNullIntegerFeature() {
        try {
            List<Integer> integerList = new ArrayList<Integer>();

            Integer elementA = 5;
            Integer elementB = null;

            // 此处如果把5换为elementA；对比异同和原因！
            if (elementB == 5) {
                System.out.println("nice~ Integer null值和int值判==没有抛异常！");
            }

            integerList.add(elementA);
            integerList.add(elementB);

            int sum = 0;

            for (Integer i : integerList) {
                // 当i==null时，Integer拆箱时调用Integer.intValue()，悲剧鸟~
                sum += i;
            }

            System.out.println("sum=" + sum);
        } catch (Exception e) {
            System.out.println("悲剧鸟~" + e + ", 异常堆栈为：");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testNullIntegerFeature();
    }

}
