function showNavigationMenu() {
    document.getElementById("main").style.display = "block";

    // reset the content of replaceableContent to select another one later
    var replaceableContent = getRefToReplaceableContent();
    replaceableContent.innerHTML = "";

    changePageTitle("Main");
}

function hideNavigationMenu() {
    document.getElementById("main").style.display = "none";
}

function getRefToReplaceableContent() {
    return document.getElementById("replaceableContent");
}

function changePageTitle(newTitle) {
    document.title = newTitle;
}

function getBackToMainContent() {
    var content = "<hr />" + 
                "<a id=\"backToMainLink\" href=\"#\" onclick=\"showNavigationMenu()\">Back to main</a>";

    return content;
}

function extractWrittenValue(elementId) {
    var value = document.getElementById(elementId).value;

    return value;
}

function resetInputFields() {
    var numberOfFields = document.getElementsByTagName("input").length;

    for (var cursor = 0; cursor < numberOfFields; cursor++) {
        document.getElementsByTagName("input")[cursor].value = "";
    }
}