/*
 * Copyright (c) 2015 by k3b.
 *
 * This file is part of LocationMapViewer.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */

package de.k3b.android.locationMapViewer.geopoint;

import android.graphics.Bitmap;

import java.io.Serializable;

import de.k3b.geo.api.GeoPointDto;

/**
 * a GeoPoint with a bitmap.
 *
 * Created by k3b on 24.03.2015.
 */
public class GeoPointDtoWithBitmap extends GeoPointDto implements Serializable {
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    /** a bitmap representing the GeoPoint */
    private Bitmap bitmap = null;

    /** a bitmap representing the GeoPoint */
    public Bitmap getBitmap() {
        return bitmap;
    }

    public GeoPointDtoWithBitmap setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }
}
