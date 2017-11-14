/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var connector = {
    post: function (url, formId, data, succesPostback, errorPostback) {
        var req = {
            method: 'POST',
            url: url,
            headers: {
                'Content-Type': undefined
            },
            data: data
        };
        var xmlhttp;
        if (window.XMLHttpRequest) {
            // code for modern browsers
            xmlhttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        };
        //xmlhttp.setRequestHeader();
        xmlhttp.open("POST", url, false);
        xmlhttp.setRequestHeader("Content-type", "application/json");
        var query = JSON.stringify(data);
        xmlhttp.onreadystatechange = function () {
            console.log(xmlhttp);
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                console.log("here"+xmlhttp.responseText);
                succesPostback(xmlhttp.responseText);
            } else {
                if (errorPostback !== undefined && errorPostback !== null) {
                    errorPostback(xmlhttp.responseText);
                }
            }
        };
        xmlhttp.send(query);
        //$http(req).then(succesPostback, errorPostback);
    }
};