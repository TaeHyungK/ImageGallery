package com.enter.taehyung.imagegallery.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

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

    private NetworkManager() {

    }

    public static NetworkManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final NetworkManager INSTANCE = new NetworkManager();
    }

    public void requestData(NetworkListener listener) {
        new Thread() {
            @Override
            public void run() {
                ArrayList<ImageData> imageList = new ArrayList<>();
                Bundle bundle = new Bundle();
                try {
                    Connection.Response response = Jsoup.connect(TARGET_URL).execute();

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
                        Elements textWrapper = element.getElementsByClass("text-wrapper");
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
                    // TODO bundle에 Exception 발생 담아서 retry 3회까지 하도록 수정 필요.
                }
            }
        }.start();
    }
}
