package com.islet.extend;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.*;
import java.util.*;

/** 训练数据集服务
 * @author tangJM.
 * @date 2021/11/1
 * @description
 */
public class TrainingDatasetService {

    public static final int TRAIN_MAX_NUM = 50000;//训练邮件最大数
    public static final int TEST_MAX_NUM = 10000;//测试邮件最大数

    public static Map<String, Integer> spamMailMap = new HashMap<>();//垃圾邮件分词表
    public static Map<String, Integer> hamMailMap = new HashMap<>();//正常邮件分词表
    public static Integer spamMailSegNum = 0;//垃圾邮件分词总数量
    public static Integer hamMailSegNum = 0;//正常邮件分词总数量
    public static Map<String, Double> spamMailRateMap = new HashMap<>();//垃圾邮件比率
    public static Map<String, Double> hamMailRateMap = new HashMap<>();//正常邮件比率




    public static void main(String[] args) {
        TrainingDatasetService tds = new TrainingDatasetService();
        tds.readIndexNumber();
    }

    /**
     * 读取索引数
     */
    public void readIndexNumber() {
        String dir = "C:/Users/EDZ/Desktop/mailbox/trec06c";
        int trainNum = 0;
        String indexLine = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dir + "/full/index"));
            while((indexLine = bufferedReader.readLine())!=null && trainNum < TRAIN_MAX_NUM) {
                trainNum++;
                String[] typeAndIndex = indexLine.split(" ");
                String type = typeAndIndex[0];
                String index = typeAndIndex[1].replace("..", dir);
                // 读取邮件文本内容，拼接成字符串
                String content = readFile(index);
                // 分词
                List<String> participleList = participle(content);
                // 分词清洗
                List<String> rightList = participlesCleaning(participleList);
                // 归类
                classify(type, rightList);

                System.out.println(spamMailMap);
                System.out.println("=============================================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取邮件文本内容，拼接成字符串
     * @param index
     * @return
     */
    private String readFile(String index) {
        try {
            StringBuffer str = new StringBuffer();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(index), "GBK");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String tmp = "";
            int flag = 0;
            while ((tmp = bufferedReader.readLine()) != null){
                if (flag == 1){
                    str.append(tmp);
                }
                if (tmp.equals("") || tmp.length() == 0){
                    flag = 1;
                }
            }

            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 分词
     */
    public List<String> participle(String content) {
        JiebaSegmenter jb = new JiebaSegmenter();
        List<String> list = jb.sentenceProcess(content);
        return list;
    }


    /**
     * 分词清理
     * @param participles
     * @return
     */
    public List<String> participlesCleaning(List<String> participles) {
        List<String> rightList = new ArrayList<>();
        for (String participle : participles){
            String str = participle.trim();
            if(str.length() > 1)
                rightList.add(str);
        }

        return rightList;
    }

    /**
     * 归类
     */
    public void classify(String type, List<String> rightList) {
        if (type.equals("spam")) {
            spamMailSegNum += rightList.size();
            for (String s : rightList) {
                spamMailMap.put(s, spamMailMap.containsKey(s) ? spamMailMap.get(s) + 1 : 1);
            }
        }else {
            hamMailSegNum += rightList.size();
            for (String s : rightList) {
                hamMailMap.put(s, hamMailMap.containsKey(s) ? hamMailMap.get(s) + 1 : 1);
            }
        }
    }

    /**
     * 分词概率统计
     */
    public void WordSegmentationProbabilityStatistics() {
        for (Iterator iter = spamMailMap.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            double rate = (double) spamMailMap.get(key) / (double) spamMailSegNum;
            double allRate = rate;
            if (hamMailMap.containsKey(key)) {
                allRate += (double) hamMailMap.get(key) / (double) hamMailSegNum;
            }else{ //如果正常邮件中未出现该词，则默认在正常邮件中出现过1次，防止判断过于绝对
                allRate += 1d / (double) hamMailSegNum;
            }
            spamMailRateMap.put(key, rate / allRate);
        }
    }
}
