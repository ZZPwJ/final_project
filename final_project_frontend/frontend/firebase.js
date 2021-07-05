 import firebase from "firebase/app";
 import "firebase/auth";
 import "firebase/firestore";

    // Your web app's Firebase configuration
    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
    var firebaseConfig = {
        apiKey: "AIzaSyArf6b9Jj5O-TSr1ot7oiCernlFT_Lk5bQ",
        authDomain: "zzpwj-final-project.firebaseapp.com",
        databaseURL: "https://zzpwj-final-project-default-rtdb.firebaseio.com",
        projectId: "zzpwj-final-project",
        storageBucket: "zzpwj-final-project.appspot.com",
        messagingSenderId: "479032583996",
        appId: "1:479032583996:web:597695220effb28e9aa039",
        measurementId: "G-GSHSYQCX98"
    };
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    firebase.analytics();
    var db = firebase.firestore()
    export default db
