package com.example.a1102;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;


public class AddressFindActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {


    MapPoint MARKER_POINT;
    MapPOIItem marker;
    MapReverseGeoCoder mapReverseGeoCoder = null;
    MapView mapView;
    Intent intent; // 주소 정보 보내기

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_find_avtivity);
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(230.000, 150.000);
        mapView = new MapView(this);
        mapView.setDaumMapApiKey("2dd18c1a795306ea0699c7e49ea922c0");


        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);

        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());

    }


    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReverseGeoCoding(s);

    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    private void onFinishReverseGeoCoding(String result) {
        // Toast.makeText(AddressFindActivity.this, "Reverse Geo-coding : " + result, Toast.LENGTH_SHORT).show();
        // System.out.println("********************"+result);
        intent.putExtra("addressName", result);
        startActivity(intent);

    }

    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.ic_launcher);
            ((TextView) mCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
            ((TextView) mCalloutBalloon.findViewById(R.id.desc)).setText("거래희망 지역");
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }

    }



    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mapReverseGeoCoder = new MapReverseGeoCoder("29df4e962493c5a1619a5d309bec9cf2", mapView.getMapCenterPoint(), AddressFindActivity.this, AddressFindActivity.this);
        mapReverseGeoCoder.startFindingAddress();
        return true;
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(final MapView mapView, final MapPoint mapPoint) {
        MARKER_POINT = mapPoint;
        System.out.println("#########################"+mapPoint);
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(AddressFindActivity.this);
        alert_confirm.setMessage("여기로 지정하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        double latitude =MARKER_POINT.getMapPointGeoCoord().latitude; // y 좌표
                        double longtitude = MARKER_POINT.getMapPointGeoCoord().longitude;// x좌표

                        createDefaultMarker(mapView);

                        mapReverseGeoCoder = new MapReverseGeoCoder("2dd18c1a795306ea0699c7e49ea922c0", mapPoint, AddressFindActivity.this, AddressFindActivity.this);
                        mapReverseGeoCoder.startFindingAddress();

                        intent = new Intent(getApplicationContext(), WriteActivity.class);
                        startActivity(intent);

                    }
                }).setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Toast.makeText(this, "Clicked " + mapPOIItem.getItemName() + " Callout Balloon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
    public void createDefaultMarker(MapView mapView) {
        marker = new MapPOIItem();
        String name = "Default Marker";
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);
        mapView.selectPOIItem(marker, true);
        mapView.setMapCenterPoint(MARKER_POINT, false);
    }





}


