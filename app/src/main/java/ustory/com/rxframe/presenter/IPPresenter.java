package ustory.com.rxframe.presenter;


import rx.Subscriber;
import ustory.com.rxframe.CallBack;
import ustory.com.rxframe.bean.GetIpInfoResponse;
import ustory.com.rxframe.model.IPModel;
import ustory.com.rxframe.model.api.ApiService;

/**
 * Created by qiyue on 2016/6/1.
 */
public class IPPresenter {

    private IPModel model;

    private CallBack mCallBack;

    private static IPPresenter ipPresenter;
    private IPPresenter(CallBack callback){
        this.model = new IPModel();
        this.mCallBack = callback;
    }

    public static synchronized  IPPresenter getInstance(CallBack callback){
        if (ipPresenter!=null){
            return ipPresenter;
        }else{
            ipPresenter = new IPPresenter(callback);
        }
        return ipPresenter;
    }

    public static synchronized void destory(){
        ipPresenter = null;
    }
    public ApiService getIP(String ip){
        mCallBack.onBefore();
        ApiService apiService = model.getIp(ip, new Subscriber<GetIpInfoResponse>() {

            @Override
            public void onStart() {
                super.onStart();


            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mCallBack.onFailure(e);
            }

            @Override
            public void onNext(GetIpInfoResponse getIpInfoResponse) {
                mCallBack.onSuccess(1,getIpInfoResponse);
            }
        });

        return apiService;

    }



}
