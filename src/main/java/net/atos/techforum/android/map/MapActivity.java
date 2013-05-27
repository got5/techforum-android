package net.atos.techforum.android.map;

import net.atos.techforum.android.R;
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.FragmentById;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.UiThread;

@EActivity(R.layout.activity_map)
public class MapActivity extends Activity implements LocationListener {

	@FragmentById
	MapFragment mapFragment;

	GoogleMap map;

	@SystemService
	LocationManager manager;

	String provider = LocationManager.NETWORK_PROVIDER;

	@AfterViews
	void init() {
		manager.requestLocationUpdates(provider, 10000, 50, this);
		map = mapFragment.getMap();
		map.getUiSettings().setMyLocationButtonEnabled(true);
	}

	@UiThread
	void makeText(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Click
	void getLocation() {
		Location l = manager.getLastKnownLocation(provider);
		if (l != null) {
			MarkerOptions marker = new MarkerOptions();
			LatLng ll = new LatLng(l.getLatitude(), l.getLongitude());
			marker.position(ll).title("My Location");
			map.addMarker(marker);
			map.moveCamera(CameraUpdateFactory.newLatLng(ll));
			map.animateCamera(CameraUpdateFactory.zoomTo(10));
			
			
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		makeText("Position updated");
	}

	@Override
	public void onProviderDisabled(String provider) {
		makeText(String.format("Provider %s is disabled", provider));

	}

	@Override
	public void onProviderEnabled(String provider) {
		makeText(String.format("Provider %s is enabled", provider));
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
