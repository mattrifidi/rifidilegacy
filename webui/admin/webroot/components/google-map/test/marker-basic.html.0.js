
var map = document.querySelector('#map1');

suite('markers', function() {

  // TODO: add test for info window content.
  test('infowindow', function() {
  });
  test.skip('infowindow');

  // TODO: test draggable, hidden, title properties.
  test('properties', function() {
  });
  test.skip('properties');

  // TODO: add test for drag and drop marker.
   test('dragdrop marker', function() {
  });
  test.skip('dragdrop marker');

  test('defaults', function() {
    var markerEl = map.firstElementChild;
    assert.isNull(markerEl.marker);
    assert.isNull(markerEl.map);
    assert.isNull(markerEl.icon);
    assert.isNull(markerEl.info);
    assert.equal(markerEl.latitude, 37.779);
    assert.equal(markerEl.longitude, -122.3892);
    assert.isNull(markerEl.offsetParent,
                  'google-map-marker should be display: none');
  });

  test('update properties', function(done) {
    map.addEventListener('google-map-ready', function(e) {
      var markerEl = map.children[0];
      markerEl.latitude = 37.77493;
      markerEl.longitude = -122.41942;

      assert.equal(
          markerEl.map, map.map, "marker's map is not the google-map's");

      asyncPlatformFlush(function() {
        assert.equal(markerEl.marker.getPosition().lat(), markerEl.latitude);
        assert.equal(markerEl.marker.getPosition().lng(), markerEl.longitude);

        markerEl.icon = 'https://www.google.com/images/srpr/logo11w.png';
        asyncPlatformFlush(function() {
          assert.equal(markerEl.marker.getIcon(), markerEl.icon);

          done();
        });

      });

    });
  });

});

