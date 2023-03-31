package Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.studyplan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrivacyLabelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivacyLabelFragment extends Fragment  implements CompoundButton.OnCheckedChangeListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrivacyLabelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivacyLabelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivacyLabelFragment newInstance(String param1, String param2) {
        PrivacyLabelFragment fragment = new PrivacyLabelFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_privacy_label,
                container, false);

        CheckBox checkBox = view.findViewById(R.id.privacyCheck);
        checkBox.setOnCheckedChangeListener(this);
        stringComponent(view);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked()){
            Toast.makeText(getActivity(), "选中",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "未选中",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void stringComponent(View view){
        SpannableString string = new SpannableString("登录即同意《软件服务协议》与《隐私政策》");

        //设置点击事件
        NolineSpanClickble clickableSpan = new NolineSpanClickble() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.d("","点击了软件服务协议");
                Toast.makeText(getActivity(), "点击了软件服务协议",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoadWebView.class);
                startActivity(intent);
            }
        };
        NolineSpanClickble clickableSpan1 = new NolineSpanClickble() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.d("","点击了隐私政策");
                Toast.makeText(getActivity(), "点击了隐私政策",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),LoadWebView.class);
                startActivity(intent);
            }
        };
        string.setSpan(clickableSpan,5,13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        string.setSpan(clickableSpan1,14,20,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //设置文字前景色
        ForegroundColorSpan foregroundColorSpan =
                new ForegroundColorSpan(Color.parseColor("#4968FF"));
        ForegroundColorSpan foregroundColorSpan1 =
                new ForegroundColorSpan(Color.parseColor("#4968FF"));
        string.setSpan(foregroundColorSpan,5,13,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        string.setSpan(foregroundColorSpan1,14,20,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        TextView tv = view.findViewById(R.id.privacyLabel);
        tv.setText(string);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

//去掉超链接文字的下划线
class NolineSpanClickble extends ClickableSpan {
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(@NonNull View widget) {

    }
}