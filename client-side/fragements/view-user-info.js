function displayViewUserInfoContent() {
    changePageTitle("User's info details");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getViewUserInfoContent();

    hideNavigationMenu();
}

function getViewUserInfoContent() {
    var content = "html tags go here";

    /*
    embed at the last line of content the below link that trigger this function => showNavigationMenu()

    <hr />
    <a id="backToMainLink" href="#" onclick="showNavigationMenu()">Back to main</a>
    */

    return content;
}