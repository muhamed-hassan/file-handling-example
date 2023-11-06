function get(requestUrl, requestHeaders) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", requestUrl, false);
    if (requestHeaders != null) {
        for (var entry of requestHeaders.entries()) {
            xhttp.setRequestHeader(entry[0], entry[1]);        
        }
    }
    xhttp.send();

    var response = "";
    if (xhttp.status == 200) {
        response = xhttp.responseText;
    }
    
    return response;
}

function postData(requestUrl, requestHeaders, requestPayload) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", requestUrl, false);
    if (requestHeaders != null) {
        for (var entry of requestHeaders.entries()) {
            xhttp.setRequestHeader(entry[0], entry[1]);        
        }
    }
    xhttp.send(requestPayload);
}

function postFile(requestUrl, requestPayload) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", requestUrl, false);
    xhttp.send(requestPayload);
}