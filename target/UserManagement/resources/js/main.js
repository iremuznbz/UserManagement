var data = "";
var selectedIndex = "";

$(function () {

    var dialog = "";
    var form = "";
    var firstName = $("#firstname");
    var lastName = $("#lastname");
    var phoneNumber = $("#phone");
    var tips = $(".validateTips");

    $("#userForm").submit(function (event) {
        event.preventDefault();
        createUser();
    });


    function createUser() {

        var formData = {
            firstName: $("#firstname").val(),
            lastName: $("#lastname").val(),
            phone: $("#phone").val()
        }

        var valid = true;

        valid = valid && checkLength(firstName, "First Name", 0);
        valid = valid && checkLength(lastName, "Last Name", 0);
        valid = valid && checkLength(phoneNumber, "Phone", 0);
        valid = valid && checkform()
        if (valid) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: window.location + "api/user/create",
                data: JSON.stringify(formData),
                dataType: 'text',
                success: function (result) {
                    refreshTable();
                    $("#firstname").val("");
                    $("#lastname").val("");
                    $("#phone").val("");

                    $("#captchaInput").val("");
                }
            });
        }
    }

    delete_ = function (selectedIndex) {
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: window.location + "/api/user/" + data[selectedIndex].id + "/delete",
            dataType: 'text',
            success: function (response) {
                $('.message').html(response.msg);
                openMessagePopup();
                refreshTable();
            },
        });
    }

    refreshTable = function () {
        debugger;
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: window.location + "api/user/list",
            dataType: 'json',
            success: function (response) {
                data = response;
                $('.tr').remove();
                var no = 1;
                for (i = 0; i < data.length; i++) {
                    $("#userTable").append('<tr class="tr"> <td>' + data[i].firstName + '</td> <td>' + data[i].lastName + '</td> <td>' + data[i].phone + '</td> <td><input type="button" class="btn btn-default" onclick="openEditPopup(' + i + ')" value="Edit"></input></td> <td> <input type="button" class="btn btn-default" onclick="openDeletePopup(' + i + ');" value="Delete"></input></td> </tr>');
                    no = no + 1;
                }
            }
        });
    }

    $("#dialog-confirm").dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "Delete": function () {
                delete_(selectedIndex);
                $(this).dialog("close");
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    });

    $(function () {
        $("#dialog-message").dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Ok: function () {
                    $(this).dialog("close");
                }
            }
        });
    });

    openEditPopup = function (i) {
        selectedIndex = i;

        firstName2.val(data[selectedIndex].firstName);
        lastName2.val(data[selectedIndex].lastName);
        phone2.val(data[selectedIndex].phone);

        $("#dialog-edit").dialog("open");
    }

    openDeletePopup = function (i) {
        selectedIndex = i;
        openConfirmPopup();
    }

    openConfirmPopup = function () {
        $("#dialog-confirm").dialog("open");
    }

    openMessagePopup = function () {
        $("#dialog-message").dialog("open");
    }


    var a = "";
    var b = "";
    var c = "";

    add_ = function (formData) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/api/user/create",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (response) {
                console.log(response.msg);
                refreshTable();
            }
        });
    }

    edit_ = function (selectedId, firstName, lastName, phone) {
        var formData = {
            id: selectedId,
            firstName: firstName,
            lastName: lastName,
            phone: phone
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/api/user/update",
            data: JSON.stringify(formData),
            dataType: 'text',
            success: function (response) {
                $('.message').html("Editted successfully.");
                openMessagePopup();
                refreshTable();
            }
        });
    }


    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength(o, n, min) {
        if (o.val().length == min) {
            o.addClass("ui-state-error");
            updateTips("" + n + " can not be empty.")
            return false;
        } else {
            return true;
        }
    }

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Save": function () {
                a = firstName.val();
                b = lastName.val();
                c = phoneNumber.val();
                var valid = true;

                valid = valid && checkLength(firstName, "First Name", 0);
                valid = valid && checkLength(lastName, "Last Name", 0);
                valid = valid && checkLength(phoneNumber, "Phone", 0);

                if (valid) {
                    dialog.dialog("close");
                    add_(a, b, c);
                }
            },
            Cancel: function () {
                dialog.dialog("close");
            }
        },
        close: function () {
            form[0].reset();
        }
    });

    form = dialog.find("form").on("submit", function (event) {
        event.preventDefault();
    });

    var dialog2 = "";
    var form2 = "";
    var firstName2 = $("#firstNameInput2");
    var lastName2 = $("#lastNameInput2");
    var phone2 = $("#phone2");

    function updateTips2(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength2(o, n, min) {
        if (o.val().length == min) {
            o.addClass("ui-state-error");
            updateTips2("" + n + " can not be empty.")
            return false;
        } else {
            return true;
        }
    }

    dialog2 = $("#dialog-edit").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Save": function () {
                a = firstName2.val();
                b = lastName2.val();
                c = phone2.val();
                var selectedId = data[selectedIndex].id;
                var valid = true;

                valid = valid && checkLength2(firstName2, "First Name", 0);
                valid = valid && checkLength2(lastName2, "Last Name", 0);
                valid = valid && checkLength2(phone2, "Phone", 0);

                if (valid) {
                    dialog2.dialog("close");
                    edit_(selectedId, a, b, c);
                }
            },
            Cancel: function () {
                dialog2.dialog("close");
            }
        },
        close: function () {
            form2[0].reset();
        }
    });

    form2 = dialog2.find("form").on("submit", function (event) {
        event.preventDefault();
    });


    // Captcha Script
    var captchaInput = $("#captchaInput")

    function checkform() {
        var why = "";

        if (captchaInput === "") {
            why += "- Please Enter CAPTCHA Code.\n";
        }
        if (captchaInput !== "") {
            if (ValidCaptcha(captchaInput) === false) {
                why += "- The CAPTCHA Code Does Not Match.\n";
            }
        }
        if (why !== "") {
            alert(why);
            return false;
        }
        return true;
    }

    function generateCaptcha() {
        var x = Math.ceil(Math.random() * 9) + '';
        var y = Math.ceil(Math.random() * 9) + '';
        var z = Math.ceil(Math.random() * 9) + '';
        var w = Math.ceil(Math.random() * 9) + '';
        var q = Math.ceil(Math.random() * 9) + '';
        return x + y + z + w + q;
    }


    var code = generateCaptcha();
    $("#captchaText").val(code);
    $("#captchaDiv")[0].innerHTML = code;

// Validate input against the generated number
    function ValidCaptcha() {
        var str1 = removeSpaces($('#captchaText').val());
        var str2 = removeSpaces($('#captchaInput').val());
        if (str1 === str2) {
            return true;
        } else {
            return false;
        }
    }

// Remove the spaces from the entered and generated code
    function removeSpaces(string) {
        return string.split(' ').join('');
    }
});

jQuery(function ($) {
    $("#phone").mask("(999) 999-9999");
});

jQuery(function ($) {
    $("#phone2").mask("(999) 999-9999");
});


