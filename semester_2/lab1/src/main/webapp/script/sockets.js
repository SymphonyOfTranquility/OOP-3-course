const socket = new WebSocket("ws://localhost:8080/lab1-1.0-SNAPSHOT/socket");

socket.binaryType = "arraybuffer";

socket.onopen = function (event) {
    start(String(event.data));
};

socket.onmessage = function (event) {
    var operation = event.data.split(" ")[0];
    if (operation === "board") {
        setValsFromServer(String(event.data));
    } else if (operation === "refresh" ) {
        setProperties(String(event.data));
        initTable();
    }
    else {
        setProperties(String(event.data));
        initStructs();
        initTable();
    }
}