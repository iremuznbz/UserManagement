<html xmlns:th="http://www.springframework.org/schema/mvc">
<head>
    <title>User Management</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://co.4.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/js/mask.js"></script>
    <script src="resources/js/main.js"></script>
    <style>
        .capbox {
            background-color: #9e9e9e;
            border: #8c8989 0px solid;
            border-width: 0px 12px 0px 0px;
            display: inline-block;
            *display: inline;
            zoom: 1; /* FOR IE7-8 */
            padding: 8px 40px 8px 8px;
        }

        .capbox-inner {
            font: bold 11px arial, sans-serif;
            color: #000000;
            background-color: # #d4d4d4;
            margin: 5px auto 0px auto;
            padding: 3px;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }

        #CaptchaDiv {
            font: bold 17px verdana, arial, sans-serif;
            font-style: italic;
            color: #000000;
            background-color: #FFFFFF;
            padding: 4px;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }

        #CaptchaInput {
            margin: 1px 0px 1px 0px;
            width: 135px;
        }
    </style>
</head>
<body id="body" onload="refreshTable();">

<div class="container">

    <div>
        <form class="form-inline" style="margin:20px 20px 20px 20px" id="userForm">
            <div class="form-group">
                <label for="firstname" style="margin-right:5px">First Name:</label>
                <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Enter FirstName"/>
            </div>
            <div class="form-group">
                <label for="lastname" style="margin-left:20px; margin-right:5px">Last Name:</label>
                <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Enter LastName"/>
            </div>
            <div class="form-group">
                <label for="phone" style="margin-left:20px; margin-right:5px">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter Phone"/>
            </div>
            <div class="form-group">
                <div class="capbox">
                    <div id="captchaDiv"></div>
                    <div class="capbox-inner">
                        Type the above number:<br>
                        <input type="hidden" id="captchaText">
                        <input type="text" id="captchaInput" name="captchaInput" size="15"><br>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-default" style="margin-left:20px; margin-right:5px">Add</button>
        </form>


    </div>

    <div>
        <table id="userTable" class="table table-bordered">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
        </table>

    </div>

    <div id="dialog-edit" title="Edit Contact">
        <p>All form fields are required.</p>
        <form class="form-inline">
            <div class="form-group">
                <label for="firstNameInput2">First Name:</label>
                <input type="text" name="firstName2"
                       id="firstNameInput2"
                       class="form-control">
            </div>
            <div class="form-group">

                <label for="lastNameInput2">Last Name:</label>
                <input type="text" name="lastName2" id="lastNameInput2"
                       class="form-control">
            </div>
            <div class="form-group">

                <label for="phone2">Phone:</label>
                <input type="text" name="phone2"
                       id="phone2"
                       class="form-control">
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-default" tabindex="-1"
                       style="position: absolute; top: -1000px">
            </div>
        </form>
    </div>

    <div id="dialog-confirm" title="Delete Contact">
        <p>
				<span class="ui-icon ui-icon-alert"
                      style="float: left; margin: 12px 12px 20px 0;"></span>This contact
            will be permanently deleted and cannot be recovered. Are you sure?
        </p>
    </div>

    <div id="dialog-message" title="Message">
        <p class="message">
				<span class="ui-icon ui-icon-circle-check"
                      style="float: left; margin: 0 7px 50px 0;"></span>
        </p>
    </div>
</div>

</div>
</body>
</html>