var atalhoF1 = function (id) {
    $('body').bind('keydown', function (e) {
        if (e.keyCode == 112) {
            console.log(id);
            PrimeFaces.widgets[id].show();


        }
    });
};

var atalhoF2 = function (id) {
    $('body').bind('keydown', function (e) {
        if (e.keyCode == 113) {
            console.log(id);
            PrimeFaces.widgets[id].show();
        }
    });
};