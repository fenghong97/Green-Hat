package com.hd.ele.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hd.ele.Bean.User;
import com.hd.ele.LoginActivity;
import com.hd.ele.R;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class MeFragment extends Fragment {

    public static User user = null;//new User();

    private static final String welcome = "尊敬的用户，欢迎您！";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        TextView login=(TextView)view.findViewById(R.id.login);
        TextView wel=(TextView)view.findViewById(R.id.wel) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle=getArguments();
        Log.d("get bundle","ok");
        if (bundle!=null)
        {
            if (user == null) {
                user = new User();
                user.setPhone(bundle.getString("phone"));
                //user.setSign(bundle.getString(""));
            }
//            Log.d("bundle",bundle.getString("phone"));
//            login.setText(bundle.getString("phone"));
//            wel.setText("尊敬的用户，欢迎您！");
        }
        if (user != null) {
            login.setText(user.getPhone());
            wel.setText(welcome);
        }

        return view;
    }
}
