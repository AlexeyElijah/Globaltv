package com.autism.globaltv.home.pre;

import android.app.Activity;

import com.autism.baselibs.http.rx.RxSubscriber;
import com.autism.globaltv.base.BasePresenter;
import com.autism.globaltv.home.model.HomeEntity;
import com.autism.globaltv.home.model.api.HomeReq;
import com.autism.globaltv.home.view.IHomeView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rx.Subscriber;


/**
 * Author：i5 on 2017/4/1 15:58
 * Used:GlobalTv
 */
public class HomePre extends BasePresenter<IHomeView> {

    public HomePre(Activity activity, IHomeView view) {
        super(activity, view);
    }

    @Override
    public void attachView() {
        Subscriber<JsonObject> subscriber = new RxSubscriber(mActivity) {
            @Override
            public void _onNext(String homeBean) {
                Gson mGson = new Gson();
                HomeEntity homeEntity = mGson.fromJson(homeBean, HomeEntity.class);
                mView.onSuccess(homeEntity.getRoom());
            }

            @Override
            public void _onError(String code) {
                mView.onError();
            }
        };
        HomeReq.getInstance().getHomeData(subscriber);
        addSubscrebe(subscriber);
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
    }
}
