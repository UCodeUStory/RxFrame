package ustory.com.rxframe.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ustory.com.rxframe.CallBack;
import ustory.com.rxframe.R;
import ustory.com.rxframe.bean.GetIpInfoResponse;
import ustory.com.rxframe.core.BaseAppCompatActivity;
import ustory.com.rxframe.model.api.ApiService;
import ustory.com.rxframe.presenter.IPPresenter;

public class RetrofitActivity extends BaseAppCompatActivity implements CallBack {
    private static final String ENDPOINT = "http://ip.taobao.com";
    private static final String LOGIN_URL = "http://192.168.2.115:8080/wddj";
    private TextView mTvContent;
    private ProgressBar mProgressBar;
    private FloatingActionButton fab;
    private IPPresenter ipPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    protected void initListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipPresenter.getIP("63.223.108.42");
            }
        });

    }

    @Override
    protected void initData() {
        ipPresenter = IPPresenter.getInstance(this);
    }


    private void login(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.login("18240332388","1","1","1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                        mTvContent.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(String getIpInfoResponse) {
                        Toast.makeText(RetrofitActivity.this,"",Toast.LENGTH_SHORT).show();
                       // mTvContent.setText(getIpInfoResponse.data.country);
                    }
                });
    }


    @Override
    public void onBefore() {
         debugToast("显示进度条"+Thread.currentThread().getName());
         showProgressDialog();
    }

    @Override
    public void onSuccess(int code, Object object) {
        dismissProgressDialog();
        debugToast("onSuccess"+Thread.currentThread().getName());
          if (code==1){
              GetIpInfoResponse ipInfoResponse = (GetIpInfoResponse)object;
              debugToast("ipinfo="+ipInfoResponse.data.country);
          }
    }

    @Override
    public void onFailure(Object object) {
        dismissProgressDialog();

    }

    @Override
    protected void onStop() {
        super.onStop();
        IPPresenter.destory();
    }
}
