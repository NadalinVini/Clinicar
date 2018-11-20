package company.clinicar.clinicar.ActivityContrato;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import company.clinicar.clinicar.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng brazil1 = new LatLng(-25.4432914, -49.2557159);
        LatLng brazil = new LatLng(-25.418732,-49.3291343);
        LatLng brazil2 = new LatLng(-25.4100978, -49.2693819);
        LatLng brazil3 = new LatLng(-25.3799016, -49.2094964);
        LatLng brazil4 = new LatLng(-25.4211191, -49.2617217);
        LatLng brazil5 = new LatLng(-25.4435947,-49.2709106);

        mMap.addMarker(new MarkerOptions().position(brazil).title("Oficina XV"));
        mMap.addMarker(new MarkerOptions().position(brazil1).title("Oficina Jardim Botanico"));
        mMap.addMarker(new MarkerOptions().position(brazil2).title("Oficina Museo do Olho"));
        mMap.addMarker(new MarkerOptions().position(brazil3).title("Oficina do parque atuba"));
        mMap.addMarker(new MarkerOptions().position(brazil4).title("Oficina do Coxa"));
        mMap.addMarker(new MarkerOptions().position(brazil5).title("Oficina da Opet"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brazil1,15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

    }
}
