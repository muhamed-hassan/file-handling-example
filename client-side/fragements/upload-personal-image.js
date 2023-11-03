function displayUploadPersonalImageContent() {
    changePageTitle("Upload personal image");

    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = getUploadPersonalImageContent();

    hideNavigationMenu();
}

function getUploadPersonalImageContent() {
    var content = "html tags go here";

    /*
    embed at the last line of content the below link that trigger this function => showNavigationMenu()

    <hr />
    <a id="backToMainLink" href="#" onclick="showNavigationMenu()">Back to main</a>
    */

    return content;
}