package com.enter.taehyung.imagegallery.util;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.Comparator;

/**
 * 정렬 설정 - ORIGINAL
 * 받아온 데이터순
 */
public class OriginalComparator implements Comparator<ImageData> {
    @Override
    public int compare(ImageData o1, ImageData o2) {
        // FIXME 왜 Integer로 객체화 했는지 물어봄 + int 값으로 그냥 비교할 수 있지 않냐고 어떻게 하는지 설명해달라고 함
        //  compareTo API를 사용하기 위해 객체화 했고 int값으로 바로 비교하는 방법 설명드림
        //  이부분도 래퍼 클래스를 사용하면 메모리가 엄청 잡아먹는다고 이렇게 쓰면 안된다함 + compare() 가 모든 리스트를 하나하나 심지어 중복되게 돌게 돼서 메모리가 기하급수적으로
        //  잡아먹힌다고 함.
        // FIXME int랑 Integer 사이즈가 어떻게 되는지 아시나요?
        //  int는 4byte고 Integer는 정확히 모른다고 함.
        //  -> Integer는 숫자에 따라 다르기는 한데 통상 20~30byte 정도 된다고 엄청난 차이라고 설명해주심..
        return ((Integer) o1.getIdx()).compareTo((Integer) o2.getIdx());
    }
}
