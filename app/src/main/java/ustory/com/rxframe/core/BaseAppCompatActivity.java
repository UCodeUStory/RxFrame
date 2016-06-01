/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package ustory.com.rxframe.core;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;




/**
 * Description：BaseAppCompatActivity
 * Created by：Ustory
 * Time：2016-03-01 16:33
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private ProgressDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
     //   ButterKnife.bind(this);
      //  this.initToolbar(savedInstanceState);
        this.initViews(savedInstanceState);
        this.initData();
        this.initListeners();
    }

    protected abstract int getLayoutId();


    @SuppressWarnings("unchecked")
    protected <V extends View> V $(int id) {
        return (V) this.findViewById(id);
    }



    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initListeners();


    protected abstract void initData();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }


    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
      //  ButterKnife.unbind(this);
        super.onDestroy();
    }

    /*********
     * Toast *
     *********/

    public void debugToast(String msg){
        if (true) {
            this.toast(msg, Toast.LENGTH_SHORT);
        }
    }
    public void toast(String msg) {
        this.toast(msg, Toast.LENGTH_SHORT);
    }

    public void toast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            Toast.makeText(this, msg, duration).show();
        } else {
            Toast.makeText(this, msg, duration).show();
        }
    }

    public void toast(int resId) {
        this.toast(resId, Toast.LENGTH_SHORT);
    }


    public void toast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            Toast.makeText(this, getResources().getString(resId), duration).show();
        } else {
            Toast.makeText(this, getResources().getString(resId), duration).show();
        }
    }

    //lyj_layout
    public void showSnackbar(int layoutId, String msg) {
        Snackbar.make(findViewById(layoutId), msg, Snackbar.LENGTH_SHORT).show();
    }


    public ProgressDialog getProgDialog() {
        return progDialog;
    }

    public void setProgDialog(ProgressDialog progDialog) {
        this.progDialog = progDialog;
    }

    /**
     * 显示进度框
     */
    public void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("操作中...");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    public void dismissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

}
