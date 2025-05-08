package java_ad3.functional;

import java.util.ArrayList;
import java.util.List;

public class SideEffectListMain {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("사과");
        list1.add("애플");
        list1.add("사과주스");
        System.out.println("Before List = " + list1);
        changeList1(list1);
        System.out.println("After List  = " + list1);

        List<String> list2 = new ArrayList<>();
        list2.add("오렌지");
        list2.add("오란지");
        list2.add("오렌지주스");
        System.out.println("Before List = " + list2);
        List<String> resultList2 = changeList2(list2);
        System.out.println("After List = " + list2);
        System.out.println("After result  = " + resultList2);


    }

    private static void changeList1(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + " + 추가합니다.");
        }
    }

    private static List<String> changeList2(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            newList.add(s + " + 다른 추가입니더");
        }
        return newList;
    }
}
