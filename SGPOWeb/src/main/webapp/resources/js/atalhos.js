var atalhoF1 = function (id) {
    $('body').bind('keydown', function (e) {
        if (e.keyCode == 112) {
            PrimeFaces.widgets[id].show();
          
           
        }
    });
};

var atalhoF2 = function (id) {
    $('body').bind('keydown', function (e) {
        if (e.keyCode == 112) {
            e.preventDefault();
            PrimeFaces.widgets[id].show();
        }
    });
};