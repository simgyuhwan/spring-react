$(document).ready(function(){
    // 목록조회
    $('#codeGroupListBtn').on('click', function(){
        $.ajax({
            type: "GET",
            url: "/codegroups",
            contentType: "application/json; charset=UTF-8",
            success: function(data){
                console.log(data);
                alert(JSON.stringify(data));
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText +"\n" + "error: " + error);
            }
        });
    });

    // 상세 조회
    $("#codeGroupReadBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/codegroups/" + $("#groupCode").val(),
            contentType: "application/json; charset=UTF-8",
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));

                $("#groupName").val(data.groupName);
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    // 등록
    $("#codeGroupRegisterBtn").on("click", function(){
        // id 값인 'groupCode', 'groupName' 값을 가져와서 담아준다.
        var codeGroupObject = {
          groupCode = $("#groupCode").val(),
          groupName = $("groupName").val()
        };

        // JSON 파일을 문자열로 출력해준다. 입력된 값을 문자열로 출력한다고 생각하자.
        alert(JSON.stringify(codeGroupObject));

        // 비동기 전송, 등록이기 때문에 POST 로 보낸다.
        // 값은 JSON 으로 보내기 때문에 key와 값을 "" 에 담아서 보내야 한다.(아님말고)
        $.ajax({
            type: "post",
            url: "/codegroups",
            data: JSON.stringify(codeGroupObject),
            contentType: "application/json; charset=UTF-8",
            success: function(){
                alert("Created");
            },
            error: function(xhr, textStatus, error){
                alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 삭제
    $("#codeGroupDeleteBtn").on("click", function(){
        $.ajax({
            type: "DELETE",
            url: "/codegroups/" + $("#groupCode").val(),
            contentType: "application/json; charset:UTF-8",
            success: function(){
                alert("Delete");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 수정
    $("#codeGroupModifyBtn").on("click", function(){
        var groupCodeVal = $("#groupCode").val();

        var codeGroupObject = {
            groupCode: groupCodeVal,
            groupName: $("#groupName").val()
        };

        $.ajax({
            type: "PUT",
            url: "/codegroup/" + groupCodeVal,
            data: JSON.stringify(codeGroupObject),
            contentType: "application/json; charset=UTF-8",
            success: function(){
                alert("Modified");
            },
            error: function(xhr, status, error){0
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 리셋
    $("#codeGroupResetBtn").on("click", function(){
        $("#groupCode").val("");
        $("#groupName").val("");
    });
});