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

package de.k3b.android.locationMapViewer;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import de.k3b.geo.api.GeoPointDto;
import microsoft.mappoint.TileSystem;

/**
 * Created by k3b on 16.03.2015.
 */
public class ZoomUtil {
    public static void zoomTo(MapView mapView, int zoom, IGeoPoint min, IGeoPoint max) {
        MapTileProviderBase tileProvider = mapView.getTileProvider();
        IMapController controller = mapView.getController();
        IGeoPoint center = min;

        if (max != null) {
            center = new GeoPoint((max.getLatitudeE6() + min.getLatitudeE6()) / 2, (max.getLongitudeE6() + min.getLongitudeE6()) / 2);

            if (zoom == GeoPointDto.NO_ZOOM) {
                final double requiredMinimalGroundResolutionInMetersPerPixel = ((double) new GeoPoint(min.getLatitudeE6(), min.getLongitudeE6()).distanceTo(max)) / Math.min(mapView.getWidth(), mapView.getHeight());
                zoom = calculateZoom(center.getLatitude(), requiredMinimalGroundResolutionInMetersPerPixel, tileProvider.getMaximumZoomLevel(), tileProvider.getMinimumZoomLevel());
            }
        }
        if (zoom != GeoPointDto.NO_ZOOM) {
            controller.setZoom(zoom);
        }

        if (center != null) {
            controller.setCenter(center);
        }
/*
            if (logger.isDebugEnabled()) {
                logger.debug("DelayedSetCenterZoom.execute({}: ({}) .. ({}),z={}) => ({}), z={} => {}",
                        debugContext,
                        min, max, mZoomLevel, center, zoom, getStatusForDebug());
            }
*/
    }

    private static int calculateZoom(double latitude, double requiredMinimalGroundResolutionInMetersPerPixel, int maximumZoomLevel, int minimumZoomLevel) {
        for (int zoom = maximumZoomLevel; zoom >= minimumZoomLevel; zoom--) {
            if (TileSystem.GroundResolution(latitude, zoom) > requiredMinimalGroundResolutionInMetersPerPixel)
                return zoom;
        }

        return GeoPointDto.NO_ZOOM;
    }

}
