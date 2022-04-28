package com.upn.ferniandita.mydietdiary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class SettingFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private VPAdapterSetting mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.content_tab, container, false);
        mAdapter = new VPAdapterSetting(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) mView.findViewById(R.id.vp_Tab);
        mViewPager.setAdapter(mAdapter);

        mTabLayout = (TabLayout) mView.findViewById(R.id.tab_Layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return mView;
    }

    public class VPAdapterSetting extends FragmentStatePagerAdapter {

        private final ArrayList<Fragment> mFragment;
        private final ArrayList<String> mTitle;

        public VPAdapterSetting(FragmentManager fm) {
            super(fm);
            mFragment = new ArrayList<Fragment>(2);
            mFragment.add(new AccountFragment());
            mFragment.add(new AccountDiagnoseFragment());

            mTitle = new ArrayList<String>(mFragment.size());
            mTitle.add(getString(R.string.setting_tab1));
            mTitle.add(getString(R.string.setting_tab2));
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }


}

