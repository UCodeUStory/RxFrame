package ustory.com.rxframe.model;

import android.view.View;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ustory.com.rxframe.bean.GetIpInfoResponse;
import ustory.com.rxframe.constants.Constants;
import ustory.com.rxframe.model.api.ApiService;

/**
 * Created by qiyue on 2016/5/31.
 */
public class IPModel {

    public ApiService getIp(String ip,Subscriber<GetIpInfoResponse> subscriber){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        /**
         * 直接在Presenter里面写是不是好？？
         */
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getIpInfo("63.223.108.42")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return apiService;

    }

}
