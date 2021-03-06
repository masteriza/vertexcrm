<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>

    <title>Sign In</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

    <script src="js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="../../css/registration.css"/>
</head>

<body>

<div class="wrapper">
    <main class="content">
        <div class="registration">
            <div id="registration-form">
                <div class="reg-form">
                    <h3><span></span>Sign In</h3>

                    <div class="errorSummary">
                    </div>
                    <p><label>E-mail <span></span></label><input type="text" value="" id="reg_email"></p>

                    <p><label>Password <span></span></label><input type="password" value="" maxlength="32"
                                                                   id="reg_password"></p>

                    <div id="btRegistration" class="row-button">
                        <input type="button" value="Sign In"></div>

                    <br>
                </div>
            </div>
        </div>
    </main>
</div>
<!-- .wrapper -->

<footer class="footer">
    Welcome to Vertex CRM (:
</footer>
<!-- .footer -->

<div id="overlay" class="overlay" style="display:none;"></div>
</body>
</html>