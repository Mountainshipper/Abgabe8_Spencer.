package com.example.joanneumprojekt.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.joanneumprojekt.databinding.FragmentHomeBinding;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower;
    private TextView txtGetData;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);





        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;






    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;



    }

    @Override
    public void onClick(View view) {
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("User");
        parseQuery.getInBackground("Projekt", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (object != null && e == null) {
                    txtGetData.setText(object.get("name") +"");
                }
            }
        });
    }

    }

