$(document).ready(function(){

    // 목록 조회
    $("#memberListBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/users",
            contentType: "application/json; charset=UTF-8",
            headers : {"Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));
            },
            error: function(xhr, textStatus, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
           }
        })
    });

    // 상세 조회
    $("#memberReadBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/users/" + $("#userNo").val(),
            contentType: "application/json; charset=UTF-8",
            headers : {"Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));

                $("#memberId").val(data.userId);
                $("#memberPw").val(data.userPw);
                $("#memberName").val(data.userName);
                $("#job").val(data.job);

                if(data.authList[0]){
                    $("#memberAuth0").val(data.authList[0].auth);
                }

                if(data.authList[1]){
                    $("#memberAuth1").val(data.authList[1].auth);
                }

                if(data.authList[2]){
                    $("#memberAuth2").val(data.authList[2].auth);
                }
            },
            error: function(xhr, textStatus, error){
                 alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 등록
    $("#memberRegisterBtn").on("click", function(){
        var userObject = {
            userId : $("#memberId").val(),
            userPw : $("#memberPw").val(),
            userName : $("#memberName").val(),
            job: $("#job").val()
        };

        alert(JSON.stringify(userObject));

        $.ajax({
            type: "POST",
            url: "/users",
            data: JSON.stringify(userObject),
            contentType: "application/json; charset=UTF-8",
            headers : {"Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Created");
            },
            error: function(xhr, textStatus, error){
                 alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 삭제
    $("#memberDeleteBtn").on("click", function(){
        $.ajax({
            type: "DELETE",
            url: "/users/" + $("#userNo").val(),
            contentType: "application/json; charset=UTF-8",
            headers : {"Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Deleted");
            },
            error: function(xhr, textStatus, error){
                 alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 수정
    $("#memberModifyBtn").on("click", function(){
        var userNoVal = $("#userNo").val();

        var userObject = {
            userId :$("#memberId").val(),
            userPw : $("#memberPw").val(),
            userName: $("#memberName").val(),
            job : $("#job").val(),
            authList : [
                {
                    userNo : userNoVal,
                    auth: $("#memberAuth0").val()
                },
                {
                    userNo : userNoVal,
                    auth: $("#memberAuth1").val()
                },
                {
                    userNo : userNoVal,
                    auth: $("#memberAuth2").val()
                }
            ]
        };

        alert(JSON.stringify(userObject));

        $.ajax({
            type: "PUT",
            url: "/users/" + userNoVal,
            data: JSON.stringify(userObject),
            contentType: "application/json; charset=UTF-8",
            headers : {"Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Modified");
            },
            error: function(xhr, textStatus, error){
                 alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        })
    });

    // 입력값 리셋
    $("#memberResetBtn").on("click", function(){
        $("#userNo").val("");
        $("#memberId").val("");
        $("#memberPw").val("");
        $("#memberName").val("");
        $("#job").val("");
        $("#memberAuth0").val("");
        $("#memberAuth1").val("");
        $("#memberAuth2").val("");
    });

    $.getJSON("/codes/job",function(list){
        $(list).each(function(){
            var str="<option value=" + this.value + ">" + this.label + "</option>";
            $("#job").append(str);
        })
    });
})