$(document).ready(function()
    {
        $("#subscribe-msg").show();
        $("#register-msg").hide();
        $("#register-image").hide();
        $("#loading").hide();

        $("#notifyBtn").click(function(e) {
            e.preventDefault();
            $("#register-msg").show();
            var emailId = $("#emailId").val();
            if(validateEmail(emailId)){
                $("#loading").show()
                var sendData = {"emailId": emailId};
                $.ajax({
                    type : "POST" ,
                    dataType: "json" ,
                    url : "/matlb/subscriber/add",
                    contentType : 'application/json',
                    data : JSON.stringify(sendData),
                    success : function(result) {
                        //$("#msg").html(result);
                        //alert(result[0].emailId);
                        //alert(JSON.stringify(result[0].emailId));
                        //return false;
                        //var data = JSON.parse(result);
                        //alert(data);
                        //return false;
                        //$.each(result, function(i, item){
                        //    console.log(item.emailId);
                        //    console.log(item.name);
                        //    //console.log(item.dateTime);
                        //});
                        $("#loading").hide();
                        if(result.userCreated == true){
                            $("#subscribe-email").hide();
                            $("#register-image").show();
                        }
                        $("#register-msg").text(result.message);
                        //alert(result.message);
                    }
                });
            } else {
                $("#register-msg").text("Please enter valid email address");
            }
        });
    });

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

