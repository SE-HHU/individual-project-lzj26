package 软件工程;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class 四则运算 {
    public static void main(String args[])
    {
        System.out.println("请输入需要生成的口算题目数量，如20");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        String []exercises=new String[n];
        int k=0;//存储已经成功生成的题目个数
        int []answers=new int[n];
        Random rand =new Random();
        for(int j=0;;j++) {
            int geshu = rand.nextInt(2) + 1;//先通过随机数确定是一个还是两个运算符
            if (geshu == 1)//运算符为一个的情况
            {
                int a = rand.nextInt(101);
                int b = rand.nextInt(101);
                    int yuansuai = rand.nextInt(100) % 2;//判断运算符，单数规定为+，双为-
                    String a1 = String.valueOf(a);
                    String b1 = String.valueOf(b);
                    int daan = 0;//答案
                    String one = null;//运算形式
                    if (yuansuai == 1)//+
                    {
                        if(a+b<=100) {//保证在100以内
                            daan = a + b;//答案
                            one = a1 + "+" + b1 + "=";//运算形式
                        }
                    }
                    if (yuansuai == 0)//-
                    {
                        if(a-b>=0) {//保证在100以内
                            daan = a - b;//答案
                            one = a1 + "-" + b1 + "=";//运算形式
                        }
                    }
                    if(one!=null) {
                        int i;//作用是看是否全不相同
                        for (i = 0; i < k; i++)//判断有无重复
                        {
                            if (Objects.equals(exercises[i], one))
                                break;
                        }
                        if (i == k)//无重复则写入,同时记录答案，并增加k的值
                        {
                            exercises[k] = one;
                            answers[k] = daan;
                            k++;
                        }
                    }
            }
            if (geshu == 2)//运算符为二的情况
            {
                int a = rand.nextInt(101);
                int b = rand.nextInt(101);
                int c = rand.nextInt(101);
                    int yuansuai1 = rand.nextInt(100) % 2;//判断运算符，单数规定为+，双为-
                    int yuansuai2 = rand.nextInt(100) % 2;//判断运算符，单数规定为+，双为-
                    String a1 = String.valueOf(a);
                    String b1 = String.valueOf(b);
                    String c1 = String.valueOf(c);
                    int daan = 0;
                    String one = null;
                    if (yuansuai1 == 1 && yuansuai2 == 1)//a+b+c
                    {
                        if(a+b+c<=100) {//保证在100以内
                            daan = a + b + c;
                            one = a1 + "+" + b1 + "+" + c1 + "=";
                        }
                    }
                    if (yuansuai1 == 0 && yuansuai2 == 0)//a-b-c
                    {
                        if((a-b-c>=0)&&(a-b>=0)) {//保证在100以内
                            daan = a - b - c;
                            one = a1 + "-" + b1 + "-" + c1 + "=";
                        }
                    }
                    if (yuansuai1 == 1 && yuansuai2 == 0)//a+b-c
                    {
                        if((a+b<=100)&&(a+b-c>=0)&&(a+b-c>=0))//保证在100以内
                        {
                            daan = a + b - c;
                            one = a1 + "+" + b1 + "-" + c1 + "=";
                        }
                    }
                    if (yuansuai1 == 0 && yuansuai2 == 1)//a-b+c
                    {
                        if((a-b>=0)&&(a-b+c>=0)&&(a-b+c<=100))//保证在100以内
                        {
                            daan = a - b + c;
                            one = a1 + "-" + b1 + "+" + c1 + "=";
                        }
                    }
                    if(one !=null) {//防止数目不符合时的乱写入
                        int i;
                        for (i = 0; i < k; i++) {
                            if (Objects.equals(exercises[i], one))
                                break;
                        }
                        if (i == k)//无重复则写入,同时记录答案，并增加k的值
                        {
                            exercises[k] = one;
                            answers[k] = daan;
                            k++;
                        }
                    }

            }
            if(k==n)//循环跳出条件
                break;
        }
        //用FileWrite写入文本
        FileWriter fil=null;
        FileWriter fil2=null;
        try{
            fil=new FileWriter("Exercises.txt",true);
            fil2=new FileWriter("Answers.txt",true);//答案
            int sum=1;
            for(int i=0;i<n;i++)
            {
                String sum1=String.valueOf(sum);//写入序号
                String xuhao=sum1+".";
                fil.write(xuhao);
                fil.write(exercises[i]);
                fil.write("\n");//以上三条为对题目的处理
                fil.flush();
                fil2.write(xuhao);
                String an=String.valueOf(answers[i]);
                fil2.write(an);
                fil2.write("\n");//以上三条为答案的处理
                fil2.flush();
                sum++;
            }
        }catch(Exception h)
        {
            System.out.print("erroe!");
        }finally{
            if(fil!=null) {
                try {
                    fil.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fil2!=null) {
                try {
                    fil2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
