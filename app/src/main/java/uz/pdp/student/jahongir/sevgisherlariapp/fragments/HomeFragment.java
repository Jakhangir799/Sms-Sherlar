package uz.pdp.student.jahongir.sevgisherlariapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.pdp.student.jahongir.sevgisherlariapp.R;
import uz.pdp.student.jahongir.sevgisherlariapp.databinding.FragmentHomeBinding;
import uz.pdp.student.jahongir.sevgisherlariapp.models.SmsSher;
import uz.pdp.student.jahongir.sevgisherlariapp.utils.MyGson;
import uz.pdp.student.jahongir.sevgisherlariapp.utils.MySharedPreference;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentHomeBinding binding;
    private List<SmsSher> Setlist;
    private int count1 = 0;
    private MySharedPreference mySharedPreference;
    private List<SmsSher> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mySharedPreference = MySharedPreference.getInstance(requireContext());

        String count = mySharedPreference.getCount();

        if (count.equals("")) {
            count1 = 1;
            mySharedPreference.setCount("0");
        } else {
            count1 = 0;
        }
        if (this.count1 == 1) {
            loadData();
            String toJson = MyGson.getInstance().getGson().toJson(Setlist);
            mySharedPreference.setString(toJson);
            Toast.makeText(requireContext(), "Setboldi", Toast.LENGTH_SHORT).show();
        }


        String str = mySharedPreference.getString();

        if (str.equals("")) {
            list = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<SmsSher>>() {
            }.getType();
            list = MyGson.getInstance().getGson().fromJson(str, type);
        }

        int countNew = 0;
        int countLiked = 0;
        for (SmsSher smsSher : list) {
            if (smsSher.getIsnew()) {
                countNew++;
            }
            if (smsSher.getIsliked()) {
                countLiked++;
            }
        }

        binding.numberYangilik.setText(String.valueOf(countNew));
        binding.numLike.setText(String.valueOf(countLiked));


        Bundle bundle = new Bundle();
        binding.cardYangilik.setOnClickListener(v -> {
            bundle.putString("sms_category", "Yangi");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardLiked.setOnClickListener(v -> {
            bundle.putString("sms_category", "Saqlangan");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardSevgiSherlari.setOnClickListener(v -> {
            bundle.putString("sms_category", "Sevgi");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardSoginchArmon.setOnClickListener(v -> {
            bundle.putString("sms_category", "Sog'inch");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardTabrikSherlari.setOnClickListener(v -> {
            bundle.putString("sms_category", "Tabrik");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardOtaona.setOnClickListener(v -> {
            bundle.putString("sms_category", "Ota-ona");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardDostlik.setOnClickListener(v -> {
            bundle.putString("sms_category", "Do'stlik");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });
        binding.cardHazilSherlari.setOnClickListener(v -> {
            bundle.putString("sms_category", "Hazil");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.categoryFragment,bundle);
        });


        return binding.getRoot();
    }

    private void loadData() {
        Setlist = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Setlist.add(new SmsSher("Sevgi", true, "Sevgi hatlari", "Sizni birinchi bor ko’rganimdayoq menga yoqib qolgansiz, lekin bu tuyg’u sevgiga aylanadi deb o’ylamagandim . . . ", false));
        }
        for (int i = 0; i < 3; i++) {
            Setlist.add(new SmsSher("Sevgi", true, "Hayron", "z, lekin bu tuyg’u sevgiga aylanadi deb o’ylamagandim . . . ", true));
        }
        for (int i = 0; i < 3; i++) {
            Setlist.add(new SmsSher("Hazil", true, "Hayron", "z, lekin bu tuyg’u sevgiga aylanadi deb o’ylamagandim . . . ", true));
        }
        for (int i = 0; i < 3; i++) {
            Setlist.add(new SmsSher("Do'stlik", true, "Hayron", "z, lekin bu tuyg’u sevgiga aylanadi deb o’ylamagandim . . . ", true));
        }
        for (int i = 0; i < 3; i++) {
            Setlist.add(new SmsSher("Ota-ona", false, "Hayron", "z, lekin bu tuyg’u sevgiga aylanadi deb o’ylamagandim . . . ", true));
        }
        Setlist.add(new SmsSher("Sog'inch", false, "Salom", "Salom", true));
    }
}