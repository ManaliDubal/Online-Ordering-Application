<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php echo form_open(base_url() . 'admin/login_validate'); ?>
<fieldset>
<legend>Sign-in</legend>
<?php echo validation_errors('<div class="error">', '</div>'); ?>
	<p><label>Username: </label><input type="text" name="uname" /></p>
    <p><label>Password: </label><input type="password" name="pword" /></p>
    <p><input type="submit" value="Login" /></p>
	
<?php echo form_close(); ?>
<a href="#">Forgot Password?</a>
</fieldset>
</body>
</html>