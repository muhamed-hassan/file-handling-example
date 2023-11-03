function displayCreateUserContent() {
    changePageTitle("Create user");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getCreateUserContent();

    hideNavigationMenu();
}

function getCreateUserContent() {
    var content = 
    "<table> " +
        "<tr> " +
            "<td> " +
                "<label for=\"name\">Name:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"name\" name=\"name\"> " +
            "</td> " +

            "<td></td> " +

            "<td> " +
                "<label for=\"nationalId\">National ID:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"nationalId\" name=\"nationalId\"> " +
            "</td> " +      
        "</tr> " +

        "<tr> " +
            "<td> " +
                "<label for=\"cellPhone\">Cell phone:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"cellPhone\" name=\"cellPhone\"> " + 
            "</td> " +

            "<td></td> " +

            "<td> " +
                "<label for=\"email\">Email:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"email\" name=\"email\"> " +
            "</td> " +
        "</tr> " +

        "<tr> " +
            "<td> " +
                "<label for=\"mailingAddress\">Mailing address:</label> " +
            "</td> " +
            "<td> " +
                "<input type=\"text\" id=\"mailingAddress\" name=\"mailingAddress\"> " +
            "</td> " +

            "<td></td> " + 
            "<td></td> " +
            "<td></td> " +
        "</tr> " +

        "<tr> " +
            "<td colspan=\"2\"> " +
                "<button onclick=\"saveUser()\">Save</button> " +
            "</td> " +
        
            "<td></td> " +

            "<td colspan=\"2\"> " +
                "<button onclick=\"resetNewUserFields()\">Cancel</button> " +
            "</td> " +
        "</tr> " +
    "</table>" +
    getBackToMainContent();

    return content;
}

/* ************************************************************************************************************************ */
/* ************************************************************************************************************************ */

function saveUser() {
    var userInfoCreateModel = new UserInfoCreateModel();
    userInfoCreateModel.name = extractWrittenValue("name");
    userInfoCreateModel.nationalId = extractWrittenValue("nationalId");
    userInfoCreateModel.cellPhone = extractWrittenValue("cellPhone");
    userInfoCreateModel.email = extractWrittenValue("email");
    userInfoCreateModel.mailingAddress = extractWrittenValue("mailingAddress");

    var requestPayload = JSON.stringify(userInfoCreateModel); 
    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/v1/users", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(requestPayload);
    } catch (error) {
        alert("An error encountered during user creation, so please try again later.");
    }
}

function resetNewUserFields() {
    resetInputFields();
}