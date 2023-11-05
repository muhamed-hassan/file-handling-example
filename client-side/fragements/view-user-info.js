function displayViewUserInfoContent() {
    changePageTitle("User's info details");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getViewUserInfoContent();

    hideNavigationMenu();
}

function getViewUserInfoContent() {
    // incoming navigation from those pages:
    /*
    1. Create user
    2. Search for a user
    3. Upload personal image    
    */
    var userInfo = localStorage.getItem("userInfo");
    if (userInfo != null) {
        localStorage.removeItem("userInfo");

        var userInfoObject = JSON.parse(userInfo);
        var content = 
        "<table> " +
            "<tr> " +
                "<td> " +
                    "<label>Name:</label> " +
                "</td> " +
                "<td> " +
                    userInfoObject.name +
                "</td> " +

                "<td></td> " +

                "<td> " +
                    "<label>National ID:</label> " +
                "</td> " +
                "<td> " +
                    userInfoObject.nationalId +
                "</td> " +      
            "</tr> " +

            "<tr> " +
                "<td> " +
                    "<label>Cell phone:</label> " +
                "</td> " +
                "<td> " +
                    userInfoObject.cellPhone + 
                "</td> " +

                "<td></td> " +

                "<td> " +
                    "<label>Email:</label> " +
                "</td> " +
                "<td> " +
                    userInfoObject.email +
                "</td> " +
            "</tr> " +

            "<tr> " +
                "<td> " +
                    "<label>Mailing address:</label> " +
                "</td> " +
                "<td> " +
                    userInfoObject.mailingAddress +
                "</td> " +

                "<td></td> " + 
                "<td></td> " +
                "<td></td> " +
            "</tr> " +

            "<tr>" +
                "<td colspan=\"5\">" +
                    "<img src=\"" + userInfoObject.fileUrl + "\">" +
                "</td>" +
            "</tr> " +
        "</table>" +
        getBackToMainContent();

        return content;
    } else {
        return null;
    }
}