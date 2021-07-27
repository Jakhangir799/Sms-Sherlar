package uz.pdp.student.jahongir.sevgisherlariapp.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import uz.pdp.student.jahongir.sevgisherlariapp.R;
import uz.pdp.student.jahongir.sevgisherlariapp.adapters.RvAdapter;
import uz.pdp.student.jahongir.sevgisherlariapp.databinding.CustomDialogBinding;
import uz.pdp.student.jahongir.sevgisherlariapp.databinding.FragmentCategoryBinding;
import uz.pdp.student.jahongir.sevgisherlariapp.models.SmsSher;
import uz.pdp.student.jahongir.sevgisherlariapp.utils.MyGson;
import uz.pdp.student.jahongir.sevgisherlariapp.utils.MySharedPreference;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;


public class CategoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "sms_category";


    private String mParam1;

    public CategoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    private FragmentCategoryBinding binding;
    private MySharedPreference mySharedPreference;
    private List<SmsSher> alllist;
    private List<SmsSher> categorylist = new ArrayList<>();
    private RvAdapter rvAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        mySharedPreference = MySharedPreference.getInstance(requireContext());


        String str = mySharedPreference.getString();
        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        if (str.equals("")) {
            alllist = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<SmsSher>>() {
            }.getType();
            alllist = MyGson.getInstance().getGson().fromJson(str, type);
        }
        switch (mParam1) {
            case "Saqlangan":
                for (SmsSher smsSher : alllist) {
                    if (smsSher.getIsliked()) {
                        categorylist.add(smsSher);
                    }
                }
                binding.title.setText("Saqlangan \nshe'rlar");
                break;
            case "Yangi":
                for (SmsSher smsSher : alllist) {
                    if (smsSher.getIsnew()) {
                        categorylist.add(smsSher);
                    }
                }
                binding.title.setText("Yangi \nshe'rlar");
                break;
            default:
                for (SmsSher smsSher : alllist) {
                    if (smsSher.getCategory().equals(mParam1)) {
                        categorylist.add(smsSher);
                    }
                }
                switch (mParam1) {
                    case "Sevgi":
                        binding.title.setText("Sevgi \nshe'rlari");
                        break;
                    case "Sog'inch":
                        binding.title.setText("Sog'inch \nshe'rlari");
                        break;
                    case "Tabrik":
                        binding.title.setText("Tabrik \nshe'rlari");
                        break;
                    case "Ota-ona":
                        binding.title.setText("Ota-ona \nxaqida");
                        break;
                    case "Do'stlik":
                        binding.title.setText("Do'stlik \nshe'rlari");
                        break;
                    case "Hazil":
                        binding.title.setText("Hazil \nshe'rlari");
                        break;
                }
                break;
        }

//        if (mParam1.equals("Saqlangan")) {
//            for (SmsSher smsSher : alllist) {
//                if (smsSher.getIsliked()) {
//                    categorylist.add(smsSher);
//                }
//            }
//        } else {
//            for (SmsSher smsSher : alllist) {
//                if (smsSher.getCategory().equals(mParam1)) {
//                    categorylist.add(smsSher);
//                }
//            }
//        }

        rvAdapter = new RvAdapter(categorylist, (smsSher1, position) -> {
            final Boolean[] isliked = new Boolean[1];

            CustomDialogBinding customDialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(inflater.getContext()));

            if (smsSher1.getIsliked()) {
                customDialogBinding.savedDialog.setImageResource(R.drawable.ic_vector);
                isliked[0] = true;
            } else {
                customDialogBinding.savedDialog.setImageResource(R.drawable.ic_heart);
                isliked[0] = false;
            }
            customDialogBinding.nameBottomShed.setText(smsSher1.getName());
            customDialogBinding.descBottomSher.setText(smsSher1.getDescription());
            customDialogBinding.savedDialog.setOnClickListener(v -> {
                if (isliked[0]) {
                    customDialogBinding.savedDialog.setImageResource(R.drawable.ic_heart);
                    isliked[0] = false;
                } else {
                    customDialogBinding.savedDialog.setImageResource(R.drawable.ic_vector);
                    isliked[0] = true;
                }

//                for (SmsSher sher : alllist) {
//                    if (sher.getDescription().equals(smsSher1.getDescription()) && sher.getName().equals(smsSher1.getName())) {
//                        sher.setIsliked(isliked[0]);
//                    }
//                }
                smsSher1.setIsliked(isliked[0]);
                mySharedPreference.removeString();
                String toJson = MyGson.getInstance().getGson().toJson(alllist);
                mySharedPreference.setString(toJson);
                if (mParam1.equals("Saqlangan")) {
                    rvAdapter.notifyItemRemoved(position);
                    rvAdapter.notifyItemRangeChanged(position, alllist.size());
                }else {
                    rvAdapter.notifyItemChanged(position);
                }
            });
            customDialogBinding.copyText.setOnClickListener(v -> {
                String decscopy = customDialogBinding.descBottomSher.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("set", decscopy);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(requireContext(), "Muvaffaqiyatli!", Toast.LENGTH_SHORT).show();
            });
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.CustomBottomSheetDialogTheme);
            bottomSheetDialog.setContentView(customDialogBinding.getRoot());
            bottomSheetDialog.show();


        });


        binding.rv.setAdapter(rvAdapter);

        return binding.getRoot();
    }
}