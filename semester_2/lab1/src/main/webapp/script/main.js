//buttons init
const rotateButton = document.getElementById("rotate");
const refreshButton = document.getElementById("refresh");
//slider init
var sliderRad = document.getElementById("sliderRange")
//get radius id value in html
const vals = document.getElementById("vals");

rotateButton.onclick = function () {
    rotate = !rotate;
    if (rotate)
        rotateButton.innerText = "Get depth";
    else
        rotateButton.innerText = "Rotate";
}

//set refresh button to create new ocean
refreshButton.onclick = function () {
    socket.send("refresh");
    sliderRad.value = 10;
    vals.innerText = "radius = " + radius;
}

//change radius
sliderRad.oninput = function() {
    radius = this.value;
    vals.innerText = "radius = " + radius;
}

//get scene window
const sceneWindow = document.getElementById("scene");

//GLOBAL values for scene
var scene, camera, renderer, lineMaterial;
//points - for matrix array field, linePoints - for points in visualisation
var points, linePoints, geometry, line;
var sideLength, K;        //matrix side len /scalebility
var rotate;                     //true - rotate field, false - not
var mouseHeight, zeroHeight;    //zeroHeight - ocean serface
var radius;                     //radius - distance value of getting surface
var MYTIME;                     //time handing

//socket init
const socket = new WebSocket("ws://localhost:8080/lab1-1.0-SNAPSHOT/socket");
socket.binaryType = "arraybuffer";

//socket onopen
socket.onopen = function (event) {
    start(String(event.data));
};

socket.onmessage = function (event) {
    var operation = event.data.split(" ")[0];
    if (operation === "board") {    //get new values for board
        setValsFromServer(String(event.data));
    } else if (operation === "refresh" ) {  //get new board
        setDefaultProperties(String(event.data));
        initTable();
    }
    else {                                  //first board init
        setDefaultProperties(String(event.data));
        initStructs();
        initTable();
    }
}

//on open socket init scene
function start(data) {
    initScene();
    MYTIME = window.performance.now();
}

//properties of scene and ocean field
function setDefaultProperties(data) {
    K = 2;
    var tokens = data.split(" ");
    sideLength = parseFloat(tokens[1]);
    mouseHeight = parseFloat(tokens[2]);
    zeroHeight = parseFloat(tokens[3]);
    radius = 10;
    vals.innerText = "radius = " + radius;
}

//create ocean
function initScene() {
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 500);
    camera.position.set(0, 0, 125);
    camera.lookAt(0, 0, 0);
    rotate = true;
    var windowWidth = 1200;
    var windowHeight = 620;
    renderer = new THREE.WebGLRenderer();
    renderer.setSize(windowWidth, windowHeight);
    sceneWindow.appendChild(renderer.domElement);

    lineMaterial = new THREE.LineBasicMaterial( { color:0x0000ff} );
    window.addEventListener('mousemove', onDocumentMouseMove, false);
    var windowHalfX = window.innerWidth / 2 - (window.innerWidth - windowWidth)/2,
        windowHalfY = window.innerHeight / 2 - (window.innerHeight - windowHeight)/2,
        mouseX = 0,
        mouseY = 0;

    //mouse move handling
    function onDocumentMouseMove(event) {
        mouseX = (event.clientX - windowHalfX)/6;
        mouseY = (event.clientY - windowHalfY)/6;
    }


    function animate() {
        requestAnimationFrame(animate);
        render();
    }

    //rotate cam on moving mouse
    function rotateCam(mouseX, mouseY) {
        camera.position.x += (mouseX - camera.position.x) * .05;
        camera.position.y += (-mouseY - camera.position.y) * .05
    }

    //render current state
    function render() {
        if (rotate) {   //scene is rotatable
            rotateCam(mouseX, mouseY);
        }
        else {          //scene is getting depth from server
            if (window.performance.now() - MYTIME >= 80) {
                changeVals(mouseX, mouseY);
            }
        }
        camera.lookAt(scene.position);
        renderer.render(scene, camera);
    }
    animate();
}

// init strucs for ocean
function initStructs() {
    points = [[]]
    linePoints = [];
    for (var i = 0;i < sideLength; ++i) {
        points.push([]);
        linePoints.push([]);
        for (var j = 0;j < sideLength; ++j) {
            linePoints[i].push(new THREE.Vector3(0, 0, 0));
            points[i].push(0);
        }
    }
    for (var i = 0;i < sideLength; ++i) {
        linePoints.push([]);
        for (var j = 0;j < sideLength; ++j) {
            linePoints[i + sideLength].push(new THREE.Vector3(0, 0, 0));
        }
    }
    geometry = [];
    for (var i = 0; i < sideLength+sideLength; ++i)
        geometry.push(new THREE.BufferGeometry().setFromPoints( linePoints[i] ));
    line = [];
    for (var i = 0; i < 2*sideLength; ++i) {
        line.push(new THREE.Line(geometry[i], lineMaterial));
        scene.add(line[i]);
    }
}

// create matrix of points
function initTable() {
    var newPoints = [];
    for (var i = 0;i < sideLength*sideLength; ++i)
        newPoints.push(zeroHeight);
    setNewValsFromArray(newPoints, true);
}

//get matrix from server and add it to current matrix
function setValsFromServer(data) {
    MYTIME = window.performance.now();
    var tockens = data.split(" ");
    var newPoints = [];
    for (var i = 1;i < tockens.length; ++i) {
        newPoints.push(parseFloat(tockens[i]))
    }
    setNewValsFromArray(newPoints, false);
}

//send request to server get new vals  for mouse position
function changeVals(mouseX, mouseY) {
    camera.position.x = 0;
    camera.position.y = 0;

    var posForX = Math.min(Math.max((mouseX - camera.position.x)/K + sideLength/2, 1), sideLength-2)| 0;
    var posForY = Math.min(Math.max((-mouseY - camera.position.x)/K + sideLength/2, 1),sideLength-2) | 0;

    socket.send("get_vals " +  String(posForX) + " " + String(posForY) + " " + radius);
}

//update vals for matrix and visibility lines
function setNewValsFromArray(newPoints, isNew) {    //isNew - true for refresh, false for data from server
    for (var i = 0;i < sideLength; ++i) {
        for (var j = 0;j < sideLength; ++j) {
            if (!isNew)
                points[j][i] = Math.min(newPoints[j*sideLength + i], points[j][i])
            else {
                points[i][j] = newPoints[i*sideLength + j];
            }
        }
    }

    for (var i = 0;i < sideLength; ++i) {
        for (var j = 0;j < sideLength; ++j) {
            linePoints[i][j].x = (i-sideLength/2)*K;
            linePoints[i][j].y = (j-sideLength/2)*K;
            linePoints[i][j].z = points[i][j];
        }
    }
    for (var i = 0;i < sideLength; ++i) {
        for (var j = 0;j < sideLength; ++j) {
            linePoints[i + sideLength][j].x = (j-sideLength/2)*K;
            linePoints[i + sideLength][j].y = (i-sideLength/2)*K;
            linePoints[i + sideLength][j].z = points[j][i];
        }
    }
    for (var i = 0; i < 2*sideLength; ++i)
        geometry[i].setFromPoints( linePoints[i] );
}
