$(document).ready(function(){
    //등록
    $("#adminRegisterBtn").on("click", function(){
        var userObject = {
            userId : $("#adminId").val(),
            userPw : $("#adminPw").val(),
            userName : $("#adminName").val()
        };

        console.log(userObject);

        alert(JSON.stringify(userObject));

        $.ajax({
            type: "POST",
            url: "/users/setup",
            data: JSON.stringify(userObject),
            contentType: "application/json; charset=UTF-8",
            success: function(){
                alert("Created");
            },
             error: function(xhr, status, error){0
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    //입력값 리셋
    $("#adminResetBtn").on("click", function(){
           $("#adminId").val("");
           $("#adminPw").val("");
           $("#adminName").val("");
    });
});