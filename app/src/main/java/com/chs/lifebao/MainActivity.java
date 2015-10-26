package com.chs.lifebao;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.chs.lifebao.base.BaseActivity;
import com.chs.lifebao.fragment.DiymainFragment;
import com.chs.lifebao.fragment.FindFragment;
import com.chs.lifebao.fragment.MineFragment;
import com.chs.lifebao.fragment.NewsFragment;
import com.chs.lifebao.library.view.MipcaActivityCapture;
import com.chs.lifebao.utils.DensityUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private  RadioButton rbNews,rbDymain,tbFind,rbMine;
    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;
    private FrameLayout content;
    private PopupWindow menuPop;
    private  Toolbar toolbar;
    private int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        initToolBar();
        intViews();
        initEnvents();
        setSelect(0);
        initPopWindow();
    }

    private void initPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_view,null);
        LinearLayout pop_ll_scanner = (LinearLayout) view.findViewById(R.id.pop_ll_scanner);
        menuPop = new PopupWindow(view,screenWidth/3
        , ViewGroup.LayoutParams.WRAP_CONTENT,true);
        menuPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.transparent));
        menuPop.setBackgroundDrawable(dw);
        menuPop.setOutsideTouchable(true);
        pop_ll_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
                startActivity(intent);
            }
        });
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.scanning:
                        switch (item.getItemId()) {
                            case R.id.scanning:
                                menuPop.showAsDropDown(toolbar,-DensityUtil.dip2px(MainActivity.this,10)
                                        ,-DensityUtil.dip2px(MainActivity.this,10),Gravity.RIGHT);
                                break;
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i)
        {
            case 0:
                if (mTab01 == null)
                {
                    mTab01 = new NewsFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else
                {
                    transaction.show(mTab01);
                }
                rbNews.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_comprehensive_pressed_icon, 0, 0);
                break;
            case 1:
                if (mTab02 == null)
                {
                    mTab02 = new DiymainFragment();transaction.add(R.id.id_content, mTab02);
                } else
                {
                    transaction.show(mTab02);

                }
                rbDymain.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_move_pressed_icon, 0, 0);
                break;
            case 2:
                if (mTab03 == null)
                {
                    mTab03 = new FindFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else
                {
                    transaction.show(mTab03);
                }
                tbFind.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_found_pressed_icon, 0, 0);
                break;
            case 3:
                if (mTab04 == null)
                {
                    mTab04 = new MineFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else
                {
                    transaction.show(mTab04);
                }
                rbMine.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_me_pressed_icon, 0, 0);
                break;

            default:
                break;
        }

        transaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction)
    {
        if (mTab01 != null)
        {
            transaction.hide(mTab01);
        }
        if (mTab02 != null)
        {
            transaction.hide(mTab02);
        }
        if (mTab03 != null)
        {
            transaction.hide(mTab03);
        }
        if (mTab04 != null)
        {
            transaction.hide(mTab04);
        }
    }
    private void initEnvents() {
        rbNews.setOnClickListener(this);
        rbDymain.setOnClickListener(this);
        tbFind.setOnClickListener(this);
        rbMine.setOnClickListener(this);
    }

    private void intViews() {
        rbNews = (RadioButton) findViewById(R.id.rb_comprehensive);
        rbDymain = (RadioButton) findViewById(R.id.rb_move);
        tbFind = (RadioButton) findViewById(R.id.rb_found);
        rbMine = (RadioButton) findViewById(R.id.rb_me);
        content= (FrameLayout) findViewById(R.id.id_content);
    }


    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId())
        {
            case R.id.rb_comprehensive:
                setSelect(0);
                break;
            case R.id.rb_move:
                setSelect(1);
                break;
            case R.id.rb_found:
                setSelect(2);
                break;
            case R.id.rb_me:
                setSelect(3);
                break;

            default:
                break;
        }
    }
    /**
     * 切换图片至暗色
     */
    private void resetImgs()
    {
        rbNews.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.tab_comprehensive_icon, 0, 0);
        rbDymain.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_move_icon, 0, 0);
        tbFind.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_found_icon, 0, 0);
        rbMine.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_me_icon, 0, 0);
    }
}
