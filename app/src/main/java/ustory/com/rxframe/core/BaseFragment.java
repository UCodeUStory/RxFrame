package ustory.com.rxframe.core;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutId, container, false);
       // ButterKnife.bind(this, rootView);

        initView(rootView);
        initData();
        initListener();
        return rootView;
    }

    protected void setRootView(int layoutId) {
        this.layoutId = layoutId;
    }

    protected abstract void initView(View rootView);

    protected abstract void initListener();

    protected abstract void initData();

    protected <V extends View> V $(int id) {
        return (V) rootView.findViewById(id);
    }


    public void toast(String msg) {
        this.toast(msg, Toast.LENGTH_SHORT);
    }

    public void toast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            Toast.makeText(getActivity(), msg, duration).show();
        } else {
            Toast.makeText(getActivity(), msg, duration).show();
        }
    }

    public void toast(int resId) {
        this.toast(resId, Toast.LENGTH_SHORT);
    }


    public void toast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            Toast.makeText(getActivity(), getResources().getString(resId), duration).show();
        } else {
            Toast.makeText(getActivity(), getResources().getString(resId), duration).show();
        }
    }
}
