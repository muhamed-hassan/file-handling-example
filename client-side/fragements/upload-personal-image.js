function displayUploadPersonalImageContent() {
    changePageTitle("Upload personal image");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getUploadPersonalImageContent();

    hideNavigationMenu();
}

function getUploadPersonalImageContent() {
    var content = 
    "<table> " +
        "<tr> " +
            "<td> " +
                "<label for=\"nationalId\">National ID:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"nationalId\" name=\"nationalId\"> " +
            "</td> " +

            "<td></td> " +
            "<td></td> " +
            "<td></td> " +      
        "</tr> " +

        "<tr> " +
            "<td> " +
                "<label for=\"personalImage\">Personal image:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"file\" id=\"personalImage\" name=\"personalImage\"> " + 
            "</td> " +

            "<td></td> " +
            "<td></td> " +
            "<td></td> " +
        "</tr> " +

        "<tr style=\"height: 100px;\"> " +
            "<td colspan=\"2\" style=\"text-align: center;\"> " +
                "<button onclick=\"uploadPersonalImage()\" class=\"blue-button\">Upload</button> " +
            "</td> " +
        
            "<td></td> " +

            "<td colspan=\"2\" style=\"text-align: center;\"> " +
                "<button onclick=\"resetPersonalImageFields()\" class=\"cancel-button\">Cancel</button> " +
            "</td> " +
        "</tr> " +
    "</table>" +
    getBackToMainContent();

    return content;
}

/* ************************************************************************************************************************ */
/* ************************************************************************************************************************ */

function uploadPersonalImage() {
    var nationalId = extractWrittenValue("nationalId");
    var personalImage = document.getElementById("personalImage").files[0];

    try {
        var requestPayload = new FormData();
        requestPayload.append("personalImage", personalImage);
        postFile("http://localhost:8080/v1/users/" + nationalId + "/images", requestPayload);
        
        var requestHeaders = new Map();
        requestHeaders.set("Accept", "application/json");
        var response = get("http://localhost:8080/v1/users/" + nationalId, requestHeaders);
        if (response != "") {
            localStorage.setItem("userInfo", response);
            displayViewUserInfoContent();
        } else {
            throw "error";
        } 
    } catch (error) {
        alert("An error encountered during uploading the image, so please try again later.");
    }
}

function resetPersonalImageFields() {
    resetInputFields();
}