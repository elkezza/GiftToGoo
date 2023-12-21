document.getElementById('locationList').addEventListener('click', function(e) {
    if (e.target.tagName === 'LI') {
        var mapURL = e.target.getAttribute('data-map-url');
        document.getElementById('mainMap').src = mapURL;
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // Get all buttons with the 'location-btn' class and add a click event listener to them
    var locationButtons = document.querySelectorAll('.location-btn');

    locationButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            // When a button is clicked, get the URL from the 'data-map-url' attribute
            var mapUrl = button.getAttribute('data-map-url');
            // Find the iframe with the class 'google-map' and set its 'src' to the URL
            document.querySelector('.google-map').src = mapUrl;
        });
    });
});

// document.addEventListener('DOMContentLoaded', function() {
//     let locationListItems = document.querySelectorAll('#locationList li');
//
//     locationListItems.forEach(item => {
//         item.addEventListener('click', function() {
//             let mapURL = this.getAttribute('data-map-url');
//             document.querySelector('.google-map').src = mapURL;
//         });
//     });
// });
//
// document.addEventListener('DOMContentLoaded', function() {
//     var mapFrame = document.querySelector('.google-map');
//     var locationButtons = document.querySelectorAll('.location-btn');
//
//     locationButtons.forEach(function(button) {
//         button.addEventListener('click', function() {
//             var mapUrl = button.getAttribute('data-map-url');
//             mapFrame.setAttribute('src', mapUrl);
//         });
//     });
// });

// document.addEventListener('DOMContentLoaded', function() {
//     const mapIframe = document.querySelector('.google-map');
//     const locationItems = document.querySelectorAll('#locationList li');
//
//     locationItems.forEach(item => {
//         item.addEventListener('click', function() {
//             const newMapUrl = this.getAttribute('data-map-url');
//             mapIframe.setAttribute('src', newMapUrl);
//         });
//     });
// });
