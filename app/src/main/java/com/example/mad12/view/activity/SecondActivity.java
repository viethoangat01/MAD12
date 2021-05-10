package com.example.mad12.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mad12.R;
import com.example.mad12.model.entity.User;
import com.example.mad12.utils.Session;
import com.example.mad12.viewmodel.UserViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    boolean session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Session.save(getApplicationContext(),"session","true");
        checkSession();

        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    if(users.size()>0)
                    Toast.makeText(SecondActivity.this, users.get(0).getUserName(), Toast.LENGTH_SHORT).show();
                }
            });
            User user=new User(personId+"",personGivenName,personFamilyName,personEmail,"google",personPhoto.toString());
            Log.d("user", "onCreate: "+personId);
//            userViewModel.insert(user);
            Toast.makeText(this, "name: "+personName, Toast.LENGTH_SHORT).show();
            Log.d("ABCD", acct.getIdToken()+"");
        }
    }

    private void checkSession() {
        //set default value to false
        //when user login first, will set false
        session= Boolean.valueOf(Session.read(getApplicationContext(),"session","false"));
        if(!session){
            //when user login first or logout value is false
            //Back to Login Activity
            //finish()
        }else{
            //when user logged in, value: is true
            Log.d("Session", "checkSession: true");
        }
    }

//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                        Toast.makeText(SecondActivity.this, "Sign out!", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                });
    //        Session.save(getApplicationContext(),"session","false");
//    }
}
