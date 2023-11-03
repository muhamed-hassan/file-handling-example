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

        "<tr> " +
            "<td colspan=\"2\"> " +
                "<button onclick=\"uploadPersonalImage()\">Upload</button> " +
            "</td> " +
        
            "<td></td> " +

            "<td colspan=\"2\"> " +
                "<button onclick=\"resetPersonalImageFields()\">Cancel</button> " +
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

    var requestPayload = new FormData();
    requestPayload.append("personalImage", personalImage);

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/v1/users/" + nationalId + "/images", false);
        //xhttp.setRequestHeader("Content-type", "multipart/form-data");
        xhttp.send(requestPayload);
    } catch (error) {
        alert("An error encountered during uploading the image, so please try again later.");
    }
}

function resetPersonalImageFields() {
    resetInputFields();
}