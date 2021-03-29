package com.wxf.keyword;

/**
 * Retry测试
 *
 * continue用法
 * 不使用Retry时输出：
 * 0  1  2  4  5  6  7
 * 0  1  2  4  5  6  7
 * 0  1  2  4  5  6  7
 * 0  1  2  4  5  6  7
 * 0  1  2  4  5  6  7
 *
 * 使用Retry时输出：
 * 0  1  2  0  1  2  0  1  2  0  1  2  0  1  2
 *
 *
 * Break用法
 *
 * 不使用Retry时输出：
 * 0  1  2
 * 0  1  2
 * 0  1  2
 * 0  1  2
 * 0  1  2
 *
 * 使用Retry时输出：
 * 0  1  2
 *
 * @author WangXiaofan777
 * @since  2020年9月28日 09:25:33
 */
public class RetryMain {

    public static void main(String[] args) {
        retry:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 3)
                    break retry;
                System.out.print(j);
                System.out.print("  ");
            }
            System.out.println();
        }

    }
}
