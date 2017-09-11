

function getAddressByCoords(lat, long, callback) {
    var t = new google.maps.LatLng(lat, long),
        i = new google.maps.Geocoder;
    return  i.geocode({ latLng: t }, function (i, a) {
        //common succes handler
        if (a == google.maps.GeocoderStatus.OK) {
            callback(i, a);

        }
        else
        {
            console.log("error getting geo location " + a);
        }
    })
}