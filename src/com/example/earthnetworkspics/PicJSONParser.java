package com.example.earthnetworkspics;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PicJSONParser {

	public static List<Pic> getListFromString(String initUrl) {
		String initCommand = HTTPJSONGetter.getJSONString(initUrl);
		List<Pic> lp = new ArrayList<Pic>();
		try {
			JSONObject jo = new JSONObject(initCommand);

			JSONArray ja = jo.getJSONArray("album");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jp = ja.getJSONObject(i);
				Pic p = new Pic();
				p.setName(jp.getString("img_name"));
				p.setDescription(jp.getString("img_description"));
				p.setUrl("http://entest-webappslab.rhcloud.com/images/"
						+ jp.getString("img_filename"));
				lp.add(p);
			}

		} catch (Exception e) {
			return lp;
		}

		return lp;
	}

}
