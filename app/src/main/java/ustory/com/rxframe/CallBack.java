package ustory.com.rxframe;

/**
 * Created by qiyue on 2016/6/1.
 */
public interface CallBack {

     void onBefore();

     void onSuccess(int code,Object object);

     void onFailure(Object object);


}
