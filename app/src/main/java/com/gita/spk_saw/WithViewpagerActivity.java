package com.gita.spk_saw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class WithViewpagerActivity extends AppCompatActivity {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    TabLayout tl;
    Button btnConfirm;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_viewpager);

        final ArrayList<String> selected_ids = getIntent().getStringArrayListExtra("selected_ids");
        final ArrayList<String> selected_names = getIntent().getStringArrayListExtra("selected_names");

        tl = (TabLayout)findViewById(R.id.tabs);

        koneksiDB = new DBController(this);

        for(int i=0; i<selected_names.size(); i++) {
            tl.addTab(tl.newTab().setText(selected_names.get(i)));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), selected_names.size());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
        tl.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goResultActivity = new Intent(WithViewpagerActivity.this, ResultActivity.class);
                goResultActivity.putExtra("selected_ids", selected_ids);
                startActivity(goResultActivity);
            }
        });

    }

    /**
     * A placeholder fragment containing a simple view.
     */

    static DBController koneksiDB;
    protected static Cursor cursor;

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_with_viewpager, container, false);

            TextView bio_nama = (TextView) rootView.findViewById(R.id.bio_nama);
            TextView bio_tempat_lahir = (TextView) rootView.findViewById(R.id.bio_tempat_lahir);
            TextView bio_tgl_lahir = (TextView) rootView.findViewById(R.id.bio_tgl_lahir);
            TextView bio_jurusan = (TextView) rootView.findViewById(R.id.bio_jurusan);
            TextView bobot_organisasi = (TextView) rootView.findViewById(R.id.bobot_organisasi);
            TextView bobot_semester = (TextView) rootView.findViewById(R.id.bobot_semester);
            TextView bobot_kegiatan = (TextView) rootView.findViewById(R.id.bobot_kegiatan);
            TextView jarak_rumah = (TextView) rootView.findViewById(R.id.jarak_rumah);


            String querySel;
            querySel = "SELECT * FROM alternative where id = " + getArguments().getInt(ARG_SECTION_NUMBER);
            SQLiteDatabase db = koneksiDB.getReadableDatabase();
            cursor = db.rawQuery(querySel, null);

            final ArrayList<HashMap<String, String>> daftarMHS;
            daftarMHS = new ArrayList<HashMap<String, String>>();

//            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
//                case 1:
//                    next_label = "Gita";
//                    break;
//                case 2:
//                    next_label = "Fitria";
//                    break;
//                case 3:
//                    next_label = "Ratnasari";
//                    break;
//
//            }

//            textView.setText(next_label);
            String key_bio_nama, key_bio_tempat_lahir, key_bio_tgl_lahir, key_bio_jurusan;
            if (cursor.moveToFirst() ){
                do {
                    bio_nama.setText(cursor.getString(1));
                    bio_tempat_lahir.setText(cursor.getString(2));
                    bio_tgl_lahir.setText(cursor.getString(3));
                    bio_jurusan.setText(cursor.getString(4));
                } while (cursor.moveToNext());
            }

//            bobot_organisasi.setText(bobot_organisasi);
//            bobot_semester.setText(bobot_semester);
//            bobot_kegiatan.setText(bobot_kegiatan);
//            jarak_rumah.setText(jarak_rumah);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        int get_count_value;

        public SectionsPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            get_count_value = count;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // GET_COUNT_VALUE FOR DINAMIC TOTAL LAYOUT
            return get_count_value;
        }
    }
}
