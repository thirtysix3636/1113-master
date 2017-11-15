package com.example.a1102;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class WriteActivity extends AppCompatActivity implements View.OnClickListener{

    Button list_btn, home_btn, submit_btn,address_btn;
    Spinner spinner1, spinner2, spinner3, spinner4,spinner5; // 카테고리 1차분류 , 카테고리 2차분류, 네고분류
    TextView getPicture, addressText, addressTextRes;
    EditText subjectText, priceText, descriptionText;

    String subject, price, variation, statue, deal, description, address, categorySum, image1, image2, image3; // 데이터베이스 넘겨주는 데이터
    String[] arrays;

    SQLiteDatabase userDb; // 데이터 베이스 생성

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_form);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        list_btn = (Button)findViewById(R.id.list_menu_btn);
        home_btn = (Button)findViewById(R.id.home_btn);
        address_btn = (Button) findViewById(R.id.addressButton);
        descriptionText = (EditText) findViewById(R.id.descriptionText);
        addressText = (TextView)findViewById(R.id.addressTextRes);
        addressTextRes = (TextView)findViewById(R.id.addressTextRes);



        try{
            Intent intent2 = getIntent();
            address = intent2.getExtras().getString("addressName");
            addressTextRes.setText(address);
        }catch (Exception e){
            address = "-1";
            e.printStackTrace();
        }

        descriptionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getApplicationContext();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddressFindActivity.class);
                startActivity(intent);
            }
        });
        list_btn.setOnClickListener(this);
        home_btn.setOnClickListener(this);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.action_search:
                        intent = new Intent(getApplicationContext(),SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_chat:
                        intent = new Intent(getApplicationContext(),ChatActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_keyword:
                        intent = new Intent(getApplicationContext(),AlertActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_write:
                        intent = new Intent(getApplicationContext(),WriteActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                    case R.id.action_myinfo:
                        intent = new Intent(getApplicationContext(),MyInfo.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                }
                return false;
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.list_menu_btn:
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.left_Drawer);
                NavigationView drawerView = (NavigationView) findViewById(R.id.drawer_view);
                drawerLayout.openDrawer(drawerView);
                break;
            case R.id.home_btn:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
                break;
        }
    }
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.categorySpinner2);
        List<String> list = new ArrayList<String>();
        try{
        if(String.valueOf(spinner1.getSelectedItem()).equals("컴퓨터")) {
            list.add("중고 컴퓨터");
            list.add("컴퓨터 주변기기");
            list.add("하드웨어");
        }
        else if(String.valueOf(spinner1.getSelectedItem()).equals("모바일")) {
            list.add("아이폰");
            list.add("삼성폰");
            list.add("엘지폰");
            list.add("핸드폰 주변기기");
        }
        else if(String.valueOf(spinner1.getSelectedItem()).equals("생활용품")){
            list.add("사무용품");
            list.add("가정용품");
            list.add("주방용품");
            list.add("기타용품");
        }
        else if(String.valueOf(spinner1.getSelectedItem()).equals("의류")){
            list.add("여성의류");
            list.add("남성의류");
            list.add("아동의류");
            list.add("유니섹스");
        }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "오류가 발생하였습니다. 어플리케이션을 종료합니다.", Toast.LENGTH_LONG).show();
            finish();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner1 = (Spinner) findViewById(R.id.categorySpinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addItemsOnSpinner2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                addItemsOnSpinner2();
            }
        });
    }

    //get the selected dropdown list value
    public void addListenerOnButton() { // 데이터 넘겨주기

        spinner1 = (Spinner) findViewById(R.id.categorySpinner1);
        spinner2 = (Spinner) findViewById(R.id.categorySpinner2);
        spinner3 = (Spinner) findViewById(R.id.negoSpinner);
        spinner4 = (Spinner) findViewById(R.id.saleSpinner);
        spinner5 = (Spinner) findViewById(R.id.productSpinner);
        subjectText = (EditText) findViewById(R.id.subjectText);
        priceText = (EditText)findViewById(R.id.priceText);


        submit_btn = (Button) findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 제출하면서 동시에 디비에 삽입
                subject = subjectText.getText().toString(); // 제목
                price =  (priceText.getText().toString()); // 가격
                categorySum = String.valueOf(spinner1.getSelectedItem()) +"_"+ String.valueOf(spinner2.getSelectedItem());
                variation = String.valueOf(spinner4.getSelectedItem()); // 거래종류
                statue = String.valueOf(spinner5.getSelectedItem()); // 매물상태
                deal = String.valueOf(spinner3.getSelectedItem()); // 흥정유무
                description = descriptionText.getText().toString(); // 제품 상세 설명

                if(address.equals("-1")){
                    Toast.makeText(WriteActivity.this, "주소를 지정해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    address = addressTextRes.getText().toString() ;// 주소의 위도, 경도 좌표
                    insertData(subject, price, categorySum,image1, image2, image3,
                            variation, statue, deal, description, address);

                    Intent intent;
                    intent = new Intent(getApplicationContext(), WriteActivityCheck.class);
                    startActivity(intent); // 필수 요소인 주소가 지정이 되면 그때 화면을 넘김.
                }


                /*
                Intent intent;
                intent = new Intent(getApplicationContext(), WriteActivityCheck.class);
                try {
                    intent.putExtra("subject", subject);
                    intent.putExtra("price", price);
                    intent.putExtra("category1", category1);
                    intent.putExtra("category2", category2);
                    intent.putExtra("variation", variation);
                    intent.putExtra("statue", statue);
                    intent.putExtra("deal", deal);
                    intent.putExtra("description", description);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "작성을 다 한다음에 눌러주세요.", Toast.LENGTH_SHORT).show();
                }

                */
            }
        });
    }

    public void insertData(String subject, String price, String image1, String image2, String image3,
                           String variation, String statue, String deal, String description, String categorySum, String address){
        String sql = "insert into userdb1(subject, price, image1, image2, image3," +
                " variation, statue, deal, description, category) values(?,?,?,?,?,?,?,?,?,?)";

        Object[] params = {subject, price, image1, image2, image3, variation, statue, deal, description, categorySum};
        if(userDb != null){
            try{
                userDb.execSQL(sql, params);
                Toast.makeText(getApplicationContext(), "삽입완료.", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "예상치 못한 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }


}


//category1 = String.valueOf(spinner1.getSelectedItem()); // 카테고리
//category2 = String.valueOf(spinner2.getSelectedItem()); // 카테고리2차분류