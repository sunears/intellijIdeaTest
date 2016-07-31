package org.sunears;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by sunears on 16/7/29.
 */

public class HelloWorld {
   public static void main(String args[]){
//       System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
//       List<Term> termList = StandardTokenizer.segment("商品和服务");
//       System.out.println(termList);
//
//       List<Term> termList1 = NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程");
//       System.out.println(termList1);
       HanLP.Config.enableDebug();
       String text = "南无阿弥陀佛了知一个巴掌拍不响";
       List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
       System.out.print("原文,");
       for (char c : text.toCharArray())
       {
           System.out.printf("%c", c);
       }
       System.out.println(text.toCharArray().length);

//       System.out.print("拼音（数字音调）,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin);
//       }
//       System.out.println();

       System.out.print("拼音（符号音调）,");
       for (Pinyin pinyin : pinyinList)
       {
           int i=pinyinList.indexOf(pinyin);
           System.out.printf("%s,",
                   pinyin.getPinyinWithToneMark() == "none"?text.toCharArray()[i]:pinyin.getPinyinWithToneMark());
       }
       System.out.println(pinyinList.toArray().length);

//       System.out.print("拼音（无音调）,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin.getPinyinWithoutTone());
//       }
//       System.out.println();
//
//       System.out.print("声调,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin.getTone());
//       }
//       System.out.println();
//
//       System.out.print("声母,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin.getShengmu());
//       }
//       System.out.println();
//
//       System.out.print("韵母,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin.getYunmu());
//       }
//       System.out.println();
//
//       System.out.print("输入法头,");
//       for (Pinyin pinyin : pinyinList)
//       {
//           System.out.printf("%s,", pinyin.getHead());
//       }
       System.out.println();

   }
}
