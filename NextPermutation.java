/*
按字典序生成下一个排列

任何一个n元素集都可以和n个元素的自然数集合{1,2,3,...,n}建立一一映射,故只考虑含n个自然数的集合的全列.
定义字典序:
    设a1,a2,...,an 和 b1,b2,...,bn是{1,2,3,...,n}两个排列,说a1,a2,...,an 按字典序排在 b1,b2,...,bn的前面,当且仅当存在j,使得aj<bj,且对所有k<j,ak=bk.

    如 13254 在 13425的前面, 12345 在 12354的前面, 21345在24315的前面.
    显然,最前面的排列是123...n,最小的排列是n...321


设a1,a2,...,an是{1,2,3,...,n}的一个排列,
考察最后两项a(n-1),和an,
    如果a(n-1)<an,那么,交换a(n-1)和an的值,就得到下一个最大排列a1,a2,...,a,比如35214 -> 35241
    如果a(n-1)>an,那么,仅通过调整着最后两项是不可能的到下一个最大排列的,
此时(即a(n-1)<an时),考察最后三项,a(n-2),a(n-1),an,如果a(n-2)<a(n-1),那么将min(an,a(n-1)) 作为新的a(n-2)的值,
    再将an和a(n-2)升序排列后作为a(n-1)和an,就得到下一个最大排列,比如 35214 -> 35124
    如果a(n-2)<a(n-1) (同时a(n-1)<an),那么仅通过调整最后三项的顺序不可能得到下一个最大排列.
观察此过程,可以得到一般性的思路

如果a1,a2,...,an是{1,2,3,...,n}的一个排列,那么从a(n-1)开始,向前查找,找到第一个满足ak<a(k+1)的项ak,
    再从a(k+1),...,an中找比ak大的数中最小的那个数aj,交换aj和ak的值,再将a(k+1),...,an升序排列在ak的后面,
    如 625431, ak=a2=2, aj=a5=3,交换a2,a5,得635421,将a3,a4,a5,a6升序排列,得631245,此排列就是原排列的下一个最大排列.

    就得到下一个最大排列,且此排列和原排列之间没有其它排列.


伪码描述
----------------------------------------
    procedure next-permutation({a1,a2,...,an}: 集合{1,2,3,...,n} 的一个不等于an-1,an-2,...,3,2,1的排列,)

        k = n-1
        while ak > an
            k:=k-1
        j = n

        while aj < ak
            j:=j-1

        swap aj,ak

        k:=k+1
        j:=n
        while k<j
            swap ak,aj
            k:=k+1
            j:=j-1
        {现在,a1,..,an 是下一个最大排列}

*/
public class NextPermutation{


    /**
       按字典序生成下一个最大排列
       @param a - 自然数 1,2,3,...,n 的一个排列,且不等于 n,n-1,...,3,2,1
    */
    public static boolean nextPermutation(int[] a){
        int n = a.length;
        int k=n-2;
        while(a[k]>a[k+1]) {
            k--;
            if (k<0) return false;
        }
        int j = n-1;
        while(a[j]<a[k]) j--;

        int tmp = a[j];
        a[j] = a[k];
        a[k] = tmp;

        k++;
        j=n-1;
        while(k<j){
            tmp = a[j];
            a[j] = a[k];
            a[k] = tmp;
            k++;
            j--;
        }

        return true;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6};
        // int i = 13;
        int c = 0;
        do{
            System.out.print(++c+"\t: ");
            for(int x:a) {System.out.print(x+",");}
            System.out.println();
        }while(nextPermutation(a));
    }


}
