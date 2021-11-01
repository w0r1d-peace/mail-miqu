package com.islet;

import com.huaban.analysis.jieba.JiebaSegmenter;

/**
 * @author tangJM.
 * @date 2021/11/1
 * @description
 */
public class AnalysisTest {
    public static void main(String[] args) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        /*单词*/
        System.out.println(segmenter.sentenceProcess("小明硕士毕业于中国科学院计算所，后在日本京都大学深造"));
        System.out.println(segmenter.sentenceProcess("这是一个伸手不见五指的黑夜"));
    }
}
