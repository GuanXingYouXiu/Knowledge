package com.example.demo.util;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: RandomUtil
 * @Description: 随机数生成
 * @author: LZA
 * @date: 2019-03-09
 */
public class RandomUtil {

    /**
     * 随机数操作,可以指定字符串的某个位置是什么范围的值
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result = Math.round(Math.random() * 25 + 65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    //产生a-z的ASCII码(Math.random()*25+97)
                    //产生A-Z的ASCII码
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 随机生成一个字符串，列如：684eaaa415ba4b70962b00c47d8fd676
     */
    public static String randomStr() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
