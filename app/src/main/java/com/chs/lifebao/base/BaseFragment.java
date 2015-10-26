package com.chs.lifebao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.lifebao.utils.NetWorkHelper;
import com.chs.lifebao.utils.StringUtils;
import com.chs.lifebao.utils.Url;

/**
 * Created by llbt on 2015/9/16.
 */
public class BaseFragment extends Fragment {
    public View mView;
    /**
     * 当前页
     */
    public int currentPagte = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView != null){
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 判断是否有网络
     *
     * @return
     */

    public boolean hasNetWork() {
        return NetWorkHelper.isNetworkAvailable(getActivity());
    }
    public BaseActivity getMyActivity() {
        return (BaseActivity) getActivity();
    }

    public String getNewUrl(String index) {
        String urlString = Url.TopUrl + Url.TopId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getCommonUrl(String index, String itemId) {
        String urlString = Url.CommonUrl + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getLocalUrl(String index, String itemId) {
        String urlString = Url.Local + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getFangUrl(String index, String itemId) {
        String urlString = Url.FangChan + itemId + "/" + index + Url.endUrl;
        return urlString;
    }

    public String getPhotosUrl(String index) {
        String urlString = Url.TuJi + index + Url.TuJiEnd;
        return urlString;
    }

    public String getReDianPicsUrl(String index) {
        String urlString = Url.TuPianReDian + index + Url.TuJiEnd;
        return urlString;
    }

    public String getDuJiaPicsUrl(String index) {
        String urlString = Url.TuPianDuJia + index + Url.TuJiEnd;
        return urlString;
    }

    public String getMingXingPicsUrl(String index) {
        String urlString = Url.TuPianMingXing + index + Url.TuJiEnd;
        return urlString;
    }

    public String getTiTanPicsUrl(String index) {
        String urlString = Url.TuPianTiTan + index + Url.TuJiEnd;
        return urlString;
    }

    public String getMeiTuPicsUrl(String index) {
        String urlString = Url.TuPianMeiTu + index + Url.TuJiEnd;
        return urlString;
    }

    public String getSinaJingXuan(String index) {
        String urlString = Url.JINGXUAN_ID + index;
        return urlString;
    }

    public String getSinaQuTu(String index) {
        String urlString = Url.QUTU_ID + index;
        return urlString;
    }

    public String getSinaMeiTu(String index) {
        String urlString = Url.MEITU_ID + index;
        return urlString;
    }

    public String getSinaGuShi(String index) {
        String urlString = Url.GUSHI_ID + index;
        return urlString;
    }

    // ��Ƶ http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public String getVideoUrl(String index, String videoId) {
        String urlString = Url.Video + videoId + Url.VideoCenter + index + Url.videoEndUrl;
        return urlString;
    }

    public boolean isNullString(String imgUrl) {

        if (StringUtils.isEmpty(imgUrl)) {
            return true;
        }
        return false;
    }
}
