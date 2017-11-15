package com.example.a1102;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivityCheck extends AppCompatActivity {

    SQLiteDatabase userDb;

    String subject, price, variation, statue, deal, description, address, categorySum, image1, image2, image3;

    TextView subjectView, priceView, categoryView;

    final public static String dataBaseName = "userdb";
    final public static String dataBaseTable = "userdb1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_check);

        openAndSelectDatabase(dataBaseName, dataBaseTable);





    }

    public void openAndSelectDatabase(String database, String tablename){
        try {
            // 디비 연결
            userDb = openOrCreateDatabase(database, MODE_PRIVATE, null);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "디비연결을 하지 못했습니다.", Toast.LENGTH_SHORT).show();
        }

        try{
            // 디비 질의문 처리
            String sql = "select * from userdb1";
            Cursor cursor = userDb.rawQuery(sql, null);

            System.out.println("########################검색된 데이터의 갯수: " + cursor.getCount());
            for(int i = 0 ; i<cursor.getCount(); i++){
                cursor.moveToNext();
                System.out.println("#################검색된 튜플의 갯수: " + (i+1));
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "해당하는 테이블을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}


/*

TextView subjectView, priceView, categoryView, variationView, dealView, statueView, descriptionView;


        subjectView = (TextView)findViewById(R.id.subjectView);
        priceView = (TextView)findViewById(R.id.priceView);
        categoryView = (TextView)findViewById(R.id.categoryView);
        variationView = (TextView)findViewById(R.id.variationView);
        dealView = (TextView)findViewById(R.id.dealView);
        statueView = (TextView)findViewById(R.id.statueView);
        descriptionView = (TextView)findViewById(R.id.descriptionView);

        Intent intent;
        intent = getIntent();

try {
        String name = intent.getExtras().getString("subject");
        int price = Integer.parseInt(intent.getExtras().getString("price"));
        String category = intent.getExtras().getString("category1");
        String category2 = intent.getExtras().getString("category2");
        String variation = intent.getExtras().getString("variation");
        String deal = intent.getExtras().getString("deal");
        String statue = intent.getExtras().getString("statue");
        String description = intent.getExtras().getString("description");


        priceView.setText("가격: " + price);
        subjectView.setText("제목: " + name);
        categoryView.setText("카테고리 분류: " +
        category +
        "\n2 차 분류: " +
        category2
        );
        variationView.setText("거래 종류: " + variation);
        dealView.setText("흥정유무: " + deal);
        statueView.setText("매물 상태: " + statue);
        descriptionView.setText("제품 상세 설명: \n" + description);

        }catch (Exception e){
        Toast.makeText(getApplicationContext(), "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
        }*/
