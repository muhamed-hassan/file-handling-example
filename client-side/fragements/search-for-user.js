function displaySearchForUserContent() {
    changePageTitle("Search for a user");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getSearchForUserContent();

    hideNavigationMenu();
}

function getSearchForUserContent() {
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
            "<td colspan=\"2\"> " +
                "<button onclick=\"search()\">Search</button> " +
            "</td> " +
        
            "<td></td> " +

            "<td colspan=\"2\"> " +
                "<button onclick=\"resetSearchForUserFields()\">Cancel</button> " +
            "</td> " +
        "</tr> " +
    "</table>" +
    getBackToMainContent();

    return content;
}

/* ************************************************************************************************************************ */
/* ************************************************************************************************************************ */

function search() {
    var nationalId = extractWrittenValue("nationalId");

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/v1/users/" + nationalId, false);
        xhttp.setRequestHeader("Accept", "application/json");
        xhttp.send();
        if (xhttp.status == 200) {
            localStorage.setItem("userInfo", xhttp.responseText);
            displayViewUserInfoContent();
        } else {
            throw "any error pass throw here to be caught later";
        }        
    } catch (error) {
        alert("An error encountered during retrieving the user's info, so please try again later.");
    }    
}

function resetSearchForUserFields() {
    resetInputFields();
}