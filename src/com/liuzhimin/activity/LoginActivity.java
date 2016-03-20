package com.liuzhimin.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.liuzhimin.healthmanager.R;

public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		readUser();
		
		findViewById(R.id.login).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText ed_username = (EditText) findViewById(R.id.username);
				EditText ed_password = (EditText) findViewById(R.id.pswd);
				String username = ed_username.getText().toString();
				String password = ed_password.getText().toString();
				//�����Ժ���Ϻ����ݿ�Ľ���
				
				
				//ת��������
				Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
				startActivity(intent);
				CheckBox cb = (CheckBox) findViewById(R.id.remember);
				if(cb.isChecked()){
					File file = new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/user.txt");
					try {
						FileOutputStream fos = new FileOutputStream(file);
						fos.write((username+"@"+password).getBytes());
						fos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				finish();
				
			}
		});
	}

	
	public void readUser(){
		EditText ed_username = (EditText) findViewById(R.id.username);
		EditText ed_password = (EditText) findViewById(R.id.pswd);
		File file = new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/user.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String text = br.readLine();
			String[] s = text.split("@");
			br.close();
			fis.close();
			ed_username.setText(s[0]);
			ed_password.setText(s[1]);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
