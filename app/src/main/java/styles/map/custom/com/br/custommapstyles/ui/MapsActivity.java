package styles.map.custom.com.br.custommapstyles.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import styles.map.custom.com.br.custommapstyles.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FloatingActionButton fabOne, fabTwo, fabThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorMorning));

        //Obtendo os elementos da UI
        this.fabOne = (FloatingActionButton) findViewById(R.id.fab_style_one);
        this.fabTwo = (FloatingActionButton) findViewById(R.id.fab_style_two);
        this.fabThree = (FloatingActionButton) findViewById(R.id.fab_style_three);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        double latitude = -18.910317;
        double longitude = -48.262676;

        LatLng endereco = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(endereco)).setTitle(getResources().getString(R.string.text_marker));
        CameraPosition cameraPosition = new CameraPosition(endereco,16,0,0);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2000, null);

        this.setStyleMap(googleMap);
    }

    private void setStyleMap(final GoogleMap googleMap) {
        //Morning
        this.fabOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getBaseContext(), R.raw.style_morning));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorMorning));
            }
        });

        //Afternoon
        this.fabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getBaseContext(), R.raw.style_afternoon));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorAfternoon));
            }
        });

        //Night
        this.fabThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getBaseContext(), R.raw.style_night));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorNight));
            }
        });
    }
}
