package com.example.beyob.receta;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.beyob.MainActivity;
import com.example.beyob.NavigationIconClickListener;
import com.example.beyob.R;
import com.example.beyob.network.TipoEntry;
import com.example.beyob.recetas.RecetasCardRecyclerViewAdapter;
import com.example.beyob.tipos.TiposArreglarItems;

import java.util.ArrayList;
import java.util.List;

public class RecetaViewFragment extends Fragment {
    private String titulo;

    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.titulo = getArguments() != null ? getArguments().getString("tipo") : "";

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.byb_receta_view_fragment, container, false);

        // Set up the toolbar
        setUpToolbar(view);

        // Set up de RecyclerView
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
//        /*RecetaRecyclerViewAdapter adapter = new RecetaRecyclerViewAdapter(getContext(),
//                TipoEntry.initTipoEntryList(getResources(),"recetas", this.titulo));
//        recyclerView.setAdapter(adapter);*/
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.byb_tipo_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.byb_tipo_grid_spacing_small);
//        recyclerView.addItemDecoration(new TiposArreglarItems(largePadding, smallPadding));

        // Set cut corner background for API 23+
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            view.findViewById(R.id.recetas_grid).setBackground(getContext().getDrawable(R.drawable.byb_background_shape));
//        }

        return view;
    }

    private void setUpToolbar(View view) {
        toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Shop");

//        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
//                getContext(),
//                view.findViewById(R.id.recetas_grid),
//                new AccelerateDecelerateInterpolator()//,
				/*getContext().getResources().getDrawable(R.drawable.byb_branded_menu), // Menu open icon
				getContext().getResources().getDrawable(R.drawable.byb_close_menu)*/
//				)); // Menu close icon

        /*activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.byb_menu_top, menu);
//        super.onCreateOptionsMenu(menu, menuInflater);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_ingredientes:
                    //toolbar.setTitle("Ingredientes");
                    fragment = new IngredientesFragment();
                    loadFragment(fragment,true);
                    return true;
                case R.id.navigation_explicacion:
                    //toolbar.setTitle("Receta");
                    fragment = new ExplicacionFragment();
                    loadFragment(fragment,true);
                    return true;
//                case R.id.navigation_cart:
//                    toolbar.setTitle("Cart");
//                    return true;
//                case R.id.navigation_profile:
//                    toolbar.setTitle("Profile");
//                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    /*private void setupViewPager(ViewPager viewPager) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFragment(new IngredientesFragment(), "Ingredientes");
        adapter.addFragment(new ExplicacionFragment(), "Receta");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/
}
