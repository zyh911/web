<!DOCTYPE>

<!-- php1 by ZhaoYuhang
     -->

<html>
<head>
<title> Submit!! </title>
</head>
<body>
<?php
error_reporting(15);
$name = $_POST['name'];
$gender = $_POST['gender'];
$age = $_POST['age'];
$email = $_POST['email'];

$db = mysql_connect("localhost", "usr_2015_36", "zyhyhzyy");
if(!$db)
{
	print "<h2>Error - Could not connect to MySQL</h2>";
	exit;
}
$er = mysql_select_db("db_2015_36");
if(!$er)
{
	print "<h2>Error - Could not select the guest database</h2>";
	exit;
}
$query = "INSERT INTO guest(Guest_Name, Age, Gender, E_mail) VALUES('$name', $age, '$gender', '$email')";
trim($query);
$query = stripslashes($query);
$result = mysql_query($query);
if(!$result)
{
	print "Error - the query could not be executed";
	$error = mysql_error();
	print "<p>" . $error . "</p>";
	exit;
}
echo "<h2>You have submitted!!</h2><br />\n";
echo "<form action = \"php2.php\" method = \"post\">\n<input type = \"submit\"  value = \"Query\" />\n</form>";
print "<p><a href = \"homework4-1.html\">go to survey page</a></p>\n";
?>
</body>
</html>