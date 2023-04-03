package Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyplan.LoadWebView;
import com.example.studyplan.LoginActivity;
import com.example.studyplan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountLoginFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountLoginFragment newInstance(String param1, String param2) {
        AccountLoginFragment fragment = new AccountLoginFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_login,
                container,
                false);

        PrivacyLabelFragment privacy = new PrivacyLabelFragment();
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.accountPrivacyContainer,privacy);
        transaction.commit();

        TextView tv = view.findViewById(R.id.changeToPhoneLogin);
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneLoginFragment phoneLoginFragment =
                        new PhoneLoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.loginbacground,
                        phoneLoginFragment).commit();
            }
        });

        TextView textView3 = view.findViewById(R.id.accountFragmentTologin);
        textView3.setClickable(true);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击账号登录");
                Toast.makeText(getActivity(), "点击账号登录",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
