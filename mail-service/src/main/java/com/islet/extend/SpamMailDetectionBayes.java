package com.islet.extend;

import com.huaban.analysis.jieba.JiebaSegmenter;
import java.io.*;
import java.util.List;
import java.util.*;

public class SpamMailDetectionBayes {
    public static final String MAIL_INDEX_PATH = "C:/Users/EDZ/Desktop/mailbox/trec06c/full/index";//邮件索引
    public static Map<String, Integer> spamMailMap = new HashMap<>();//垃圾邮件分词表
    public static Map<String, Integer> hamMailMap = new HashMap<>();//正常邮件分词表

    public static Integer spamMailSegNum = 0;//垃圾邮件分词总数量
    public static Integer hamMailSegNum = 0;//正常邮件分词总数量
    public static Map<String, Double> spamMailRateMap = new HashMap<>();//垃圾邮件分词概率表
    public static Map<String, Double> hamMailRateMap = new HashMap<>();//正常邮件分词概率表

    public static final int TRAIN_MAX_NUM = 5000;//训练邮件最大数
    public static final int TEST_MAX_NUM = 1000;//测试邮件最大数

    public static double probability[] = new double[TEST_MAX_NUM];//测试邮件为垃圾邮件的概率数组
    public static String testRealType[] = new String[TEST_MAX_NUM];//测试邮件真实的类型数组
    public static int trainNum = 0;//实际训练邮件数
    public static Integer spamTrainMailNum = 0;//垃圾邮件总数量
    public static double spamTrainRate = 0d;//垃圾邮件率
    public static int testNum = 0;//实际测试邮件数

    public static double trainTime = 0d;//训练时间
    public static double testTime = 0d;//测试时间

    public static void main(String[] args) throws IOException {
        SpamMailDetectionBayes smc = new SpamMailDetectionBayes();
        smc.TrainMail();
        smc.TestMail();
    }

    /**
     * 给定邮件,分词,根据分词结果判断是垃圾邮件的概率
     * P(A|BC)= P（A|B) P(A|C)/P(A)
     * P(Spam|t1,t2,t3……tn)=（P1*P2*……PN）/(P1*P2*……PN+(1-P1)*(1-P2)*……(1-PN))
     */
    public void TestMail() throws IOException {
        long testStartTime = System.currentTimeMillis();
        //垃圾邮件率
        spamTrainRate = (double) spamTrainMailNum / (double) trainNum;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MAIL_INDEX_PATH));
        int nowIndex = 0;
        String indexLine;
        int start_count = TRAIN_MAX_NUM;//从训练集之后开始
        System.out.println("\n-------------------------------测试------------------------------");
        while ((indexLine = bufferedReader.readLine()) != null && testNum < TEST_MAX_NUM) {
            //跳过训练集
            if (nowIndex < start_count) {
                nowIndex++;
                continue;
            } else {
                //分离出邮件真实类型和文件名索引
                String[] typeAndIndex = indexLine.split(" ");
                String type = typeAndIndex[0];
                List<String> wordList = getWordList(typeAndIndex[1]);
                //该邮件是否手动设置概率的标志
                int flag = 0;
                //P(Spam)*  乘积P(Spam|ti)*P(ti)/P(Spam) (i从1-n)
                //spamTrainRate = P(Spam)
                double rate = spamTrainRate;
                //[1-P(Spam)]*  乘积[1-P(Spam|ti)]*P(ti)/[1-P(Spam)] (i从1-n)
                // 1 - spamTrainRate = [1-P(Spam)]
                double tempRate = 1 - spamTrainRate;
                for (String s : wordList) {
                    if (spamMailRateMap.containsKey(s)) {
                        double spamTmp = spamMailRateMap.get(s);
                        rate *= spamTmp * (((double)(spamMailMap.get(s) + (hamMailMap.containsKey(s)? hamMailMap.get(s): 0))) / (double) (spamMailSegNum + hamMailSegNum)) / spamTrainRate;
                        tempRate *= (1d - spamTmp) * (((double)spamMailMap.get(s)+(hamMailMap.containsKey(s)? hamMailMap.get(s): 0)) / (double) (spamMailSegNum + hamMailSegNum)) / spamTrainRate;;
                        if (testNum == 1 || testNum == 2){
                            System.out.println("rate:"+rate+"\ntempRate:"+tempRate);
                        }
                        //当有一个概率非常趋近于0时，需要进行判断
                        // 判断该邮件更接近垃圾邮件还是正常邮件
                        if (rate < Math.pow(10, -300) || tempRate < Math.pow(10, -300)){
                            if (rate > 1){
                                probability[testNum] = 1d;
                                flag = 2;
                                break;
                            }else if (tempRate > 1){
                                probability[testNum] = 0d;
                                flag = 3;
                                break;
                            }else {
                                rate *= Math.pow(10, 7);
                                tempRate *= Math.pow(10, 7);
                            }
                        }
                    }

                }
                if (flag == 0) { //flag!=0代表已经手动设置过概率
                    probability[testNum] = rate / (rate + tempRate);
                    System.out.println(probability[testNum]);
                }
                testRealType[testNum++] = type;
            }
        }
        //关闭文件读流
        bufferedReader.close();
        long testFinishTime = System.currentTimeMillis();
        testTime = (testFinishTime - testStartTime) / 1000;
        System.out.println("-------------------------------测试完成------------------------------");
    }

    //得到分词集合
    private List getWordList(String index) {
        index = index.replace("..", "C:/Users/EDZ/Desktop/mailbox/trec06c");
        String str = readFile(index);
        JiebaSegmenter jb = new JiebaSegmenter();
        List<String> tempList = jb.sentenceProcess(str);
        List<String> rightList = new ArrayList<>();
        for (String s : tempList) {
            //过滤掉标点符号等无意义分词
            if (s.length() > 1)
                rightList.add(s);
        }
        return rightList;
    }

    /**
     * 从给定的垃圾邮件、正常邮件语料中建立map <切出来的词,出现的频率> 
     */
    public void TrainMail() throws IOException {
        long trainStartTime = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MAIL_INDEX_PATH));
        //得到训练集概率表
        String indexLine;
        System.out.println("-----------------------------------训练----------------------------------");
        while((indexLine = bufferedReader.readLine())!=null && trainNum < TRAIN_MAX_NUM){
            trainNum++;
         //   System.out.println(indexLine);
            //分离出邮件真实类型和文件名索引
            String[] typeAndIndex = indexLine.split(" ");
            String type = typeAndIndex[0];
            List<String> wordList = getWordList(typeAndIndex[1]);
            if (type.equals("spam")) {
                spamTrainMailNum ++;
                spamMailSegNum += wordList.size();
                for (String s : wordList) {
                    spamMailMap.put(s, spamMailMap.containsKey(s) ? spamMailMap.get(s) + 1 : 1);
                }
            }else {
                hamMailSegNum += wordList.size();
                for (String s : wordList) {
                    hamMailMap.put(s, hamMailMap.containsKey(s) ? hamMailMap.get(s) + 1 : 1);
                }
            }
        }
        bufferedReader.close();
        //垃圾邮件率  垃圾邮件数/总邮件数
        spamTrainRate = (double) spamTrainMailNum / (double) trainNum;
        getSpamMailRateMap();
        long trainFinishTime = System.currentTimeMillis();
        trainTime = (trainFinishTime - trainStartTime) / 1000;
    }

    /**
     * 邮件中出现ti时,该邮件为垃圾邮件的概率
     * P1(ti)为ti出现在垃圾邮件中的次数/垃圾邮件分词总数
     * P2(ti)为ti出现在正常邮件中的次数/正常邮件分词总数
     * P(ti)为ti出现在所有邮件中的次数/邮件分词总数
     * P(Spam|ti)=P1(ti)/((P1(ti)+P2(ti))
     * P(ham|ti)=P2(ti)/((P1(ti)+P2(ti))
     * P(ti|Spam)=P(Spam|ti)*P(ti)/P(Spam)
     * P(ti|ham)=P(ham|ti)*P(ti)/P(ham)
     *          =[1-P(Spam|ti)]*P(ti)/[1-P(Spam)]
     *
     * P(Spam|t1,t2,...,tn)
     * =P(Spam)*P(t1|Spam)P(t2|Spam)*...*P(tn|Spam)/[P(Spam)*P(t1|Spam)P(t2|Spam)*...*P(tn|Spam)+P(ham)*P( t1|ham)P( t2|ham)*...*P( tn|ham)]
     *
     * P(Spam)*P(t1|Spam)*P(t2|Spam)*...*P(tn|Spam)
     * =P(Spam)*[P(Spam|t1)*P(t1)/P(Spam)]*...*[P(Spam|tn)*P(tn)/P(Spam)]
     *
     * P(ham)*P(t1|ham)*P(t2|ham)*...*P(tn|ham)
     * =[1-P(Spam)]*[1-P(Spam|t1)]*P(t1)/[1-P(Spam)]*...*[1-P(Spam|tn)]*P(tn)/[1-P(Spam)]
     * 如果直接用“ti在垃圾邮件中出现的次数/ti出现的总次数”得到结果的话，在垃圾邮件占多数的情况下，会影响结果。
     * 因此需要用“ti出现的概率代替ti出现的次数”进行计算
     */
    public void getSpamMailRateMap() {
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




    /**
     * 读文件 
     */
    public String readFile(String filePath) {
        StringBuffer str = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(filePath)), "gbk"));
            String tmp = "";
            int flag = 0;
            while ((tmp = br.readLine()) != null){
                if (flag == 1){
                    str.append(tmp);
                }
                if (tmp.equals("")||tmp.length() == 0){
                    flag = 1;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}