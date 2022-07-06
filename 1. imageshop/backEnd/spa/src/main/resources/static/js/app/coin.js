$(document).ready(function(){

    $("#coinPayListBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url : "/coins/pay",
            contentType: "application/json; charset=UTF-8",
            headers: {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success : function(data){
                console.log(data);

                alert(JSON.stringify(data));
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        })
    })

    // 충전 내역 조회
    $("#coinChargeListBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/coins",
            contentType: "application/json; charset=UTF-8",
            headers: {
                "Authorization": "Bearer " + ACCESS_TOKEN
            },
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    // 코인 충전
    $("#coinChargeBtn").on("click", function(){
        $.ajax({
            type: "POST",
            url: "/coins/charge/" + $("#coinAmount").val(),
            contentType: "application/json; charset=UTF-8",
            headers: {
                "Authorization": "Bearer " + ACCESS_TOKEN
            },
            success : function(data){
                console.log(data);

                alert(JSON.stringify(data));

                $("#userItemName").val(data.itemName);
                $("#userItemDescription").val(data.description);
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    $("#coinResetBtn").on("click", function(){
        $("#coinAmount").val("");
    });
});