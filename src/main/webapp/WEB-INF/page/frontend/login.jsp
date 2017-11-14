<%-- 
    Document   : home
    Created on : Sep 13, 2017, 11:40:20 PM
    Author     : Vu Manh Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    document.body.style.backgroundColor = "#AABBCC";

    var user = {
        userName: "",
        userImg: "",
    };

    window.fbAsyncInit = function () {
        FB.init({
            appId: '1432649816818850',
            autoLogAppEvents: true,
            xfbml: true,
            version: 'v2.10'
        });
        FB.AppEvents.logPageView();
    };

    loginFacebook = function () {
        FB.getLoginStatus(function (response) {
            console.log(response);
            if (response.status === "connected") {
                user.accessToken = response.authResponse.accessToken;
                getFacebookInfo();
            } else {
                FB.login(function (response) {
                    if (response.status === "connected") {
                        user.accessToken = response.authResponse.accessToken;
                        getFacebookInfo();
                    }
                });
            }
        });
    };

    getFacebookInfo = function () {
        FB.api('/me', function (response) {
            console.log(response);
            user.id = response.id;
            user.userName = response.name;
            user.userImg = "http://graph.facebook.com/" + response.id + "/picture";
            console.log(user);
            getUserFeed();

            //connector.post("loginAction!loginFB.do", null, {accessToken: user.accessToken}, afterLoginSucces, afterLoginError);
        });
    };

    getUserFeed = function () {
        FB.api("/me/taggable_friends",
                function (friendList) {
                    console.log(friendList);
                    var url = "/"+friendList;
                    FB.api(
                            url,
                            function (response) {
                                console.log("friend list id");
                                console.log(response);
                                if (response && !response.error) {
                                    /* handle the result */
                                }
                            }
                    );
                }
        )
//        FB.api(
//                "/{friend-list-id}",
//                function (response) {
//                    console.log("friend list id");
//                    console.log(response);
//                    if (response && !response.error) {
//                        /* handle the result */
//                    }
//                }
//        );
//        FB.api(
//                "/me/feed",
//                function (response) {
//                    if (response && !response.error) {
//                        console.log(response);
//                    }
//                }
//        );
    }

    afterLoginSucces = function (response) {
        var result = JSON.parse(response);
        console.log(result);
        window.location = result.message;
    };

    afterLoginError = function (response) {

    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
<div style="text-align: center">
    <div title="Facebook" class="title" style="background-color: #3d5b99" onclick="loginFacebook();">
        <i class="fa fa-facebook fa-4x iconInTitle" aria-hidden="true" ></i>
        <div>Tài khoản Facebook</div>
    </div>
    <div title="Gmail" class="title" style="background-color: firebrick">
        <i class="fa fa fa-google fa-4x iconInTitle" aria-hidden="true"></i>
        <div>Tài khoản Google</div>
    </div>
    <div title="Tài khoàn tự tạo" class="title" style="background-color: chocolate ">
        <i class="fa fa-user-o fa-4x iconInTitle" aria-hidden="true"></i>
        <div>Tài khoản</div>
    </div>
</div>

<script>
</script>