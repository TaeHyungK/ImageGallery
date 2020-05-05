package com.enter.taehyung.imagegallery.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.enter.taehyung.imagegallery.data.ImageData;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();

    private static final String TARGET_URL = "http://www.gettyimagesgallery.com/collection/sasha/";

//    private NetworkManager() {
//
//    }

    // FIXME 굳이 싱글턴이 아니여도 그냥 아래 메소드를 static 메소드로 사용하면 될 것 같다 함
    //  아마 객체를 생성하면서 메모리를 더 잡아먹어서 그런 듯?
//    public static NetworkManager getInstance() {
//        return LazyHolder.INSTANCE;
//    }

//    private static class LazyHolder {
//        public static final NetworkManager INSTANCE = new NetworkManager();
//    }

    /**
     * Jsoup을 통해 이미지 데이터 크롤링
     *
     * @param listener NetworkListener
     */
    public static void requestData(NetworkListener listener) {
        // FIXME 평소에서 스레드를 이렇게 쓰냐고 이거 엄청 위험하다고 함.
        //  Thread 자원이 안드로이드에서 가벼운 자원일까요? 무거운 자원일까요? 부터 하나하나 깜..
        //  매우 무거운 자원이며 평소에는 이렇게 쓰지 않고 Handler 로 사용한다고 함 (-> 잘못 말한듯.. 면접 끝나고 생각났는데 AsyncTask 써야할 듯..)
        //  면접관님 말로는 사용자의 인터랙션으로 인해 해당 메소드가 100번 호출되면 어떻게 될 것 같냐고 물어봄
        //  스레드가 100번 생길 것이고 응답이 올 때마다 리스너 계속 호출되면서 덮어써질것같다고 함.
        //  현재 로직에서는 이 메소드는 사용자 인터랙션이 아무리 많아도 한번밖에 호출이 안된다고 설명했어야 했는데....
        // TODO 추후 정상적인 비동기 방식으로 수정 필요
        new Thread() {
            @Override
            public void run() {
                ArrayList<ImageData> imageList = new ArrayList<>();
                // FIXME 왜 bundle 사용하는지?
                //  번들 사용은 액티비티 혹은 프로세스간에 사용하는 것이 좋음 -> 객체화 하고 Parcelable 쓰면서 메모리가 커지니
                //  여기서 굳이 번들로 쓸 이유가 없음. + 속도 저하 원인이 된다고 함.
                Bundle bundle = new Bundle();
                try {
                    // FIXME Jsoup 동작 원리가 어떤 것인지 아는지?
                    //  connect 시 HttpConnection 을 통해 데이터를 긁어온다고 말함

                    // FIXME 그럼 돔트리를 가져온다는 것을 알텐데 어떻게 하면 개선할 수 있을지? 직접 구현하면 어떻게 구현할 것 같은지?
                    //  잘 대답을 못해서 힌트를 주며 이끌어 내려고했는데 이끌어 내기까지 꽤 오래걸림...
                    //  이미지 태그만을 찾아서 가져오면 될 것 같다고 함.
                    //  File IO에 대한 건 어쩔 수 없이 해야 하니 일단 돔트리를 File로 쓰고 해당 파일을 라인 바이 라인으로 읽어오면서
                    //  <img> 태그인 애들만 걸러내면 될 것 같다 함
                    // FIXME 혹시 정규식 써봤는지?
                    //  써봤고 정규식을 어떻게 썼었는지 이야기함(핸드폰번호 에스터리스크로 변환할 때 썼다 함)
                    //  그럼 <img> 태그를 가져올 때 정규식 쓰면 더 수월하게 할 수 있을 것 같지 않나요? 라고 해서 오...네 그렇네요 함..
                    Connection.Response response = Jsoup.connect(TARGET_URL).timeout(NetworkConst.TIMEOUT_MS).execute();

                    if (response.statusCode() != NetworkConst.STATUS.OK) {
                        Log.d(TAG, "getHtmlData() status is not OK.");
                        listener.onResult(bundle);
                        return;
                    }

                    Document doc = response.parse();
                    Elements elements = doc.select(NetworkConst.PARSER_QUERY.DIV_PARENT).select(NetworkConst.PARSER_QUERY.DIV_CHILD);

                    int idx = 0;
                    for (Element element : elements) {
                        ImageData data = new ImageData();
                        data.setIdx(idx++);
                        Elements textWrapper = element.getElementsByClass(NetworkConst.PARSER_QUERY.IMAGE_TEXT_WRAPPER);
                        if (textWrapper != null) {
                            data.setTitle(textWrapper.text());
                        }
                        data.setImagePath(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_SRC));

                        imageList.add(data);
                    }

                    bundle.putInt(NetworkConst.BUNDLE_KEY.STATE_CODE, response.statusCode());
                    bundle.putParcelableArrayList(NetworkConst.BUNDLE_KEY.DATA_IMAGE_LIST, imageList);
                    listener.onResult(bundle);

                } catch (Exception e) {
                    e.printStackTrace();
                    bundle.putInt(NetworkConst.BUNDLE_KEY.STATE_CODE, NetworkConst.STATUS.NETWORK_EXCEPTION);
                    listener.onResult(bundle);
                }
            }
        }.start();
    }

    /**
     * Refactoring.
     * Jsoup을 통해 이미지 데이터 크롤링
     *
     * @param listener NetworkListener
     */
    public static void requestAsyncData(NetworkListener listener) {
        JsoupTask jsoupTask = new JsoupTask();
        jsoupTask.execute(listener);
    }

    private static class JsoupTask extends AsyncTask<NetworkListener, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 실행전 정리 동작.
        }

        @Override
        protected Void doInBackground(NetworkListener... listeners) {
            int state = NetworkConst.STATUS.FAILED;
            ArrayList<ImageData> imageList = new ArrayList<>();
            NetworkListener listener = listeners[0];

            try {
                Connection.Response response = Jsoup.connect(TARGET_URL).timeout(NetworkConst.TIMEOUT_MS).execute();

                if (response.statusCode() != NetworkConst.STATUS.OK) {
                    Log.d(TAG, "getHtmlData() status is not OK.");
                    listener.onResult(state, imageList);
                    return null;
                }
                state = NetworkConst.STATUS.OK;

                Document doc = response.parse();
                Elements elements = doc.select(NetworkConst.PARSER_QUERY.DIV_PARENT).select(NetworkConst.PARSER_QUERY.DIV_CHILD);

                int idx = 0;
                for (Element element : elements) {
                    ImageData data = new ImageData();
                    data.setIdx(idx++);
                    Elements textWrapper = element.getElementsByClass(NetworkConst.PARSER_QUERY.IMAGE_TEXT_WRAPPER);
                    if (textWrapper != null) {
                        data.setTitle(textWrapper.text());
                    }
                    data.setImagePath(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_SRC));

                    imageList.add(data);
                }

                listener.onResult(state, imageList);

            } catch (Exception e) {
                e.printStackTrace();
                listener.onResult(state, imageList);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // 비동기 처리 완료 후 동작.
        }
    }
}
