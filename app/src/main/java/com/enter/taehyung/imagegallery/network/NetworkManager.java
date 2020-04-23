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

                    for (Element element : elements) {
                        ImageData data = new ImageData();
                        data.setAlt(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_ALT));
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

    private Handler mNetworkHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case NetworkConst.NETOWRK_MESSAGE_WHAT.REQUEST_IMAGE_LIST:
                    Object listenerObject = msg.obj;
                    if (!(listenerObject instanceof NetworkListener)) {
                        return;
                    }

                    NetworkListener listener = (NetworkListener) listenerObject;

                    ArrayList<ImageData> imageList = new ArrayList<>();
                    try {
                        Bundle bundle = new Bundle();
                        Connection.Response response = Jsoup.connect(TARGET_URL).execute();

                        if (response.statusCode() != NetworkConst.STATUS.OK) {
                            Log.d(TAG, "getHtmlData() status is not OK.");
                            listener.onResult(bundle);
                            return;
                        }

                        Document doc = response.parse();
                        Elements elements = doc.select(NetworkConst.PARSER_QUERY.DIV_PARENT).select(NetworkConst.PARSER_QUERY.DIV_CHILD);

                        for (Element element : elements) {
                            ImageData data = new ImageData();
                            data.setAlt(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_ALT));
                            data.setImagePath(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_SRC));

                            imageList.add(data);
                        }

                        bundle.putParcelableArrayList(NetworkConst.BUNDLE_KEY.DATA_IMAGE_LIST, imageList);
                        listener.onResult(bundle);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    private ArrayList<ImageData> getImageList(NetworkListener listener) {
        ArrayList<ImageData> imageList = new ArrayList<>();
        try {
            Bundle bundle = new Bundle();
            Connection.Response response = Jsoup.connect(TARGET_URL).execute();

            if (response.statusCode() != NetworkConst.STATUS.OK) {
                Log.d(TAG, "getHtmlData() status is not OK.");
                listener.onResult(bundle);
                return null;
            }

            Document doc = response.parse();
            Elements elements = doc.select(NetworkConst.PARSER_QUERY.DIV_PARENT).select(NetworkConst.PARSER_QUERY.DIV_CHILD);

            for (Element element : elements) {
                ImageData data = new ImageData();
                data.setAlt(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_ALT));
                data.setImagePath(element.select(NetworkConst.PARSER_QUERY.IMG_TAG).attr(NetworkConst.PARSER_QUERY.IMG_ATTR_SRC));

                imageList.add(data);
            }

            bundle.putParcelableArrayList(NetworkConst.BUNDLE_KEY.DATA_IMAGE_LIST, imageList);
            listener.onResult(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
