package com.islet.support.spam;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class SpamMailDetectionBayes {
    public static final String MAIL_INDEX_PATH = "C:/Users/EDZ/Desktop/mailbox/trec06c/full/index";//邮件索引
    public static Map<String, Integer> spamMailMap = new HashMap<>();//垃圾邮件分词表
    public static Map<String, Integer> hamMailMap = new HashMap<>();//正常邮件分词表

    public static Integer spamMailSegNum = 0;//垃圾邮件分词总数量
    public static Integer hamMailSegNum = 0;//正常邮件分词总数量
    public static Map<String, Double> spamMailRateMap = new HashMap<>();//垃圾邮件分词概率表
    public static Map<String, Double> hamMailRateMap = new HashMap<>();//正常邮件分词概率表
    public static double typeThreshold = -0.05d;//分类阈值,初值-0.05
    public static final double MAX_TYPE_THRESHOLD = 1.051d;//分类阈值最大值，浮点数累加会产生误差，故设置多一位小数
    public static final double TYPE_THRESHOLD_INCREASES = 0.05;//分类阈值增幅为0.1

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

    /*评价指标*/
    public static int TP = 0;//实际为垃圾，预测也为垃圾
    public static int FN = 0;//实际为垃圾，预测却为正常
    public static int FP = 0;//实际为正常，预测却为垃圾
    public static int TN = 0;//实际为正常，预测也为正常

    /*被分对的样本数除以所有的样本数，通常来说，正确率越高，分类器越好*/
    public static double accuracy = 0;//准确率Accuracy = (TP+TN)/(TP+FP+TN+FN)
    /*被分为正例的示例中实际为正例的比例*/
    public static double precision = 0;//精确率precision=TP/(TP+FP)
    /*所有负例中被分对的比例，衡量了分类器对负例的识别能力*/
    public static double specificity = 0;//特效率specificity=TN/N
    /*所有正例中被分对的比例，衡量了分类器对正例的识别能力*/
    public static double recall = 0;//召回率recall=TP/(TP+FN) = 灵敏度（Sensitivity）
    /*F1-Score指标综合了Precision与Recall的产出的结果。
    F1-Score的取值范围从0到1的，1代表模型的输出最好，0代表模型的输出结果最差。*/
    public static double F1_Score = 0;//平衡F分数:准确率和召回率的调和平均数

    /*True Positive Rate(TPR)和False Positive Rate(FPR)分别构成ROC曲线的y轴和x轴。*/
    /*实际正样本中被预测正确的概率*/
    public static double TPR = 0;//TPR=TP/(TP+FN) = recall = Sensitivity
    /*实际负样本中被错误预测为正样本的概率*/
    public static double FPR = 0;//FPR=FP/(FP+TN)

    //ROC坐标集合，键为阈值，值为TPR和FPR组成的数组
    List<Double[]> ROCLocalList = new ArrayList<Double[]>();

    /*Precision和Recall则分别构成了PR曲线的y轴和x轴。*/



    /*public static List<String> clearList = new ArrayList<String>();

    static {
        clearList.add("");
        clearList.add(" ");
        clearList.add("　　");
        clearList.add("\"");
        clearList.add("!");
        clearList.add("$");
        clearList.add("%");
        clearList.add("(");
    }*/


    public static void main(String[] args) throws IOException {
        SpamMailDetectionBayes smc = new SpamMailDetectionBayes();
        smc.TrainMail();
        String content = "-------- Forwarding messages --------\n" +
                "From: \"whb\"<wu_huan_bin@163.com>\n" +
                "Date: 2020-11-19 23:57:28\n" +
                "To: w0r1d_space@tom.com, chen_pan@tom.com, liyouwei@tom.com, zhouyaocun@tom.com, wuhuanbin@tom.com\n" +
                "Subject: 不再油腻增1.3T别克新款君越已申报\n" +
                "版权声明:本文版权为网易汽车所有,转载请注明出处。 \n" +
                "\n" +
                "网易汽车11月14日报道 日前,根据工信部申报信息:上汽通用别克中期改款君越车型正式申报。新车采用全新的外观设计,并为入门车型搭载1.3T发动机,高配车型仍为2.0T动力。\n" +
                "\n" +
                "不再油腻/增1.3T 别克新款君越已申报\n" +
                "\n" +
                "不再油腻/增1.3T 别克新款君越已申报\n" +
                "\n" +
                "↑普通版(搭1.3T和2.0T),↓Avenir版(搭2.0T)\n" +
                "\n" +
                "不再油腻/增1.3T 别克新款君越已申报\n" +
                "\n" +
                "不再油腻/增1.3T 别克新款君越已申报\n" +
                "\n" +
                "不再油腻/增1.3T 别克新款君越已申报\n" +
                "\n" +
                "【申报信息】\n" +
                "\n" +
                "新车尺寸:长5026mm、宽1866mm、高1462mm;轴距2905mm;\n" +
                "\n" +
                "整备质量:1540kg(1.3T车型)、1600kg、1640kg;\n" +
                "\n" +
                "轮胎规格:235/55R17(1.3T车型)、235/50R18、245/45R19;\n" +
                "\n" +
                "最高车速:205km/h(1.3T车型)、235km/h;\n" +
                "\n" +
                "外饰选装部位:前组合灯、前雷达、后保、侧雷达、门把手、双天窗、字牌、下格栅、轮辋、外后视镜、玻璃黑边、摄像头;\n" +
                "\n" +
                "发动机:\n" +
                "\n" +
                "1)1.3T:代号L3Z、排量1341ml、功率116kW、油耗申报值5.9L/100km;\n" +
                "\n" +
                "2)2.0T:代号LSY、排量1998ml、功率172kW、油耗申报值6.9L/100km。\n" +
                "\n" +
                "王海陆 本文来源:网易汽车 作者:leo 责任编辑:汝绪光_NA5845\n" +
                "1 2\n" +
                "分页导航:\n" +
                "第01页:不再油腻/增1.3T 别克新款君越已申报\n" +
                "第02页:君越车系频道\n" +
                "6岁以下孩子慎用感冒药\n" +
                "\n" +
                "\n" +
                "\n" +
                "孩子一感冒,很多家长最先想到的是吃非处方感冒药。然而,《英国医学杂志》近日刊登新研究指出,6岁以下孩子最好不要服用减充血剂或含有抗组胺成分的感冒药,它们对孩子健康没有任何好处。\n" +
                "\n" +
                "比利时根特大学高级研究员安德·萨特博士及其研究团队完成的最新临床试验表明,家长经常给孩子使用非处方感冒药,非但不能有效缓解鼻塞、流鼻涕等症状,反而会给孩子带来潜在的副作用,比如血压骤升、应激反应或全身抽搐等。\n" +
                "\n" +
                "萨特博士指出,普通感冒通常由病毒感染所致,症状7~10天内即可消失。研究发现,非处方感冒药对成年人治疗效果也不理想。单独使用减充血剂,或与含抗组胺成分的感冒药、止痛药同时使用,对缓解鼻塞、流鼻涕作用很小,症状依然会持续3~7天。此外,可能出现嗜睡、头痛或胃部不适等副作用。长期使用减充血剂还会导致慢性鼻塞。\n" +
                "\n" +
                "早在2008年,美国食品药品监督管理局(FDA)就发出警告,2岁以下婴幼儿不应服用咳嗽药和感冒药,此类药物只能用于大一点的孩子。美国儿科学会也建议,不要给4岁以下儿童服用非处方咳嗽药和感冒药。\n" +
                "\n" +
                "FDA建议,父母可以使用儿童泰诺或布洛芬缓解发烧和疼痛等症状,使用冷雾加湿器帮孩子鼻腔收缩,呼吸通畅。另外,要多给患儿喝水。(徐 江 编译)\n" +
                "\n" +
                "王亚楠 本文来源:生命时报 责任编辑:王亚楠_NBJ9832\n" +
                "(原标题:台湾女生生理期坐爱心座,大妈怒吼逼其让座:我也生理期)\n" +
                "\n" +
                "今天,一段台湾女生在公交车不让座被骂的视频在社交媒体疯传,引起热议。\n" +
                "\n" +
                "据台媒11月12日报道,台湾台中一名女大学生郑某当天在搭乘公交车时坐在博爱座(大陆方面称“爱心专座”)上,遭一名年约60岁的妇人要求让出座位,此时郑某正在玩手机。\n" +
                "\n" +
                "郑某解释自己因为生理期不舒服,没想到对方却破口大骂“我也生理期,我也站着”、“下三滥,你知不知羞耻?”郑某随后向媒体爆料。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "根据郑某提供的视频,画面中一名短发、穿着衬衫的妇人,不断以高亢的口气说话。\n" +
                "\n" +
                "郑某在视频中问:“你刚刚骂我什么?你再说一次,我可以提告”,这名妇人突然大骂:“骂你什么?博爱座,你知不知道羞耻?你告啊,我闲闲的,专门走法律的,我家做律师的,打官司不用钱。”\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "一旁的乘客帮忙劝解,妇人生气地反击:“我也有生理期过,我也生理期,我也站着”。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "因为无法和妇人沟通,郑某请求司机帮忙,司机以太吵为由请妇人下车,这才结束这场闹剧。\n" +
                "\n" +
                "郑某对台媒表示,自己当时真的很不舒服,看到博爱座没人坐,她才会坐上去,没想到会被妇人公开侮辱。\n" +
                "\n" +
                "“(大妈)问我说凭什么可以坐在那里?这是博爱座,我就说不好意思我生理期不舒服。然后她就说,我生理期也没有不舒服啊,那你凭什么坐在那里?而且你在滑手机,就因为你在滑手机,认定我没有不舒服。”\n" +
                "\n" +
                "郑某没有这料到位妇人随后还辱骂自己是下三滥,指责自己没有教养,自己被气到发抖。郑某说,台中市政府表示,任何有需要的人都可以使用博爱座,目前已经搜集资料,打算对这名妇人提起诉讼。\n" +
                "\n" +
                "对于郑某的遭遇,网民几乎一边倒地表示同情。不少网民对生理期的疼痛痛苦不已:\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "郑某在生理期玩手机,很可能是为了分散注意力,让自己感觉好受一点:\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "而视频中的老妇是蛮横无理、倚老卖老,霸道地让郑某让座属于道德绑架。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "事实上,让座属于道德范畴,帮人是情分,不帮也是本分,爱心专座也不只针对老年人。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "有网民介绍,自己曾在生理期乘坐公交车,身旁的一位老人看到自己不舒服主动让座,这让自己十分感动。因为座位应该留给更需要的人。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "不少网民还注意到视频中帮忙劝解的男性乘客和司机,大家纷纷点赞。\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "女生生理期坐爱心座 大妈怒吼逼其让座:我也生理期\n" +
                "\n" +
                "近年来,有关年轻人在公交车被强迫让座的事件时有发生。早在2015年,也有一名女中学生因生理期不适未让座被老人骂哭。一些老人“倚老卖老”,认为年轻人应该给自己让座,却不理解对方也身体不适。《中国青年报》曾刊文指出,只有走出那种“我弱我有理”的道德绑架,懂得换位思考,在看到自己的权利的同时也在心里为他人留一些位置,由让座引发的矛盾才会少许多。\n" +
                "\n" +
                "张雪 本文来源:观察者网 责任编辑:张雪_NBJS7309\n" +
                "网易刊登此文仅出于传递信息之目的,绝不代表网易赞同其观点或证实其描述。\n" +
                "(原标题:中办通报秦岭后,各省将有个新动作)\n" +
                "\n" +
                "这几天,各地都在通报中央办公厅的一个通报。\n" +
                "\n" +
                "这则通报的名称是《关于陕西省委、西安市委在秦岭北麓西安境内违建别墅问题上严重违反政治纪律以及开展违建别墅专项整治情况的通报》。\n" +
                "\n" +
                "中央办公厅发通报对一个省进行批评的情况极为罕见,政知圈(ID:wepoliics)注意到,目前天津、云南、黑龙江、浙江、甘肃、青海、安徽、山东等地已经开会传达了中办通报,不少省(市、区)都提到,将习近平作出的重要指示批示在本省的贯彻落实情况开展“回头看”。\n" +
                "\n" +
                "省部级单位召开民主生活会的情况 报党中央\n" +
                "\n" +
                "在说通报前,先来了解下这几天陕西方面的动向。\n" +
                "\n" +
                "从11月8日至11月12日,陕西党政领导层陆续召开了“民主生活会”。\n" +
                "\n" +
                "8日:陕西省委理论学习中心组举行专题学习,这是陕西省委常委班子专题民主生活会前的集中学习;省长刘国中主持召开党组会议,为即将召开的省政府领导班子秦岭北麓违规建别墅问题专项整治专题民主生活会进一步做好思想准备。\n" +
                "\n" +
                "9日,陕西省委常委班子召开“秦岭北麓违建别墅专项整治专题民主生活会”。\n" +
                "\n" +
                "10日,陕西省政府领导班子召开“秦岭北麓违规建别墅专项整治专题民主生活会”。\n" +
                "\n" +
                "11日,十二届陕西省政协召开十四次党组(扩大)会议,传达学习中央指示精神。\n" +
                "\n" +
                "12日,西安市召开党政班子秦岭北麓违建别墅专项整治专题民主生活会,省委书记胡和平全程参加和指导。\n" +
                "\n" +
                "政知君注意到,《陕西日报》在报道陕西省政府领导班子召开民主生活会时,提到了“根据中央要求和省委决定”。\n" +
                "\n" +
                "我们从陕西方面释放的各种消息中也不难看出高层对秦岭北麓违建问题的关注。\n" +
                "\n" +
                "陕西省委常委班子召开民主生活会时,中央专项整治工作组组长、中央纪委副书记徐令义,中央专项整治工作组副组长、中央纪委八室主任陈章永出席,中组部派员全程指导。西安方面召开民主生活会时,省委书记全程参加和指导。\n" +
                "\n" +
                "中办对陕西省委批评极为罕见 各省将有个新动作\n" +
                "\n" +
                "中办对陕西省委批评极为罕见 各省将有个新动作\n" +
                "\n" +
                "关于民主生活会,去年1月,中央曾印发《县以上党和国家机关党员领导干部民主生活会若干规定》,规定中提到:\n" +
                "\n" +
                "“上级党组织应当通过派出督导组、派人列席等方式,对下级单位召开的民主生活会进行督促检查和指导,具体工作由组织部门会同纪律检查机关负责。对问题突出的领导班子,上级党组织主要负责人应当亲自过问,派出得力的负责人列席民主生活会,严肃指出问题、深入分析原因、切实帮助解决。”\n" +
                "\n" +
                "“省部级单位召开民主生活会的情况,由中央组织部会同中央纪委机关形成综合报告,报党中央。”\n" +
                "\n" +
                "中办对陕西省委批评极为罕见 各省将有个新动作\n" +
                "\n" +
                "中办对陕西省委批评极为罕见 各省将有个新动作\n" +
                "\n" +
                "一个细节是,中央专项整治工作组还向陕西纪委移交了线索。\n" +
                "\n" +
                "11月13日,陕西省委常委、省纪委书记王兴宁主持召开省纪委常委扩大会议,提到做好中央专项整治工作组移交线索的整治和查处工作。\n" +
                "\n" +
                "各省将自查自纠 “回头看”\n" +
                "\n" +
                "在陕西方面深刻反思的同时,各地也陆续在学习中央办公厅的通报——《关于陕西省委、西安市委在秦岭北麓西安境内违建别墅问题上严重违反政治纪律以及开展违建别墅专项整治情况的通报》。\n" +
                "\n" +
                "“通报”是公文的一种,适用于表彰先进、批评错误、传达重要精神和告知重要情况,对于陕西,很明显是“批评错误”。\n" +
                "\n" +
                "中办通报下发后,11月9日至今,天津、云南、黑龙江、浙江、甘肃、青海、安徽、山东等地已经陆续开会进行了传达。\n" +
                "\n" +
                "一个明显的信号是,中央通报虽然反映的是陕西省、西安市的问题,但实际上给全国各地、各部门敲响了警钟。";
        smc.antiSpam(content);
    }


    /**
     * 反垃圾
     * @param
     * @return
     */
    public Boolean antiSpam(String content) throws IOException {
        //垃圾邮件率
        spamTrainRate = (double) spamTrainMailNum / (double) trainNum;
        List<String> wordList = participle(content);
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


        return false;
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

    private List participle(String content) {
        JiebaSegmenter jb = new JiebaSegmenter();
        List<String> tempList = jb.sentenceProcess(content);

        return tempList;
    }

    //评价模型
    private void evaluationModel() {

        /*--------------训练效率评估----------------------*/
        System.out.println("\n---------------------------------训练效率------------------------------");
        System.out.println("训练个数：" + trainNum);
        System.out.format("训练时间为：%.1fs\n", trainTime);
        System.out.println("测试个数：" + testNum);
        System.out.format("测试时间为：%.1fs\n", testTime);


        /*--------------模型评价指标----------------------*/
        System.out.println("\n---------------------------------评价指标-------------------------------\n");
        //评估各阈值下的指标，用于绘制ROC曲线
        while(typeThreshold < MAX_TYPE_THRESHOLD) {
            //初始化各个指标
            initTestValue();
            //得到该阈值下的混淆矩阵
            for (int i = 0; i < testNum; i++){
                BigDecimal bg = new BigDecimal(typeThreshold);
                if (probability[i] >= bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) {//概率大于阈值则判定为垃圾
                    if (testRealType[i].equals("spam")) {
                        TP++;
                    } else {
                        FP++;//误判为垃圾
                    }
                    //System.out.format("spam %s\n", typeAndIndex[1]);
                } else {
                    if (testRealType[i].equals("ham")) {
                        TN++;
                    } else {
                        FN++;
                    }
                    //System.out.format("ham %s\n", typeAndIndex[1]);
                }
            }
            System.out.format("\n\n------------------阈值（%.2f）-----------------\n\n ", typeThreshold);

            //绘制混淆矩阵
            System.out.println("------------------------------------------ ");
            System.out.println("|                |         实际类         |");
            System.out.println("|      数量      |------------------------|");
            System.out.println("|                |   垃圾    |    正常    |");
            System.out.println("|----------------|------------------------|");
            System.out.println("|预测类  |  垃圾  |    " + TP + "     |     " + FP + "   |");
            System.out.println("|       |  正常  |    " + FN + "      |      " + TN + "    |");
            System.out.println("|----------------|------------------------|\n");

            //根据混淆矩阵得到常用评价指标
            accuracy = (double) (TP + TN) / (double) (TP + FP + FN + TN);
            System.out.format("正确率（accuracy）= (TP+TN)/(P+N) = %.2f\n", accuracy);
            specificity = (double) TN / (double) (TN + FP);
            System.out.format("特效度（specificity）= TN/N = %.2f\n", specificity);
            precision = (double) TP / (double) (TP + FP);
            System.out.format("精度（precision）= TP/(TP+FP) = %.2f\n", precision);
            recall = (double) TP / (double) (TP + FN);
            System.out.format("召回率（recall）= 灵敏度（sensitive）= TP/(TP+FN) = %.2f\n", recall);
            F1_Score = 2 * precision * recall / (precision + recall);
            System.out.format("综合分类率（F1）= 2 * precision * recall / (precision + recall) = %.2f\n", F1_Score);

            //得到ROC曲线坐标值
            TPR = (double) TP / (double) (TP + FN);
            FPR =  (double) FP / (double)(FP + TN);
            //注入ROC坐标集合
            ROCLocalList.add(new Double[]{FPR, TPR});
            //提高阈值
            typeThreshold += TYPE_THRESHOLD_INCREASES;
        }
        System.out.println("\n-----------------------------概率分布曲线------------------------------");
        System.out.println("概率分布坐标点：");
        //创建Dataset对象
        XYDataset probabilityDataset = new XYSeriesCollection();
        XYSeries pSeries = new XYSeries("Positives");
        XYSeries nSeries = new XYSeries("Negatives");
        for (int i = 0; i < probability.length; i++) {
            int index;
            BigDecimal bg = new BigDecimal(probability[i]);
            double tempProbability = bg.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            if (testRealType[i].equals("spam")){
                if ((index = pSeries.indexOf(tempProbability)) > -1) {
                    pSeries.updateByIndex(index,  pSeries.getY(index).intValue() + 1);
                }else{
                    pSeries.add(tempProbability, new Integer(1));
                }
            }else{
                if ((index = nSeries.indexOf(tempProbability)) > -1) {
                    nSeries.updateByIndex(index, nSeries.getY(index).intValue() + 1);
                }else{
                    nSeries.add(tempProbability, new Integer(1));
                }
            }
        }
        System.out.println("正例坐标：");
        for (int i = 0; i < pSeries.getItems().size();i++){
            System.out.format("(%.2f, %d)", pSeries.getDataItem(i).getX(), pSeries.getDataItem(i).getY());
        }
        System.out.println("\n负例坐标：");
        for (int i = 0; i < nSeries.getItems().size();i++){
            System.out.format("(%.2f, %d)", nSeries.getDataItem(i).getX(), nSeries.getDataItem(i).getY());
        }
        ((XYSeriesCollection) probabilityDataset).addSeries(pSeries);
        ((XYSeriesCollection) probabilityDataset).addSeries(nSeries);
        String probabilityFilePath = "probability-Bayes.jpg";
        createLineChart(probabilityDataset, probabilityFilePath,"垃圾邮件正负例概率分布图","概率","样本数",1000,500);

        //绘制ROC曲线并保存在ROC.jpg
        System.out.println("\n-----------------------------ROC曲线------------------------------");
        System.out.println("ROC曲线坐标点：");
        for (Double[] doubles : ROCLocalList){
            System.out.format("(%.2f, %.2f)", doubles[0], doubles[1]);
        }
        String ROCFilePath = "ROC-Bayes.jpg";
        //创建Dataset对象
        XYDataset xyDataset = new XYSeriesCollection();
        XYSeries xySeries = new XYSeries("ROC");
        for (Double[] doubles : ROCLocalList) {
            xySeries.add(doubles[0],doubles[1]);
        }
        ((XYSeriesCollection) xyDataset).addSeries(xySeries);
        createLineChart(xyDataset, ROCFilePath,"垃圾邮件识别ROC曲线图", "FPR", "TPR",500,500);


    }
    //绘制ROC曲线
    public static void createLineChart(XYDataset ds, String filePath,String title,String xAxis,String yAxis,int width,int height) {
        try {
            // 标题,X坐标,Y坐标,数据集合,orientation,是否显示legend,是否显示tooltip,是否使用url链接
            JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis, ds, PlotOrientation.VERTICAL,true, true, false);
            chart.setBackgroundPaint(Color.WHITE);
            Font font = new Font("宋体", Font.BOLD, 12);
            chart.getTitle().setFont(font);
            chart.setBackgroundPaint(Color.WHITE);

            //获得坐标系
            XYPlot xyPlot = chart.getXYPlot();

            //设置标题字体
            chart.getTitle().setFont(font);

            //设置背景颜色
            xyPlot.setBackgroundPaint(Color.WHITE);
            // x轴 // 分类轴网格是否可见
            xyPlot.setDomainGridlinesVisible(true);
            // y轴 //数据轴网格是否可见
            xyPlot.setRangeGridlinesVisible(true);
            // 设置网格竖线颜色
            xyPlot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            // 设置网格横线颜色
            xyPlot.setRangeGridlinePaint(Color.LIGHT_GRAY);


            // 取得Y轴
            ValueAxis rangeAxis = xyPlot.getRangeAxis();
            //设置字体
            rangeAxis.setLabelFont(font);
            rangeAxis.setAutoRange(true);
            // 取得X轴
            ValueAxis valueAxis = xyPlot.getDomainAxis();
            valueAxis.setLabelFont(font);
            valueAxis.setRange(0, 1);

            // 设置X，Y轴坐标上的文字
            valueAxis.setTickLabelFont(font);
            rangeAxis.setTickLabelFont(font);
            // 设置X，Y轴的标题文字
            valueAxis.setLabelFont(font);
            rangeAxis.setLabelFont(font);
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化指标
    private void initTestValue() {
        TP = 0;
        FP = 0;
        TN = 0;
        FN = 0;
        accuracy = 0d;
        specificity = 0d;
        precision = 0d;
        recall = 0d;
        F1_Score = 0d;
        TPR = 0d;
        FPR = 0d;
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