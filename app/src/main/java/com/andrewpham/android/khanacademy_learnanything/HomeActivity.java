package com.andrewpham.android.khanacademy_learnanything;

import android.support.v4.app.Fragment;


public class HomeActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new HomeFragment();
    }
}
